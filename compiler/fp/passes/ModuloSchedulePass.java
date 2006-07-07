/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.util.*;
import fp.hardware.*;
import fp.passes.*;


/** This class pass calls the modulo scheduler.  Not that you couldn't have 
 *  guessed that from the name of the file...
 * 
 * @author Kris Peterson
 */
public class ModuloSchedulePass extends Pass implements GraphPass {
  
  /** The modulo scheduler willl only attempt scheduling a certain number of 
   *  times before it gives up and quits.  This number is actually the number of
   *  times it main loop in iterativeScheule runs, and as each iteration of loop 
   *  is an attempt to schedule one instruction, the number of tries should be 
   *  related to the number of instructions.  _budgetRatio is, in fact, 
   *  multiplied by the number of instructions in the loop and passed to 
   *  iterativeSchedule, who iterates exactly that many times before quiting.
   */
  private float _budgetRatio;
  
  /** when this flag is set to true, operations that have latencies less than 
   *  one, will not be packed within the same cycle.
   */
  private boolean _packNicht;
  private OperationSelection _opSel;
  /** a default _budgetRatio of 2?  that means that it will attempt to schedule
   *  each instruction 2 times.  That is probably not enough, but making it much
   *  bigger, makes waiting much longer and more boring.
   * 
   * @param pm 
   */
  public ModuloSchedulePass(PassManager pm) {
    this(pm, null, GlobalOptions.doNotPackInstrucs, 
        (float)GlobalOptions.budgetRatio);
  }
  public ModuloSchedulePass(PassManager pm, OperationSelection opSel) {
    this(pm, opSel, GlobalOptions.doNotPackInstrucs, (float)GlobalOptions.budgetRatio);
  }
  /** it is possible to specify the budgetRatio when calling the pass
   * 
   * @param pm 
   * @param budgetRatio 
   */
  public ModuloSchedulePass(PassManager pm, OperationSelection opSel, boolean packNicht) {
    this(pm, opSel, packNicht, (float)GlobalOptions.budgetRatio);
  }
  public ModuloSchedulePass(PassManager pm, OperationSelection opSel, boolean packNicht, 
                            float budgetRatio) {
    super(pm);
    _budgetRatio = budgetRatio;
    _packNicht = packNicht;
    _opSel = opSel;
 }
  
  /** First calculate MII.  Then loop calling iterativeSchedule with 
   *  successively larger values of ii, until either a schedule has been found, 
   *  or ii is greater than the total possible length of time needed to execute 
   *  every instruction in the loop sequentially.  Finally, if scheduling was 
   *  successful, call genKernalPrologEpilogCode to create the prolog and epilog 
   *  code and change the control flow graph accordingly.  Please refer to 
   *  ModuloSchedule.java for a more detailed discussion of this.
   * 
   * @param graph_BlockGraph 
   */
  public boolean optimize(BlockGraph graph) {
  
    
    for (Iterator vIt = (new HashSet(graph.getAllNodes())).iterator(); vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      if(!node.getIsRecursive()) continue; 
      if(node.getInstructions().size()==0) continue;
      ModSched modScheduler = new ModSched(_budgetRatio, _opSel, node);
      ChipDef chipInfo = GlobalOptions.chipDef;
      //chipInfo.resetBusCntsAtAllTimes();
      chipInfo.saveNode(node);
      initOpUseLists(node, chipInfo);
      modScheduler.moduloScheduler(node, chipInfo, graph);
      //node.removeAllInstructions();
      //ArrayList schedList = new ArrayList(modSched.getInstructions());
      //sort(schedList);
      //node.addInstructions(schedList);
    }    
  
    /*boolean scheduledOk = true;
    if(graph_BlockGraph.getChipInfo().getOpList()==null) {
      throw new HardwareException("Could not create the modulo schedule, beca" +
                                  "use the hardware analyzation did not compl" +
				  "ete successfully");
      //System.err.println("Could not create the modulo schedule, because the hardware analyzation did not complete successfully");
      //return false;
    }
     
     
    ArrayList nodeList = new ArrayList();
    nodeList.addAll(graph_BlockGraph.getAllNodes());
    for (Iterator vIt = nodeList.iterator(); vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      if(node.getInstructions().size()==0) continue;
      //only do modulo scheduling on loops with recursive data connections
      if(!node.getIsRecursive()) continue; 
      DependenceFlowGraph dfg = node.getDepFlowGraph();
      ModuloSchedule mScheduleInst = new ModuloSchedule(_packNicht, false, _opSel);  

      
      //calculate the maximum possible II which would be when the loop is not 
      //pipelined at all, and all instructions are run one after the other.
      //if out II ever gets above this, we know there was a problem and we 
      //can quit.
      if(((Set)dfg.getAllNodes()).size() > 2) {
        HashMap predTrans = new HashMap();
        mScheduleInst.promoteAllBlockVars(node, predTrans);
        float maxReasonableII = 1;
        for (Iterator it = ((ArrayList)node.getInstructions()).iterator(); 
          it.hasNext(); ) {
          Instruction instr = (Instruction)it.next();
          maxReasonableII += mScheduleInst.getInstrRunLength(instr, 
	                                        graph_BlockGraph.getChipInfo(), 0);
        }
	//calculate initial ii
        int ii = mScheduleInst.calc_MII(node, dfg, 
	                                        graph_BlockGraph.getChipInfo());
        System.out.println("ii " + ii+ " maxReasonableII "
			     + maxReasonableII);
	//System.exit(-1);
	//ii = 1;
        //iteratively try creating a schedule with successively larger ii's
	//until either the limit is reached, or a schedule has been created
	while(((!mScheduleInst.iterativeSchedule(ii, 
	           (int)_budgetRatio*((ArrayList)node.getInstructions()).size(), 
                                   dfg, graph_BlockGraph.getChipInfo(), node, 
				   predTrans))
		|| (!isLegalSchedule(node.getInstructions())))
		 && ( ii < maxReasonableII)) {
          ii++;
	  
          System.err.println("trying ii " + ii + " maxReasonableII "
	                     + maxReasonableII);
          //when ii is changed, the min dist matrix must be updated
	  mScheduleInst.compMinDist(dfg, ii, graph_BlockGraph.getChipInfo());
        }
         // System.out.println("last tried ii " + ii + " maxReasonableII "
	 //                    + maxReasonableII);
	//if it worked add the prolog, epilog, and kernal nodes to the 
	//control flow graph
            System.err.println("hi ii " + ii);
      if(ii < maxReasonableII)
          mScheduleInst.genKernalPrologEpilogCode(node, dfg, 
	                                         graph_BlockGraph.getChipInfo(), 
						 graph_BlockGraph, predTrans);
        else {
          throw new ScheduleException("modulo scheduling failed!  exiting..");
          //System.err.println("modulo scheduling failed!  exiting..");
          //System.exit(-1);
        }
            System.err.println("hi ii " + ii);
      }  
    }
    */ 
    //return scheduledOk;
    return true;
  }
  
  public void initOpUseLists(BlockNode node, ChipDef chipDef) {
    for (Iterator it = node.getInstructions().iterator(); it.hasNext(); ) {
      Instruction instr = (Instruction)it.next();
      chipDef.initOpUseLists(instr.operator());
    }
  }  

  public boolean isLegalSchedule(ArrayList instList) { 
  
      //System.err.println("hi ");
    for (Iterator it = instList.iterator(); 
      it.hasNext(); ) {
      Instruction instr = (Instruction)it.next();
      //System.err.println("instr " + instr);
      //System.err.println("exectime " + instr.getExecTime());
      if(instr.getExecTime()<0) return false;
    }
    return true;
  
  }
  public String name() { 
    return "ModuloSchedulePass";
  }
  
}

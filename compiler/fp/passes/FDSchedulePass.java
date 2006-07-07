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


/** With this pass, you can schedule your design with the force-directed 
 *  algorithm.  Yay!
 * 
 * @author Kris Peterson
 */
public class FDSchedulePass extends Pass implements GraphPass {
   
  private HashMap _scheduleList;
  /** do not ignore the predicates while scheduling?
   */
  private boolean _ignorePredsNicht;
  /** ignore if there are interiteration loop dependencies and schedule using 
   *  force directed anyway?
   */
  private boolean _ignoreInterItDataDep;
   /** when this flag is set to true, operations that have latencies less than 
   *  one, will not be packed within the same cycle.
   */
  private boolean _packNicht;
   /** this variable holds the maximum number of times, the force-directed 
   *   scheduler will attempt to schedule a block before exiting.
   */
  private int _maxAttemptCnt;
  
  public FDSchedulePass(PassManager pm) {
    this(pm, GlobalOptions.doNotIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs, GlobalOptions.maxAttemptsOnFDSched);
    _scheduleList = new HashMap();
  }
  
  public FDSchedulePass(PassManager pm, boolean dntIgnorePreds) {
    this(pm, dntIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs, GlobalOptions.maxAttemptsOnFDSched);
    _scheduleList = new HashMap();
  }
  
  public FDSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                        boolean ignoreDataDep) {
    this(pm, dntIgnorePreds, ignoreDataDep, GlobalOptions.doNotPackInstrucs, 
         GlobalOptions.maxAttemptsOnFDSched);
    _scheduleList = new HashMap();
  }
  public FDSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                        boolean ignoreDataDep, boolean packNicht) {
    this(pm, dntIgnorePreds, ignoreDataDep, packNicht, 
         GlobalOptions.maxAttemptsOnFDSched);
    _scheduleList = new HashMap();
  }

  public FDSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                        boolean ignoreDataDep, boolean packNicht,
			int maxTryCnt) {
    super(pm);
    _ignorePredsNicht = dntIgnorePreds;
    _ignoreInterItDataDep = ignoreDataDep;
    _packNicht = packNicht;
    _maxAttemptCnt = maxTryCnt;
   _scheduleList = new HashMap();
  }
  
  /** if the block is not recursive (unless the _ignoreInterItDataDep flag is 
   *  set) and if the block has instructions, run the force-directed scheduler.
   * 
   * @param graph_BlockGraph 
   * @return success of scheduling
   */
  public boolean optimize(BlockGraph graph_BlockGraph) {
    boolean scheduledOk = true;
     
    for (Iterator vIt = graph_BlockGraph.getAllNodes().iterator(); 
             vIt.hasNext();) {
      BlockNode node_BlockNode = (BlockNode) vIt.next();
      //don't do FD scheduling on loops with recursive data connections:
      if((!node_BlockNode.getIsRecursive())||_ignoreInterItDataDep) 
	if(((ArrayList)(node_BlockNode.getInstructions())).size()>0)
          if(!(fDSchedule(node_BlockNode, GlobalOptions.chipDef)))
            scheduledOk = false;
    }
     
    return scheduledOk;
  }
  
  //force directed schedule: for more extensive comments and explanation of algorithm see
  //flowgraph/Schedule.java
  /** schedule the list of instructions on a given block using force-directed 
   *  scheduling (see Schedule.java to see the algorithm implmented) and order 
   *  that list so that the dotty file will be more pretty and understandable to
   *  look at.
   * 
   * @param node_BlockNode 
   * @param chipInfo 
   */
  boolean fDSchedule(BlockNode node_BlockNode, ChipDef chipInfo) {
    if(chipInfo.getOpList()==null) {
      throw new HardwareException("Could not create the Force Directed schedu" +
                                  "le, because the hardware analyzation did n" +
				  "ot complete successfully");
      //System.err.println("Could not create the Force Directed schedule, because the hardware analyzation did not complete successfully");
      //return false;
    }
     
     
    _scheduleList.put(node_BlockNode, new FDSchedule(node_BlockNode.getDepFlowGraph(), _packNicht));
     
    UseHash use_hash_tmp = node_BlockNode.getUseHash();
    DefHash def_hash_tmp = node_BlockNode.getDefHash();
     
    ArrayList list = node_BlockNode.getInstructions();
     
    //the ALAP and ASAP schedules must be calculated for generating the 
    //force-directed schedule
    ArrayList ASAPList = new ArrayList();
    for (Iterator it = ((ArrayList)list).iterator(); 
      it.hasNext(); ) {
      Instruction Instr = (Instruction)it.next();
       
      if(Instr == null) continue;
         
      Instruction Instrcopy = Instr.copySaveOps();
      ASAPList.add(Instrcopy);
    }
    
    ArrayList ALAPList = new ArrayList();
    for (Iterator it = ((ArrayList)list).iterator(); 
      it.hasNext(); ) {
      Instruction Instr = (Instruction)it.next();
      Instruction Instrcopy = Instr.copySaveOps();
      ALAPList.add(Instrcopy);
    }
     
    boolean result = false;
    ASAPSchedule aSAPSchedule = new ASAPSchedule(_packNicht);
    ALAPSchedule aLAPSchedule = new ALAPSchedule(_packNicht);
    boolean intermediateResult1 = 
                    aSAPSchedule.asapSchedule(ASAPList, _ignorePredsNicht, 
	        			      chipInfo,  
	        			      node_BlockNode.getDepFlowGraph());
    sort(ASAPList);
    //aSAPSchedule.loadIntoDataBase(ASAPList);
    chipInfo.completeInitialize(); 
     
    boolean intermediateResult2 = 
                    aLAPSchedule.alapSchedule(ALAPList, _ignorePredsNicht, 
	                                      chipInfo, 
					      node_BlockNode.getDepFlowGraph());
    sort(ALAPList);
    //aLAPSchedule.loadIntoDataBase(ALAPList);
    chipInfo.completeInitialize();
     
    if((intermediateResult1)&&(intermediateResult2)) {
      //I shuffle the list, because if all the loads and stores are at the top, after they 
      //have been ordered, there will be less space to best order the more important
      //operations like add, mul, and div
      //but perhaps random order is not the best????
      //try up to 10 different orderings before failing
      
      FDWindows windowMap = new FDWindows();
      //load initial instruction window sizes
      for (Iterator it = ((ArrayList)list).iterator(); 
          it.hasNext(); ) {
	Instruction instr = (Instruction)it.next();
	windowMap.loadWinsFromALAPnASAPScheds(instr, ASAPList, ALAPList);

      }  //end foreach
      
      //System.out.println("there are " + list.size() + " instructions");
      for(int i = 0; i<_maxAttemptCnt && (!(result)); i++) {  
        
        FDWindows windowMapTmp = (FDWindows)windowMap.clone();
	for (Iterator it = ((ArrayList)list).iterator(); 
          it.hasNext(); ) {
          Instruction instr = (Instruction)it.next();
          instr.setExecTime(-1);
	}
	
	
	Collections.shuffle(list);
        System.out.println("performing FD list");
	
	chipInfo.saveNode(node_BlockNode);
	result = ((FDSchedule)(_scheduleList.get(node_BlockNode)))
	                          .fD_Schedule(list, windowMapTmp, 
			                       _ignorePredsNicht, chipInfo);
        if(!(result)) {
	  chipInfo.initializeForOneNode(node_BlockNode);
	}
      }
      if(!(result)) {
        throw new ScheduleException("Error: Failed to create a Force-Directed" +
	                            " Schedule");
      }
       
      sort(list);
      ((Schedule)(_scheduleList.get(node_BlockNode))).loadIntoDataBase(list);
    }
    else {
      throw new ScheduleException("Force-Directed Scheduling did not start, b" +
        			  "ecause either ASAP or ALAP scheduling did " +
        			  "not complete successfully.");
    }
    return result;
  }

  public void unSchedule(ArrayList list) { 
    for (Iterator it = ((ArrayList)list).iterator(); 
      it.hasNext(); ) {
      Instruction instr = (Instruction)it.next();
      instr.setExecTime(-1);
    }
    
  }
   
  public String name() { 
    return "FDSchedulePass";
  }
   
  private void sort(ArrayList o_list) {
    class DoubleCompare implements Comparator {      
      public int compare(Object o1, Object o2) {        
        if (o1 instanceof Instruction             
        && o2 instanceof Instruction) {          
          Instruction p1 = (Instruction)o1;          
          Instruction p2 = (Instruction)o2;          
          if (p1.getExecTime() > p2.getExecTime()) {            
            return 1;          
            } else if (p1.getExecTime() < p2.getExecTime()) {            
            return -1;         
            } else {            
            return 0;          
          }        
          } else {          
          throw new ClassCastException("Not Instruction");        
        }      
      }    
    }        
    Collections.sort(o_list, new DoubleCompare());  
  }
}


/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.hardware.*;


/** the ALAP schedule pass...
 * 
 * @author Kris Peterson
 */
public class ALAPSchedulePass extends Pass implements GraphPass {
   
  //private Schedule _schedule;
  private HashMap _scheduleList;
  /** do not ignore predicates when scheduling predicated instructions
   */
  private boolean _ignorePredsNicht;
  /** ignore if there inter-iteration loop data dependencies and schedule anyway
   *  with ALAP.
   */
  private boolean _ignoreInterItDataDep;
  /** when this flag is set to true, operations that have latencies less than 
   *  one, will not be packed within the same cycle.
   */
  private boolean _packNicht;
   
  /** "@param pm
   */
  public ALAPSchedulePass(PassManager pm) {
    this(pm, GlobalOptions.doNotIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs);
    //_schedule = new Schedule();
    _scheduleList = new HashMap();
  }
  
  /** "@param pm
   * 
   * @param pm 
   * @param DntIgnorePreds 
   */
  public ALAPSchedulePass(PassManager pm, boolean dntIgnorePreds) {
    this(pm, dntIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs);
    _scheduleList = new HashMap();
  }
  
  public ALAPSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                          boolean ignoreDataDep) {
    this(pm, dntIgnorePreds, ignoreDataDep, GlobalOptions.doNotPackInstrucs);
    _scheduleList = new HashMap();
  }
  /** "@param pm
   * 
   * @param pm 
   * @param DntIgnorePreds 
   * @param ignoreDataDep 
   */
  public ALAPSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                          boolean ignoreDataDep, boolean packNicht) {
    super(pm);
    _ignorePredsNicht = dntIgnorePreds;
    _ignoreInterItDataDep = ignoreDataDep;
    _packNicht = packNicht;
    _scheduleList = new HashMap();
  }
  
  /** if the block is not a recursive loop and has some instructions and perform
   *  ALAP scheduling and return whether it was successful or not.
   * 
   * @param graph control flow graph
   * @return success of ALAP scheduling
   */
  public boolean optimize(BlockGraph graph) {
    //ALAP scheduling:
     
    boolean scheduledOk = true;
     
    for (Iterator vIt = graph.getAllNodes().iterator(); 
               vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      System.out.println("scheduling node " + node.getLabel());
      //don't do ALAP scheduling on loops with recursive data connections:
      if((!node.getIsRecursive())||_ignoreInterItDataDep) 
	if(((ArrayList)(node.getInstructions())).size()>0)
          if(!(aLAPSchedule(node, GlobalOptions.chipDef)))
            scheduledOk = false;
    }
     
    return scheduledOk;
  }
  
  /** This actually calls the ALAP scheduler routine written in Schedule.java 
   *  and then it sorts the list of instructions
   * 
   * @param node hyperblock to schedule
   * @param chipInfo chip information
   * @return success of scheduling
   */
  boolean aLAPSchedule(BlockNode node, ChipDef chipInfo) {
    if(chipInfo.getOpList()==null) {
      throw new HardwareException("could not make ALAP calculation, because t" +
                                  "he hardware analyzation did not complete " +
				  "successfully");
      //System.err.println("Could not create the ALAP schedule, because the hardware analyzation did not complete successfully");
      //return false;
    }
     
    _scheduleList.put(node, new ALAPSchedule(_packNicht));
    ArrayList list = node.getInstructions();
    boolean result =
         ((ALAPSchedule)(_scheduleList.get(node)))
	        .alapSchedule(list, _ignorePredsNicht, chipInfo, 
		                   node.getDepFlowGraph());
    //sort the list based on the clock tick for each execution:
    sort(list);
    return result;
  }
   
   
  public String name() { 
    return "ALAPSchedulePass";
  }
   
  /** sort instructions in hypberblock's list based on their assigned execute 
   *  time.
   * 
   * @param o_list 
   */
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


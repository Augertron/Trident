/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.hardware.*;


/** The ASAP scheduler pass.  If you want to make an ASAP schedule of the 
 *  design, use this pass.
 * 
 * @author Kris Peterson
 */
public class ASAPSchedulePass extends Pass implements GraphPass {
   
  private HashMap _scheduleList;
  /** don't ignore predicates when scheduling (default is to ignore them)
   */
  private boolean _ignorePredsNicht;
  /** ignore if there are inter-iteration loop dependencies and perform ASAP 
   *  scheduling on this block anyway.
   */
  private boolean _ignoreInterItDataDep;
  /** when this flag is set to true, operations that have latencies less than 
   *  one, will not be packed within the same cycle.
   */
  private boolean _packNicht;
   
  /** "@param pm
   */
  public ASAPSchedulePass(PassManager pm) {
    this(pm, GlobalOptions.doNotIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs);
    _scheduleList = new HashMap();
  }
  
  public ASAPSchedulePass(PassManager pm, boolean dntIgnorePreds) {
    this(pm, dntIgnorePreds, GlobalOptions.noModSched, 
         GlobalOptions.doNotPackInstrucs);
    _scheduleList = new HashMap();
  }

  public ASAPSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                          boolean ignoreDataDep) {
    this(pm, dntIgnorePreds, ignoreDataDep, GlobalOptions.doNotPackInstrucs);
    _scheduleList = new HashMap();
  }
  
  public ASAPSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                          boolean ignoreDataDep, boolean packNicht) {
    super(pm);
    _ignorePredsNicht = dntIgnorePreds;
    _ignoreInterItDataDep = ignoreDataDep;
    _packNicht = packNicht;
    _scheduleList = new HashMap();
  }
  
   /*public ASAPSchedulePass(PassManager pm, boolean dntIgnorePreds, 
                          boolean ignoreDataDep, boolean packNicht,
			  float cL) {
    super(pm);
    _ignorePredsNicht = dntIgnorePreds;
    _ignoreInterItDataDep = ignoreDataDep;
    _packNicht = packNicht;
    _scheduleList = new HashMap();
    Schedule.cycleLength = cL;
  }*/

 /** if this is not a recursive loop and there is more than 1 instruction in 
   *  this block, then perform ASAP scheduling.
   * 
   * @param graph 
   */
  public boolean optimize(BlockGraph graph) {
    boolean scheduledOk = true;
     
    for (Iterator vIt = graph.getAllNodes().iterator(); 
           vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      System.out.println("scheduling node " + node.getLabel());
      //don't do ASAP scheduling on loops with recursive data connections
      if((!node.getIsRecursive())||_ignoreInterItDataDep) 
	if(((ArrayList)(node.getInstructions())).size()>0)
          if(!(aSAPSchedule(node, GlobalOptions.chipDef)))
            scheduledOk = false;
    }
     
    return scheduledOk;
  }
  
  /** call the ASAP scheduling routine from Schedule.java and sort the list of 
   *  instructions so that they are in the order of the exec clock time.
   * 
   * @param node 
   * @param chipInfo 
   */
  boolean aSAPSchedule(BlockNode node, ChipDef chipInfo) {
    if(chipInfo.getOpList()==null) {
      throw new HardwareException("could not make ASAP calculation, because t" +
                                  "he hardware analyzation did not complete " +
				  "successfully");
      //System.err.println("Could not create the ASAP schedule, because the hardware analyzation did not complete successfully");
      //return false;
    }
     
    _scheduleList.put(node, new ASAPSchedule(_packNicht, node));
    ArrayList list = node.getInstructions();
    boolean result = ((ASAPSchedule)(_scheduleList.get(node)))
                           .asapSchedule(list, _ignorePredsNicht, chipInfo, 
					      node.getDepFlowGraph());
     
    ArrayList tmp_list = new ArrayList(list);
    //sort the list based on the clock tick for each execution:
    sort(list);
    return result;
  }
   
  public String name() { 
    return "ASAPSchedulePass";
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


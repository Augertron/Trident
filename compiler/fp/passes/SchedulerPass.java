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


/** This pass starts the schedulers.  I use one pass now, because there are 
 *  several places that scheduling is requested, and I wanted to make it so
 *  there was one general setup for all.
 * 
 * @author Kris Peterson
 */
public class SchedulerPass extends Pass implements GraphPass {

  private OperationSelection _opSel;
  
  //this constructor should never be called or there will be problems:
  public SchedulerPass(PassManager pm) {
    this(pm, null);
    System.err.println("Not passing in an operationSelection object will caus" +
                       "e problems!");
  }
  
  public SchedulerPass(PassManager pm, OperationSelection opSel) {
    super(pm);
    _opSel = opSel;
  }

  public boolean optimize(BlockGraph graph) {
     
    HashSet allNodes = new HashSet(graph.getAllNodes());
    for (Iterator vIt = allNodes.iterator(); 
             vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      
      //this class was written to handle the setup for all the schedulers
      //so that whether a scheduler is called from a pass, from
      //AnalyzeHardwareConstraints, the Modulo Scheduler, or any other place,
      //there can be one setup:
      MasterScheduler prelimSched = new MasterScheduler(node, _opSel, 
                                                        GlobalOptions.chipDef);
      prelimSched.schedule(graph);
    }
     
    return true;
  }

  public String name() { 
    return "SchedulerPass";
  }


}

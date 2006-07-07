/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.hardware.*;
import fp.*;


/** this pass is for calculating the MResII (minimum initiation interval based 
 *  on resources).
 * 
 * @author Kris Peterson
 */
public class CalcIIPass extends Pass implements GraphPass {
   
   
  public CalcIIPass(PassManager pm) {
    super(pm);
  }
  
  /** call calculateTheII from CalculateII.java to find MResII
   * 
   * @param graph_BlockGraph 
   */
  public boolean optimize(BlockGraph graph_BlockGraph) {
    for (Iterator vIt = graph_BlockGraph.getAllNodes().iterator(); 
          vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      CalculateII iICalc_CalculateII = new CalculateII();
      iICalc_CalculateII.calculateTheII(node, GlobalOptions.chipDef);
    }
     
    return true;
  }
  
   
  public String name() { 
    return "CalcIIPass";
  }
   
}


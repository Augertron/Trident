/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.hardware.*;


/** call the analyze hardware methods to decide on hardware sharing and allocate
 *  memory.
 * 
 * @author Kris Peterson
 */
public class AnalyzeLogicSpaceConstraintsPass extends Pass implements GraphPass {
   
  private boolean _conserveArea;
   
  /** "@param pm
   */
  public AnalyzeLogicSpaceConstraintsPass(PassManager pm) {
    this(pm, GlobalOptions.conserveArea);
  }
  public AnalyzeLogicSpaceConstraintsPass(PassManager pm, 
                                          boolean conserveArea) {
    super(pm);
    _conserveArea = conserveArea;
  }
  
  /** call analyzeHardware to allocate memory and decide if and which logic 
   *  devices need to be shared on the FPGA.
   * 
   * @param graph 
   */
  public boolean optimize(BlockGraph graph) {
    //AnalyzeHardareConstraints iICalc = new AnalyzeHardareConstraints();
     
    AnalyzeHrdWrConstraintsPass.iICalc.calcLogicSpaceReq(graph, 
                                                         GlobalOptions.chipDef, 
						         _conserveArea);
    return true;
  }
  
   
  public String name() { 
    return "AnalyzeLogicSpaceConstraintsPass";
  }
   
}


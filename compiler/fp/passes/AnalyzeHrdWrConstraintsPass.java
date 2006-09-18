/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.hardware.*;
import fp.passes.*;


/** call the analyze hardware methods to decide on hardware sharing and allocate
 *  memory.
 * 
 * @author Kris Peterson
 */
public class AnalyzeHrdWrConstraintsPass extends Pass implements GraphPass {
   
  private int _scheduleSelect;
  private boolean _ignoreDataDep;
  private boolean _conserveArea;
  private OperationSelection _opSel;
  public static AnalyzeHardareConstraints iICalc;
   
  public AnalyzeHrdWrConstraintsPass(PassManager pm) {
    this(pm, null); 
  }

  public AnalyzeHrdWrConstraintsPass(PassManager pm, OperationSelection opSel) {
    super(pm);

    _scheduleSelect = GlobalOptions.scheduleSelect;
    _ignoreDataDep = !GlobalOptions.modSched;
    _conserveArea = GlobalOptions.conserveArea;
    iICalc = new AnalyzeHardareConstraints();
    _opSel = opSel;
  }
  
  /** call analyzeHardware to allocate memory and decide if and which logic 
   *  devices need to be shared on the FPGA.
   * 
   * @param graph 
   */
  public boolean optimize(BlockGraph graph) {
    //AnalyzeHardareConstraints iICalc = new AnalyzeHardareConstraints();
     
    iICalc.analyzeHardware(graph, GlobalOptions.chipDef, _scheduleSelect, 
                           _ignoreDataDep,  _conserveArea, _opSel);
    return true;
  }
  
   
  public String name() { 
    return "AnalyzeHrdWrConstraintsPass";
  }
   
}


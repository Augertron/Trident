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
   
  /** "@param pm
   */
  public AnalyzeHrdWrConstraintsPass(PassManager pm) {
    this(pm, null, GlobalOptions.scheduleSelect, GlobalOptions.noModSched, 
         GlobalOptions.conserveArea);
  }
  public AnalyzeHrdWrConstraintsPass(PassManager pm, OperationSelection opSel) {
    this(pm, opSel, GlobalOptions.scheduleSelect, GlobalOptions.noModSched, 
         GlobalOptions.conserveArea);
  }
  public AnalyzeHrdWrConstraintsPass(PassManager pm, OperationSelection opSel, 
                                     int scheduleSelect) {
    this(pm, opSel, scheduleSelect, GlobalOptions.noModSched, 
         GlobalOptions.conserveArea);
  }
  public AnalyzeHrdWrConstraintsPass(PassManager pm, OperationSelection opSel, 
                                     int scheduleSelect, 
                                     boolean ignoreDataDep) {
    this(pm, opSel, scheduleSelect, ignoreDataDep, GlobalOptions.conserveArea);
  }
  public AnalyzeHrdWrConstraintsPass(PassManager pm, OperationSelection opSel, 
                                     int scheduleSelect, 
                                     boolean ignoreDataDep, 
				     boolean conserveArea) {
    super(pm);
    _scheduleSelect = scheduleSelect;
    _ignoreDataDep = ignoreDataDep;
    _conserveArea = conserveArea;
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


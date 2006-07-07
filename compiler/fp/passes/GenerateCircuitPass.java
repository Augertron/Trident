/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.synthesis.*;

public class GenerateCircuitPass extends Pass implements GraphPass {

  private CircuitSwitch _design;

  public GenerateCircuitPass(PassManager pm) {
    this(pm, "dot");
  }

  public GenerateCircuitPass(PassManager pm, String target) {
    super(pm);
    _design = new DesignCircuitGenerator(target, "trident_design");
  }

  public GenerateCircuitPass(PassManager pm, String target, String arch) {
    super(pm);
    _design = new TopLevelCircuitGenerator(target, arch);
  }

  public String name() { return "GenerateCircuitPass"; }

  public boolean optimize(BlockGraph graph) {
    _design.generate(graph);
    return true;
  }

}

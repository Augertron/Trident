/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.hardware.*;

  /**
   * This class locates all getelementptr instructions and converts them to 
   * instructions that calculate the address to be accessed.
   */
public class ConvertGepInst extends Pass implements GraphPass {
  private OperationSelection _opSel;

  public ConvertGepInst(PassManager pm, OperationSelection opSel) {
    super(pm);
    _opSel = opSel;
  }

  // Find each GEP instruction and convert it...
  public boolean optimize(BlockGraph graph) {
    ConvertGepInstructions convert = new ConvertGepInstructions(_opSel);
    convert.gepConversion(graph);
    return true;
  }

  public String name() {
    return "ConvertGepInst";
  }
}

/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.BlockGraph;
import fp.flowgraph.LoopDotGraph;

public class PrintVariables extends Pass implements GraphPass {

  /*
    The purpose of this pass is to print the variables
  */
  

  private String _name = null;
  static private int count = 0;


  public PrintVariables(PassManager pm) {
    super(pm);
  }

  public PrintVariables(PassManager pm, String s) {
    this(pm);
    _name = s;
  }

  public boolean optimize(BlockGraph graph) {
    String debug = "";
    String count_string = "";

    if (_name != null) {
      debug = _name;
      count_string = "_"+(count++);
    }

    graph.printVariables();

    return true;
  }

  public String name() { 
    return "PrintVariables";
  }


}


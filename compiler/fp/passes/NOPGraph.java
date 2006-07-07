/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.BlockGraph;

public class NOPGraph extends Pass implements GraphPass {

  /*
    The purpose of this pass is to do nothing.  However, it probably
    will collect some statistics ... maybe.  This gives an indication
    about how much the cost of starting the JVM is.
  */
  

  public NOPGraph(PassManager pm) {
    super(pm);
  }

  public boolean optimize(BlockGraph graph) {
    return true;
  }

  public String name() { 
    return "NOPGraph";
  }


}


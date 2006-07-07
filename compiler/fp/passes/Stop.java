/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.BlockGraph;

public class Stop extends Pass implements GraphPass {

  /*
    This is a debugging tool to ensure that nothing else happens after
    this pass.  This should not be committed as a required pass.
  */
  
  private int exit_code;

  public Stop(PassManager pm, int exit) {
    super(pm);
    exit_code = exit;
  }

  public Stop(PassManager pm) {
    this(pm, -1);
  }


  public boolean optimize(BlockGraph graph) {
    System.exit(exit_code);
    return true;
  }

  public String name() { 
    return "Stop";
  }


}


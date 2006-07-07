/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.BlockGraph;

public class SetNodeOrder extends Pass implements GraphPass {

  /*
    This pass recalculates the pre, post, reversse-post orders
    of the block graph.  This is necessary, if nodes have been
    eliminated through merging or other optimizations.

    It could be combined with other passes that recalculate 
    stuff as well -- like the loop stuff.
  */


  public SetNodeOrder(PassManager pm) {
    super(pm);
  }

  public boolean optimize(BlockGraph graph) {
    // this is not a very good name.
    graph.markEdges();
    graph.resetMarkers();

    return true;
  }


  public String name() { 
    return "SetNodeOrder";
  }

}



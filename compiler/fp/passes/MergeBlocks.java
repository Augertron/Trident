/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.BlockGraph;
import fp.flowgraph.BlockNode;
import fp.flowgraph.BlockEdge;
import fp.flowgraph.HyperBlockList;


public class MergeBlocks extends Pass implements GraphPass {

  private HyperBlockList _hyperBlocks;

  public MergeBlocks(PassManager pm, HyperBlockList hyperBlocks) {
    super(pm);
    _hyperBlocks = hyperBlocks;
  }

  /**
  This method does all the merging calculated by the CalcHyperBlocks
  pass.  It performs parallel and serial merges in the order that 
  they were calculated to occur.
  */
  public boolean optimize(BlockGraph graph) {
    
    while(_hyperBlocks.popMerge(graph)) {} //perform all saved merges
    return true;
  }

  public String name() { return "MergeBlocks"; }
}

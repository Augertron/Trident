/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.HyperBlockList;
import fp.flowgraph.BlockGraph;

public class CalcHyperBlocks extends Pass implements GraphPass {

  private HyperBlockList _hyperBlocks;
  public CalcHyperBlocks(PassManager pm, HyperBlockList hyperBlocks) {
    super(pm);
    _hyperBlocks = hyperBlocks;
  }

  public boolean optimize(BlockGraph graph) {
    //calculate merged blocks because of parallel merge:
    _hyperBlocks.genMergeBlockGroupsParallel(graph);
    //calculate merged blocks because of serial merge:
    _hyperBlocks.genMergeBlockGroupsSerial(graph);
    return true;
  }
  
  public String name() {
    return "CalcHyperBlocks";
  }



} 

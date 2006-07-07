/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.hardware.*;

public class AllocateArraysPass extends Pass implements GraphPass {

  public AllocateArraysPass(PassManager pm) {
    super(pm);
  }

  // Find each GEP instruction and allocate the associated array...
  public boolean optimize(BlockGraph graph) {
    AllocateArrays allocArrays = new AllocateArrays();
    allocArrays.allocateArrays(graph);
    return true;
  }
  

  public String name() {
    return "AllocateArraysPass"; 
  }

}

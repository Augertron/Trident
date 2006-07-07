/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.LinkedList;

import fp.flowgraph.BlockGraph;

public interface GraphPass {
  
  public boolean optimize(BlockGraph graph);

  public void setup();

  public String name();

  // for dependence creation ?
  public LinkedList requires(); 
}
    

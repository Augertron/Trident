/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.LinkedList;

import fp.flowgraph.BlockNode;

public interface BlockPass {
  
  public boolean optimize(BlockNode node);

  public void setup();

  public String name();

  // for dependence creation ?
  public LinkedList requires(); 
}

/*
 *
 @LICENSE@
 */


package  fp.flowgraph;

public interface LoopNode {

  public LoopNode loopParent();
  public void     setLoopParent(LoopNode node);
  public boolean  loopContains(LoopNode node);
  public void     addLoopNode(LoopNode node);
  public LoopNode loopEntry();
  public void     setLoopEntry(LoopNode node);

  public String   toLoopDot();

}

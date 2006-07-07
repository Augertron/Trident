/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class BlockEdge extends ControlFlowEdge {

  private BooleanEquation _predicate;

  public BooleanEquation getPredicate() { return _predicate; }
  public void setPredicate(BooleanEquation eq) { 
    _predicate = eq; 
  }

  public String toDot() {
    if (_predicate != null) {
      setDotAttribute("label", _predicate.toString());
    }
    return super.toDot();
  }




}

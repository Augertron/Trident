/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class UnknownOperand extends Operand {
  private String _origname;
  
  public UnknownOperand(String origname, String name, int assign) {
    _origname = origname;
    _name = name;
    _assignment = assign;
  }
 
  public String getOrigName() {
    return _origname;
  }

  public Operand copy() { 
    System.err.println("Warning:  Shouldn't be necessary to copy an UnknownOperand");
    return this;
  }

  public Operand getNext() {
    System.err.println("Warning:  Shouldn't be necessary to get next UnknownOperand");
    return this;
  }

}

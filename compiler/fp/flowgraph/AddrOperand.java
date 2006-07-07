/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class AddrOperand extends Operand {
  
  // there should be a way to get the next assignment if
  // you don't already have one.
  public AddrOperand(String name, int assign) {
    _name = name;
    _assignment = assign;
  }

  public Operand copy() { 
    return new AddrOperand(_name, _assignment);
  }

  public Operand getNext() {
    return Operand.nextAddr(_name);
  }

}

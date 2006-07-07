/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class PrimalOperand extends Operand {
  
  PrimalOperand(String name) {
    _name = name;
    _assignment = NOT_ASSIGNED;
  }

  public Operand copy() { 
    return new PrimalOperand(_name);
  }

  public Operand getNext() {
    return Operand.nextPrimal(_name);
  }
}

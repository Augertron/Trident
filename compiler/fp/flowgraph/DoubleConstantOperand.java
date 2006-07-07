/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class DoubleConstantOperand extends ConstantOperand {
  
  private double _value;

  public DoubleConstantOperand(double value) {
    super("const"+value);
    _value = value;
  }

  public Operand copy() { 
    return new DoubleConstantOperand(_value);
  }

  public Operand getNext() {
    return copy();
  }

  public double getValue() { return _value; }

  public boolean isFloatingPointOperand() {
    return true;
  }

}

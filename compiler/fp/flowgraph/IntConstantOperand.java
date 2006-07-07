/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class IntConstantOperand extends ConstantOperand {
  
  private int _value;

  public IntConstantOperand(int value) {
    super("const"+value);
    _value = value;
  }

  public Operand copy() { 
    return new IntConstantOperand(_value);
  }

  public Operand getNext() {
    return copy();
  }

  public int getValue() { return _value; }

  public boolean isIntegerOperand() {
    return true;
  }

}

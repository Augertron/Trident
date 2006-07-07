/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class LongConstantOperand extends ConstantOperand {
  
  private long _value;

  public LongConstantOperand(long value) {
    super("const"+value);
    _value = value;
  }

  public Operand copy() { 
    return new LongConstantOperand(_value);
  }

  public Operand getNext() {
    return copy();
  }

  public long getValue() { return _value; }

  public boolean isIntegerOperand() {
    return true;
  }

}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class FloatConstantOperand extends ConstantOperand {
  
  private float _value;

  public FloatConstantOperand(float value) {
    super("const"+value);
    _value = value;
  }

  public Operand copy() { 
    return new FloatConstantOperand(_value);
  }

  public Operand getNext() {
    return copy();
  }

  public float getValue() { return _value; }

  public boolean isFloatingPointOperand() {
    return true;
  }
}

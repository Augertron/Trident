/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;


public abstract class Constant extends Node {

  public static final int INT = 0;
  public static final int FLOAT = 1;
  public static final int DOUBLE = 2;

  private int _type;
  private String _value;
  private int _width;

  public Constant(Circuit parent, String name, String value, int width, 
		  int type) {
    super(parent, name);
    _value = value;
    _width = width;
    _type  = type;
  }

  public Constant(Circuit parent, String value, int width, int type) {
    this(parent, "constant", value, width, type);
  }

  /* CHANGE: are the following needed? */
  /*
  public Constant(Circuit parent, String name, int value, int width) {
    this(parent, name, Integer.toHexString(value), width);
  }
  public Constant(Circuit parent, String name, float value, int width) {
    this(parent, name, 
	 Integer.toHexString(Float.floatToRawIntBits(value)), width);
  }
  public Constant(Circuit parent, String name, double value, int width) {
    this(parent, name, 
	 Long.toHexString(Double.doubleToRawLongBits(value)), width);
  }
  */

  // Return the hex value...
  public String getValue() { return _value; }

  public int getType() { return _type; }

  public int getWidth() { return _width; }

  public void setWidth(int i) { _width = i; }

  /**
   * The following methods are for accessing the updated String-type "_value"
   * private variable.
   */

  public int getValueInt() { 
    return Integer.parseInt(_value, 16); 
  }

  public float getValueFloat() {
    return Float.intBitsToFloat(Integer.parseInt(_value,16));
  }
  public double getValueDouble() {
    return Double.longBitsToDouble(Long.parseLong(_value,16));
  }

  /** CHANGE: are these needed?

  public void setIntValue(int value) { 
    _value = Integer.toHexString(value); 
  }
  public void setFloatValue(float value) {
    _value = Integer.toHexString(Float.floatToRawIntBits(value));
  }
  public void setDoubleValue(double value) {
    _value = Long.toHexString(Double.doubleToRawLongBits(value));
  }
  */
}

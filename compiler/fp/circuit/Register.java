/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

public abstract class Register extends Node {


  private String _value;
  private int    _width;

  

  public Register(Circuit parent, String name, int width, String value) {
    super(parent, name);
    
    // these are important
    _value = value;
    _width = width;

  }

  

  public Register(Circuit parent, int width, String value) {
    this(parent, "dff", width, value);
  }

  public int getWidth() { return _width; }

  protected String getValue() { return _value; }
  
  public int getValueInt() { return Integer.parseInt(_value, 16); }

  public float getValueFloat() { 
    return Float.intBitsToFloat(Integer.parseInt(_value, 16));
  }
  public double getValueDouble() {
    return Double.longBitsToDouble(Long.parseLong(_value, 16));
  }
  

}

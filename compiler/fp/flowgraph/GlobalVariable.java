/*
 *
 @LICENSE@
 */


package fp.flowgraph;
import fp.util.ArrayToString;

public class GlobalVariable extends Variable{

  private Object _initialValue;

  public GlobalVariable(Operand op, Type ty, Object value) {
    super(op, ty, false);
    _initialValue = value;
  }
  
  public Object getInitialValue() {
    return _initialValue;
  }

  public String toString() {
    String s = super.toString();
    String val = "null";
  
    // initial values can be arrays or primitive types 
    if (_initialValue != null) {
      if (_initialValue.getClass().isArray()) {
	val = ArrayToString.get(_initialValue);
      } else val = _initialValue.toString();
    }
    return (s + "=" + val);
  }

}
  

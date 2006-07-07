/*
 *
 @LICENSE@ 
 */ 

package fp.util;

public class BooleanOp {
  private Bool _op;
  private boolean _sense;

  public BooleanOp(Bool op, boolean sense) {
    _op = op;
    _sense = sense;
  }

  public Bool getOp() { return _op; }
  public boolean getSense() { return _sense; }

  public String toString() {
    String output;
    if (_sense) 
      output = _op.getBoolName();
    else
      output = "~"+_op.getBoolName();
    return output;
  }

  
}


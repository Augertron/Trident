/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public class Variable {

  private Operand _operand;
  private Type _type;
  private boolean _external;
  

  int read_count;
  int write_count;

  public Variable(Operand op, Type ty, boolean external) {
    _operand = op;
    _type = ty;
    _external = external;
  }
  
  
  public Operand getOperand() { return _operand; }

  public Type getType() { return _type; }

  public boolean isExternal() { return _external; }


  public int getReadCount() { return read_count; }
  public void setReadCount(int i) { read_count = i; }

  public int getWriteCount() { return write_count; } 
  public void setWriteCount(int i) { write_count = i; }


  public String toString() {
    StringBuffer buf = new StringBuffer();

    buf.append(_type.toString());
    buf.append(" ");
    buf.append(_operand.toString());
    return buf.toString();
  }

}
  

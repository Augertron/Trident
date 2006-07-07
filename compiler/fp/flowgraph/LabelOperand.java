/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class LabelOperand extends Operand {
  
  LabelOperand(String name, int assign) {
    _name = name;
    _assignment = assign;
  }

  LabelOperand(String name) {
    this(name, NOT_ASSIGNED);
  }

  public Operand copy() {
    return new LabelOperand(_name, _assignment);
  }

  public Operand getNext() {
    return Operand.nextLabel(_name);
  }
  
  public String getName() {
    return _name;
  }
  
  public String toString() {
    StringBuffer sbuf = new StringBuffer("label ");
    sbuf.append(super.toString());
    return sbuf.toString();
  }

}

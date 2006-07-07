/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.Bool;

public final class BooleanOperand extends Operand implements Bool {

  public static final BooleanOperand TRUE = new BooleanOperand("TRUE", -1);
  public static final BooleanOperand FALSE = new BooleanOperand("FALSE", -1);
  
  BooleanOperand(String name, int assignment) {
    _name = name;
    _assignment = assignment;
  }

  public Operand copy() { 
    return new BooleanOperand(_name, _assignment); 
  }

  public Operand getNext() {
    return Operand.nextBoolean(_name);
  }

  public String getBoolName() { 
    return toString();
  }
}

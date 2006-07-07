/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class TypeOperand extends Operand {
  
  // I am not sure if this is abuse or not.
  public TypeOperand(Type type) {
    _type = type;
    _name = type.toString();
    _assignment = NOT_ASSIGNED;
  }

  public Operand copy() { 
    return new TypeOperand(_type);
  }

  public Operand getNext() {
    return Operand.nextType(_type);
  }
    
}

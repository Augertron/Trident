/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Store extends InstructionFormat {

  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

   public static boolean conforms(Operator op) {
    return op.format == Store_format;
  }

    
  public static Operand getDestination(Instruction i) {
    return i.getOperand(0);
  }

  public static void setDestination(Instruction i, Operand result) {
    i.putOperand(0, result);
  }

  public static boolean hasDestination(Instruction i) {
    return i.getOperand(0) != null;
  }

  public static Operand getValue(Instruction i) {
    return i.getOperand(1);
  }

  public static void setValue(Instruction i, Operand source) {
    i.putOperand(1, source);
  }

  public static boolean hasValue(Instruction i) {
    return i.getOperand(1) != null;
  }

  public static Instruction create(Operator o, Type type,
				   Operand destination,
				   Operand value) {
    return Store.create(o, type, destination, value, null);
  }

  public static Instruction create(Operator o, Type type,
				   Operand destination,
				   Operand value, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, destination);
    i.putOperand(1, value);
    return i;
  }
 
  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasDestination(i)) {
      throw new IllegalOperand("Store instruction does not have a destination operand.");
    }
    if (!hasValue(i)) {
      throw new IllegalOperand("Store instruction does not have a source operand.");
    }
    if (!(getDestination(i).isPrimal() || getDestination(i).isAddr())) {
      throw new IllegalOperand("Store instruction destination operand " + getDestination(i).getName() + " is not primal or address operand.");
    }
    if (!getValue(i).isFirstClassOperand()) {
      throw new IllegalOperand("Store instruction source operand " + getValue(i).getName() + " must be first class operand.");
    }
  }

}

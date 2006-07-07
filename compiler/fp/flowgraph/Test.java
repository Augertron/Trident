/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Test extends Binary {

  // this should not need to be here.
  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Test_format;
  }
  
  public static Operand getResult(Instruction i) {
    return i.getOperand(0);
  }

  public static void setResult(Instruction i, BooleanOperand result) {
    i.putOperand(0, result);
  }

  public static int indexOfResult(Instruction i) { return 0; }

  public static boolean hasResult(Instruction i) {
    return i.getOperand(0) != null;
  }

  public static Instruction create(Operator o, Type type, 
				   BooleanOperand result,
				   Operand val1, Operand val2) {
    return Test.create(o, type, result, val1, val2, null);
  }

  public static Instruction create(Operator o, Type type, 
				   BooleanOperand result,
				   Operand val1, Operand val2,
				   BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, result);
    i.putOperand(1, val1);
    i.putOperand(2, val2);
    return i;
  }
 
  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasResult(i)) {
      throw new IllegalOperand("Setcc instruction does not have result operand.");
    }
    if (!hasVal1(i)) {
      throw new IllegalOperand("Setcc instruction does not have first value operand.");
    }
    if (!hasVal2(i)) {
      throw new IllegalOperand("Setcc instruction does not have second value operand.");
    }
    if (!getResult(i).isBoolean()) {
      throw new IllegalOperand("Setcc binary instruction result must be boolean operand.");
    }
    if (!getVal1(i).isFirstClassOperand()) {
      throw new IllegalOperand("Setcc binary instruction first value must be first class or block operand.");
    }
    if (!getVal2(i).isFirstClassOperand()) {
      throw new IllegalOperand("Setcc binary instruction second value must be first class or block operand.");
    }
  }
}

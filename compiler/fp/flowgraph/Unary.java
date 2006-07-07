/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Unary extends InstructionFormat {
  
  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Unary_format;
  }
  
  public static Operand getResult(Instruction i) {
    return i.getOperand(0);
  }

  public static void setResult(Instruction i, Operand result) {
    i.putOperand(0, result);
  }

  public static int indexOfResult(Instruction i) { return 0; }

  public static boolean hasResult(Instruction i) {
    return i.getOperand(0) != null;
  }


  public static Operand getVal(Instruction i) {
    return i.getOperand(1);
  }

  public static void setVal(Instruction i, Operand result) {
    i.putOperand(1, result);
  }

  public static boolean hasVal(Instruction i) {
    return i.getOperand(1) != null;
  }

  
  public static Instruction create(Operator o, Type type, 
				   Operand result, Operand val) {
    return Unary.create(o, type, result, val, null);
  }

  public static Instruction create(Operator o, Type type, Operand result,
				   Operand val, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, result);
    i.putOperand(1, val);
    return i;
  }
  


}

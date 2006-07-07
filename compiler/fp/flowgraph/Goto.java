/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Goto extends InstructionFormat {
  
  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Goto_format;
  }
  
  public static LabelOperand getTarget(Instruction i) {
    return (LabelOperand)i.getOperand(0);
  }

  public static void setTarget(Instruction i, LabelOperand result) {
    i.putOperand(0, result);
  }

  public static boolean hasTarget(Instruction i) {
    return i.getOperand(0) != null;
  }

  public static Instruction create(Operator o, LabelOperand val) {
    return Goto.create(o, val, null);
  }

  public static Instruction create(Operator o, LabelOperand val, BooleanEquation eq) {
    Instruction i = new Instruction(o, Type.Label, eq);
    i.putOperand(0, val);
    return i;
  }

  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasTarget(i)) {
      throw new IllegalOperand("Goto instruction does not have target operand.");
    }
    if (!i.getOperand(0).isLabel()) {
      throw new IllegalOperand("Goto instruction target operand is not a label.");
    }
  }

}

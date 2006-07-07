/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Return extends InstructionFormat {

  public static boolean conforms(Instruction i) {
    return conforms(i.operator());
  }
  
  public static boolean conforms(Operator op) {
    return op.format == Return_format;
  }
  
  public static Operand getVal(Instruction i) {
    return i.getOperand(0);
  }

  public static void setVal(Instruction i, Operand result) {
    i.putOperand(0, result);
  }

  public static boolean hasVal(Instruction i) {
    return i.getOperand(0) != null;
  }

  public static Instruction create(Operator o, Type type, Operand val) {
    return Return.create(o, type, val, null);
  }
  
  public static Instruction create(Operator o, Type type, Operand val,
				   BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, val);
    return i;
  }

  // Include this as well??
  public static Instruction create(Operator o) {
    return Return.create(o, Type.Void, null, null);
  }

  // this is possibly more strange return nothing, conditionally?
  public static Instruction create(Operator o, BooleanEquation eq) {
    return Return.create(o, Type.Void, null, eq);
  }

  public static void verify(Instruction i) throws IllegalOperand {
    if (hasVal(i)) {
      if (!getVal(i).isFirstClassOperand()) {
	throw new IllegalOperand("Return instruction value operand is not a first class operand.");
      }
    }
  }

}

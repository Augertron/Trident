/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Nullary extends InstructionFormat {

  public static boolean conforms(Instruction i) {
    return conforms(i.operator());
  }

   public static boolean conforms(Operator op) {
    return op.format == Nullary_format;
  }

  public static Instruction create(Operator o, Type type) {
    return Nullary.create(o, type, null);
  }
  
  public static Instruction create(Operator o, Type type, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    return i;
  }



}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public abstract class InstructionFormat {

  public static final byte Unassigned_format = 0;
  public static final byte Nullary_format = 1;
  public static final byte Return_format = 2;
  public static final byte Goto_format = 3;
  public static final byte Branch_format = 4;
  public static final byte Switch_format = 5;
  public static final byte Binary_format = 6;
  public static final byte Test_format = 7;
  public static final byte Shift_format = 8;
  public static final byte Malloc_format = 9;
  public static final byte Load_format = 10;
  public static final byte Store_format = 11;
  public static final byte Phi_format = 12;
  public static final byte Cast_format = 13;
  public static final byte Select_format = 14;
  public static final byte Unary_format = 15;
  public static final byte Getelementptr_format = 16;
  public static final byte ALoad_format = 17;
  public static final byte AStore_format = 18;
  public static final byte Call_format = 19;

  public static final int Unassigned_traits = Operator.none;
  public static final int Nullary_traits = Operator.none;
  public static final int Return_traits = Operator.terminator;
  public static final int Goto_traits = Operator.terminator;
  public static final int Branch_traits = Operator.terminator;
  public static final int Switch_traits = Operator.terminator;
  public static final int Binary_traits = Operator.none;
  public static final int Test_traits = Operator.none;
  public static final int Shift_traits = Operator.none;
  public static final int Malloc_traits = Operator.memory;
  public static final int Load_traits = Operator.memory;
  public static final int Store_traits = Operator.memory;
  public static final int Phi_traits = Operator.var_uses;
  public static final int Cast_traits = Operator.none;
  public static final int Select_traits = Operator.none;
  public static final int Unary_traits = Operator.none;
  public static final int Getelementptr_traits = Operator.var_uses;
  public static final int ALoad_traits = Operator.memory;
  public static final int AStore_traits = Operator.memory;
  public static final int Call_traits = Operator.var_uses;

}

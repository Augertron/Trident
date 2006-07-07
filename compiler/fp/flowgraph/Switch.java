/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;

import fp.util.BooleanEquation;

public class Switch extends InstructionFormat {

  private static final int getValue(int i) { return 2*i+2; }
  private static final int getLabel(int i) { return 2*i+3; }


  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Switch_format;
  }
  

  public static Operand getTest(Instruction i) {
    return i.getOperand(0);
  }

  public static void setTest(Instruction i, Operand value) {
    i.putOperand(0, value);
  }

  public static boolean hasTest(Instruction i) {
    return i.getOperand(0) != null;
  }


  public static LabelOperand getDefault(Instruction i) {
    return (LabelOperand)i.getOperand(1);
  }

  public static void setDefault(Instruction i, LabelOperand label) {
    i.putOperand(1, label);
  }

  public static boolean hasDefault(Instruction i) {
     return i.getOperand(1) != null;
  }


  public static LabelOperand getCaseLabel(Instruction i, int index) {
    return (LabelOperand)i.getOperand(getLabel(index));
  }

  public static ConstantOperand getCaseValue(Instruction i, int index) {
    return (ConstantOperand)i.getOperand(getValue(index));
  }

  public static void setCase(Instruction i, int index, ConstantOperand value, LabelOperand label) {
    i.putOperand(getValue(index), value);
    i.putOperand(getLabel(index), label);
  }

  public static boolean hasCase(Instruction i, int index) {
    return i.getOperand(getValue(index)) != null;
  }


  public static Instruction create(Operator o, Type type, Operand variable, LabelOperand def,
				   LinkedList cases) {
    return Switch.create(o, type, variable, def, cases, null);
  }

  public static Instruction create(Operator o, Type type, Operand variable, LabelOperand def,
                                   LinkedList cases, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, variable);
    i.putOperand(1, def);

    if (cases != null) {
      i.resizeNumberOfOperands( cases.size() + 5 );
      
      int index = 0;
      
      for(ListIterator iter = cases.listIterator(); iter.hasNext(); ) {
	ConstantOperand value = (ConstantOperand)iter.next();
	LabelOperand label = (LabelOperand)iter.next();
	i.putOperand(getValue(index), value);
	i.putOperand(getLabel(index), label);
	index++;
      }
    }
    return i;
  }

  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasTest(i)) {
      throw new IllegalOperand("Switch instruction does not have a test operand.");
    }
    if (!getTest(i).isIntegerOperand()) {
	throw new IllegalOperand("Switch instruction test operand is not integer operand.");
      }
    if (!hasDefault(i)) {
      throw new IllegalOperand("Switch instruction does not have a default label operand.");
    }
    if (!getDefault(i).isLabel()) {
      throw new IllegalOperand("Switch instruction default is not a label operand.");
    }

    int index = 0;
    while (hasCase(i, index)) {
      Operand value = getCaseValue(i, index);
      Operand label = getCaseLabel(i, index);
      if ((label==null) || (value==null)) {
	throw new IllegalOperand("Switch instruction missing operand in its value/label pairs");
      }
      if (!label.isLabel()) {
	throw new IllegalOperand("Switch instruction case label operand " + label.getName() + " is not label.");
      }
      if (!value.isIntegerOperand()) {
	throw new IllegalOperand("Switch instruction case value operand " + value.getName() + " is not integer operand.");
      }
      index++;
    }
  }

}



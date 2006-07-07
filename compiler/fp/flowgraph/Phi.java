/*
 *
 @LICENSE@
 */



package fp.flowgraph;

import java.util.*;

import fp.util.BooleanEquation;

public class Phi extends InstructionFormat {

  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Phi_format;
  }
  
  // handle Results
  public static Operand getResult(Instruction i) {
    return i.getOperand(0);
  }

  public static void setResult(Instruction i, Operand result) {
    i.putOperand(0, result);
  }

  // is this necessary?
  public static int indexOfResult(Instruction i) { return 0; }

  public static boolean hasResult(Instruction i) {
    return i.getOperand(0) != null;
  }


  public static LabelOperand getValLabel(Instruction i, int index) {
    return (LabelOperand)i.getOperand(2*(index+1) );
  }

  public static Operand getValOperand(Instruction i, int index) {
    return i.getOperand(2*index + 1);
  }

  public static void setVal(Instruction i, int index, Operand value, 
			    Operand label) {
    i.putOperand(2*index + 1, value);
    i.putOperand(2*(index + 1), label);
  }

  public static boolean hasVal(Instruction i, int index) {
    return i.getOperand(2*(index+1)) != null;
  }

  public static Instruction create(Operator o, Type type, Operand result,
				   LinkedList values) {
    return Phi.create(o, type, result, values, null);
  }

  public static Instruction create(Operator o, Type type, Operand result,
                                   LinkedList values, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, result);
    if (values != null) {
      i.resizeNumberOfOperands( values.size() + 5 );
      
      int index = 0;
      
      for(ListIterator iter = values.listIterator(); iter.hasNext(); ) {
	Operand value = (Operand)iter.next();
	LabelOperand label = (LabelOperand)iter.next();
	i.putOperand(2*index + 1, value);
	i.putOperand(2*(index + 1), label);
	index++;
      }
    }
    return i;
  }

  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasResult(i)) {
      throw new IllegalOperand("Phi instruction does not have a result operand.");
    }

    int index = 0;
    while (hasVal(i, index)) {
      Operand value = getValOperand(i, index);
      Operand label = getValLabel(i, index);
      if ((label==null) || (value==null)) {
	throw new IllegalOperand("Phi instruction missing operand in its value/label pairs");
      }
      if (!label.isLabel()) {
	throw new IllegalOperand("Phi instruction label operand " + label.getName() + " is not label.");
      }
      if (!value.isFirstClassOperand()) {
	throw new IllegalOperand("Phi instruction value operand " + value.getName() + " is not first class operand.");
      }
      index++;
    }
  }
											  
}

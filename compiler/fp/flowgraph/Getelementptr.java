/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;

import fp.util.BooleanEquation;

public class Getelementptr extends InstructionFormat {

  public static boolean conforms(Instruction i) {
    return conforms(i.operator);
  }

  public static boolean conforms(Operator op) {
    return op.format == Getelementptr_format;
  }
  
  // handle Results
  public static Operand getResult(Instruction i) {
    return i.getOperand(0);
  }

  public static Operand getArrayVar(Instruction i) {
    return i.getOperand(1);
  }
 
 public static void setResult(Instruction i, Operand result) {
    i.putOperand(0, result);
  }

  // is this necessary?
  public static int indexOfResult(Instruction i) { return 0; }

  public static boolean hasResult(Instruction i) {
    return i.getOperand(0) != null;
  }


  public static TypeOperand getValType(Instruction i, int index) {
    return (TypeOperand)(i.getOperand(2*index+2));
  }

  public static Operand getValOperand(Instruction i, int index) {
    return i.getOperand(2*(index + 1) + 1);
  }

  public static void setVal(Instruction i, int index, Type type, Operand value) {
    i.putOperand(2*index + 2, new TypeOperand(type));
    i.putOperand(2*(index + 1) + 1, value);
  }

  public static boolean hasVal(Instruction i, int index) {
    return i.getOperand(2*(index+1)+ 1) != null;
  }

  public static Instruction create(Operator o, Operand result, Type type, Operand ptr,
				   LinkedList values) {
    return Getelementptr.create(o, result, type, ptr, values, null);
  }

  public static Instruction create(Operator o, Operand result, Type type, Operand ptr,
                                   LinkedList values, BooleanEquation eq) {
    Instruction i = new Instruction(o, type, eq);
    i.putOperand(0, result);
    i.putOperand(1, ptr);
    if (values != null) {
      i.resizeNumberOfOperands( values.size() + 5 );
      
      int index = 0;
      
      for(ListIterator iter = values.listIterator(); iter.hasNext(); ) {
	TypeOperand typeOp = (TypeOperand)iter.next();
	Operand value = (Operand)iter.next();
	i.putOperand(2*index + 2, typeOp);
	i.putOperand(2*(index + 1) + 1, value );
	index++;
      }
    }
    return i;
  }

  public static void verify(Instruction i) throws IllegalOperand {
    if (!hasResult(i)) {
      throw new IllegalOperand("Getelementptr instruction does not have a result operand.");
    }
    // check for ptr here too

    int index = 0;
    while (hasVal(i, index)) {
      Operand value = getValOperand(i, index);
      TypeOperand typeOp = getValType(i, index);
      if ((typeOp==null) || (value==null)) {
	throw new IllegalOperand("Getelementptr instruction missing operand in its type/value pairs");
      }
      if (!value.isFirstClassOperand()) {
	throw new IllegalOperand("Getelementptr instruction value operand " + value.getName() + " is not first class operand.");
      }
      index++;
    }
  }
											  
}

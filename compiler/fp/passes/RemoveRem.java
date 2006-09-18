/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.*;
import fp.util.Bit;
import fp.util.BooleanEquation;
import java.util.*;

public class RemoveRem extends Pass implements BlockPass {

  /*
  This pass replaces the rem instruction (an instruction that calculates
  the remainder after division) with other instructions that we can
  implement in hardware.  Since only constant division is supported, we
  only support rem for division by a constant.
  
  (much of this was copied and changed from ConvertConstantMultToShiftTree
  pass)
  */
  
  public RemoveRem(PassManager pm) {
    super(pm);
  }


  public boolean optimize(BlockNode node) {
    
    ArrayList list = node.getInstructions();
    HashMap subsMap = new HashMap();
    ArrayList instRemovalList = new ArrayList();
    ArrayList instAdditionList = new ArrayList();

    for(ListIterator iter = list.listIterator(); iter.hasNext(); ) {
      Instruction inst = (Instruction) iter.next(); 
      char op = inst.operator.opcode;

      // check for integer multiplication or division
      if ((op == Operators.REM_opcode) && inst.type().isInteger()){
	Operand operandA = Binary.getVal1(inst);
	Operand operandB = Binary.getVal2(inst);

	boolean constantA = operandA.isConstant();
	boolean constantB = operandB.isConstant();

	// check for a constant operand	
	if (constantA || constantB) {
	  if (constantA && constantB) {
	    // Easy. -- done somewhere else according to comment in 
	    //"ConvertConstantMultToShiftTree"
	    continue;
	  } 
	  else if (constantA) {
	    // constantA only -- this is also not so simple it is Constant/Variable.
	    // In this case, what can we do?
	    continue;
	  } 
	  else {
	    // constantB only
	    if (operandB.isIntConstant()) {
	      int value = ((IntConstantOperand)operandB).getValue();
	      int shiftCnt = 32-(new Double(Math.floor(Math.log(value)/Math.log(2))).intValue());
	      Operand constOperand = Operand.newIntConstant(shiftCnt);
	      Operand constZero = Operand.newIntConstant(0);
	      instRemovalList.add(inst);
	      Operand shLResult = Operand.newBlock("remTmp");
	      instAdditionList.add(Binary.create(Operators.SHL, Type.Int, shLResult, operandA,
	                           constOperand, new BooleanEquation(true))); 
	      BooleanOperand pred = Operand.newBoolean("remPred");
	      instAdditionList.add(Binary.create(Operators.SETLT, Type.Int, pred, operandA,
	                           constZero, new BooleanEquation(true))); 
	      Operand shRResult = Operand.newBlock("res2");
	      instAdditionList.add(Binary.create(Operators.SHR, Type.Int, shRResult, shLResult,
	                           constOperand, new BooleanEquation(true))); 
	      Operand negatedRes = Operand.newBlock("res1");

	      instAdditionList.add(Binary.create(Operators.SUB, Type.Int, negatedRes, constZero, 
						 shRResult, new BooleanEquation(true))); 
	      // jt -- this is a hack to prevent having to build an invert integer operation.
	      //
	      //instAdditionList.add(Unary.create(Operators.INV, Type.Int, negatedRes, shRResult,
	      //                     new BooleanEquation(true))); 
	      instAdditionList.add(Select.create(Operators.SELECT, Type.Int, Binary.getResult(inst), 
	      				         pred, negatedRes, shRResult, new BooleanEquation(true)));
	      
	    }
	  }
	}
      }
    }
    
    // remove instructions
    for(Iterator iter = instRemovalList.iterator(); iter.hasNext(); ) {
      Instruction inst = (Instruction) iter.next(); 
      node.removeInstruction(inst);
    }

    // add instructions
    node.addInstructions(instAdditionList);
    return true;
  }

  public String name() { 
    return "RemoveRem";
  }


}


/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.GlobalOptions;
import fp.flowgraph.*;
import fp.util.BooleanEquation;
import fp.util.UseHash;


public class OperationSelection extends Pass implements BlockPass, Operators {
  

  public OperationSelection(PassManager pm) {
    super(pm);
  }

  public String name() { return "OperationSelection"; }

  /*
    Some discussion:

    I don't think that it makes sense in general to have "library" versions of 
    aload and astore.  These depend on two different things: 1) the target board
    in question and 2) whether we should map a particular array to block ram or
    to external ram.

  */


  public boolean optimize(BlockNode node) {
    
    ArrayList list = node.getInstructions();

    for(Iterator iter = list.iterator(); iter.hasNext(); ) {
      Instruction instruction = (Instruction)iter.next();
      select(instruction);
    }
    return true;
  }
	

  // need to fix the NOPs and make them exceptions...

  public void select(Instruction instruction) {
    if(ALoad.conforms(instruction)) {
      if(GlobalOptions.chipDef.chooseLoadOp(instruction)==null)
        instruction.operator = AAA_ALOAD;
      else
        instruction.operator = GlobalOptions.chipDef.chooseLoadOp(instruction);
    }
    if(AStore.conforms(instruction)) {
      if(GlobalOptions.chipDef.chooseStoreOp(instruction)==null)
        instruction.operator = AAA_ASTORE;
      else
        instruction.operator = GlobalOptions.chipDef.chooseStoreOp(instruction);
    }
    switch(instruction.operator.opcode) {
      // type independant.
    case ALOAD_opcode:
      /* this is only for block ram array load and not for 
	 external rams.  External loads will need to be in their
	 actual fpga board library (e.g., XD1_ALOAD or OSIRIS_ALOAD ... )
      */
      //if(GlobalOptions.hardwareName == GlobalOptions.XD1_HW)
	//instruction.operator = XD1_ALOAD;
      //else
	//instruction.operator = AAA_ALOAD;
	if(GlobalOptions.chipDef.chooseLoadOp(instruction)==null)
	  instruction.operator = AAA_ALOAD;
        else
	  instruction.operator = GlobalOptions.chipDef.chooseLoadOp(instruction);
      break;
    case ASTORE_opcode:
      //if(GlobalOptions.hardwareName == GlobalOptions.XD1_HW)
	//instruction.operator = XD1_ASTORE;
      //else
	//instruction.operator = AAA_ASTORE;
	if(GlobalOptions.chipDef.chooseStoreOp(instruction)==null)
	  instruction.operator = AAA_ASTORE;
	else
	  instruction.operator = GlobalOptions.chipDef.chooseStoreOp(instruction);
      break;
    case LOAD_opcode:
      instruction.operator = AAA_LOAD;
      break;
    case STORE_opcode:
      instruction.operator = AAA_STORE;
      break;
    case SELECT_opcode:
      instruction.operator = AAA_SELECT;
      break;
    case AND_opcode:
      instruction.operator = AAA_AND;
      break;
    case OR_opcode:
      instruction.operator = AAA_OR;
      break;
    case XOR_opcode:
      instruction.operator = AAA_XOR;
      break;
    case NOT_opcode:
      instruction.operator = AAA_NOT;
      break;
    case SETEQ_opcode:
      instruction.operator = AAA_SETEQ;
      break;
    case SETNE_opcode:
      instruction.operator = AAA_SETNE;
      break;
    case SETLT_opcode:
      instruction.operator = AAA_SETLT;
      break;
    case SETGT_opcode:
      instruction.operator = AAA_SETGT;
      break;
    case SETLE_opcode:
      instruction.operator = AAA_SETLE;
      break;
    case SETGE_opcode:
      instruction.operator = AAA_SETGE;
      break;
    case SHR_opcode:
      if (Shift.getVal2(instruction).isConstant())
	  instruction.operator = AAA_CSHR;
      else
	instruction.operator = AAA_SHR;
	break;
    case SHL_opcode:
      if (Shift.getVal2(instruction).isConstant())
	instruction.operator = AAA_CSHL;
      else
	instruction.operator = AAA_SHL;
      break;
    case ADD_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_IADD;
      break;
    case SUB_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_ISUB;
      break;
    case MUL_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_IMUL;
      break;
    case DIV_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_IDIV;
      break;
    case INV_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_IINV;
      break;
    case SQRT_opcode:
      if (instruction.type().isFloat()) 
	instruction.operator = NOP;
      else 
	instruction.operator = AAA_ISQRT;
      break;
    default:
      System.err.println("OperationSelection: I do not handle selecting operand "+instruction.operator);
    }
  }
	
}

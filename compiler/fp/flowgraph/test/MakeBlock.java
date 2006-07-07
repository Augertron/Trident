/*
 *
 @LICENSE@
 */


package fp.flowgraph.test;

import fp.flowgraph.*;

public class MakeBlock implements Operators {

  BlockNode block;

  MakeBlock() {
    this(null);
  }

  MakeBlock(BlockNode external) {
    if (external == null)
      block = new BlockNode();
    else
      block = external;

    // should there be a fancy label with its own unique name space ...
    block.setName("then",0);
    
    // who is keeping the operand name space unique and how do I get
    // the next free operand ???

    Instruction inst = null;

    Operand tmp0 = Operand.nextBlock("tmp");
    PrimalOperand a = Operand.newPrimal("a");
    inst = Load.create(LOAD, Type.Int, tmp0, a);
    block.addInstruction(inst);

    Operand tmp1 = Operand.nextBlock("tmp");
    PrimalOperand b = Operand.newPrimal("b");
    inst = Load.create(LOAD, Type.Int, tmp1, b);
    block.addInstruction(inst);

    Operand tmp2 = Operand.nextBlock("tmp");
    inst = Binary.create(ADD, Type.Int, tmp2, tmp0, tmp1);
    block.addInstruction(inst);

    BooleanOperand tmp3 = Operand.nextBoolean("tmp");
    inst = Test.create(SETEQ, Type.Int, tmp3, tmp2, 
		       Operand.newIntConstant(0));
    block.addInstruction(inst);

    inst = Branch.create(BR, tmp3, 
			 Operand.newLabel("then",0),
			 Operand.newLabel("exit"));
    block.addInstruction(inst);

  }

  public static void main(String args[]) {
    MakeBlock mb = new MakeBlock();
    System.out.println(mb.block);

  }

}

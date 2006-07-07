/*
 *
 @LICENSE@
 */


package fp.flowgraph.test;

import fp.flowgraph.*;

import fp.passes.AddPredicates;
import fp.passes.GraphPass;

public class MakeBigGraph extends BlockGraph implements Operators {

  MakeBigGraph() {
    super();
    BlockNode a, b;

    a = (BlockNode)addNode();

    MakeBlock mb = new MakeBlock(a);
    
    // need to fix up last branch in mb
    int size = a.getInstructions().size();
    Instruction branch = (Instruction)a.getInstructions().get(size - 1);
    a.removeInstruction(branch);

    Branch.setTarget2(branch, Operand.newLabel("if",0));
    a.addInstruction(branch);

    b = (BlockNode)addNode();
    addBlock(b);

    ((ControlFlowEdge)addEdge(ENTRY, a)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)addEdge(a,a)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)addEdge(a,b)).setLabel(Boolean.FALSE);
    ((ControlFlowEdge)addEdge(b, EXIT)).setLabel(Boolean.TRUE);

  }

  void addBlock(BlockNode b) {
    Instruction inst;
    
    // set name of block
    b.setName("if",0);

    // make some operands
    Operand tmp0 = Operand.nextBlock("tmp");
    PrimalOperand a = Operand.newPrimal("a");

    // add an instruction via InstructionFormat
    inst = Load.create(LOAD, Type.Int, tmp0, a);
    b.addInstruction(inst);

    // make some more operands
    Operand tmp1 = Operand.nextBlock("tmp");
    Operand one = Operand.newIntConstant(1);
    
    // add a binary instruction
    inst = Binary.create(ADD, Type.Int, tmp1, tmp0, one);
    b.addInstruction(inst);

    // add a store instruction.
    inst = Store.create(STORE, Type.Int, a, tmp1);
    b.addInstruction(inst);

    inst = Return.create(RET);
    b.addInstruction(inst);
  }


  public static void main(String args[]) {
    MakeBigGraph mg = new MakeBigGraph();
    mg.writeDotFile("mbg_test.dot");

    //GraphPass pass = new AddPredicates();
    //pass.optimize(mg);
    
    //mg.writeDotFile("mbg2_test.dot");
  }

}

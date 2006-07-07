/*
 *
 @LICENSE@
 */


package fp.flowgraph.test;

import fp.flowgraph.*;

public class MakeInstruction implements Operators {

  public static void main(String args[]) {
    
    // that is an ugly constructor.
    Instruction inst = new Instruction(ADD_opcode, Type.Float);
    int def_count = inst.getNumberOfDefs();
    for(int i = 0; i < def_count; i++) {
      // probably not the best way, but I did avoid hard coding.
      // this is not a good way to create Operands.
      inst.putOperand(i, new BlockOperand("a", i));
    }
    for(int i = def_count; i< inst.getNumberOfOperands(); i++) {
      inst.putOperand(i, new BlockOperand("c", i));
    }
    
    inst.setType(Type.Int);

    System.out.println("Basic instruction ");
    System.out.println(inst);

  }
}
    

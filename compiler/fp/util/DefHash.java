/*
 *
 @LICENSE@
 */


package fp.util;

import java.util.*;

import fp.flowgraph.Operand;
import fp.flowgraph.Instruction;


public class DefHash extends HashMap {
  // this can just be a hashMap


  /*

  add(Instruction ...)
    if Instruction.result != null
    put(result, instruction)

  */

  // where is the get ???

  public void add(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = 0; i < def_count; i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // definitions are unique.
      put(o, inst);
    }
  }

  public void remove(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = 0; i < def_count; i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // definitions are unique.
      super.remove(o);
    }
  }

}

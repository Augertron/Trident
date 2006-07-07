/*
 *
 @LICENSE@
 */


package fp.util;

import java.util.*;

import fp.flowgraph.Operand;
import fp.flowgraph.Instruction;


public class MultiDefHash extends HashMap {
  // this can just be a hashMap


  /*

  add(Instruction ...)
    if Instruction.result != null
    put(result, instruction)

  */


  //I need some more power...
  public void addinstructions(Collection instructions) {
    for (Iterator instIt = instructions.iterator();
    	 instIt.hasNext();){
      Instruction inst = (Instruction) instIt.next();
      add(inst);
    }
  }

  // where is the get ???

  public void add(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = 0; i < def_count; i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // definitions are unique.
      ArrayList list = (ArrayList)get(o);
      if (list == null) {
	list = new ArrayList();
	put(o, list); 
      }
      if (!list.contains(inst)) {
	list.add(inst);
      }
    } // end operands
  }

  public void remove(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = 0; i < def_count; i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // definitions are unique.
      ArrayList list = (ArrayList)get(o);
      if (list != null) {
	list.remove(inst);
	if (list.size() == 0)
	  super.remove(o);
      }
    } // end operands
  }


}

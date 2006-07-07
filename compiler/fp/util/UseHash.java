/*
 *
 @LICENSE@
 */


package fp.util;

import java.util.*;

import fp.flowgraph.Operand;
import fp.flowgraph.Instruction;

public class UseHash extends HashMap {


  /*

  void add(Instruction ... )

  for all ops not the result
     ArrayList a = get(op);
     if (a == null) 
         a = new List
     list.add(instruction)    

  */
  
  //I need some more power...
  public void addinstructions(Collection instructions) {
    for (Iterator instIt = instructions.iterator();
    	 instIt.hasNext();){
      Instruction inst = (Instruction) instIt.next();
      add(inst);
    }
  }

  public void add(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = def_count; i < inst.getNumberOfOperands(); i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // uses are definitely not unique.
      ArrayList list = (ArrayList)get(o);
      if (list == null) {
	list = new ArrayList();
	put(o, list);
      }
      if (!list.contains(inst)) {
	list.add(inst);
      }
    }
  }


  public void remove(Instruction inst) {
    int def_count = inst.getNumberOfDefs(); 
    for(int i = def_count; i < inst.getNumberOfOperands(); i++) {
      Operand o = inst.getOperand(i);
      if (o == null) continue;
      // uses are definitely not unique.
      ArrayList list = (ArrayList)get(o);
      if (list != null) {
	list.remove(inst);
	if (list.size() == 0) 
	  super.remove(o);
      }
    }
  }



  

}

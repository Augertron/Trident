/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.util.BooleanEquation;
import fp.util.UseHash;




public class ControlRemoval extends Pass implements BlockPass {
  
  /*
    This is a very simple pass.  It removes branches, gotos, and return.
    Dropping return may have some implications, where gotos and branches
    are safe.

    This must run after addPredicates ...

  */


  public ControlRemoval(PassManager pm) {
    super(pm);
  }

  public String name() { return "ControlRemoval"; }

  public boolean optimize(BlockNode node) {
    ArrayList list = node.getInstructions();
    HashSet dead = new HashSet();
    for(Iterator iter = list.iterator(); iter.hasNext(); ) {
      Instruction instruction = (Instruction) iter.next(); 
      if (Branch.conforms(instruction) 
	  || Goto.conforms(instruction)
	  || Return.conforms(instruction))
	dead.add(instruction);
      if (Switch.conforms(instruction)) 
	System.err.println("Illegal switch in node "+node.getName());
    }
    for(Iterator iter = dead.iterator(); iter.hasNext(); ) {
      Instruction instruction = (Instruction)iter.next();
      node.removeInstruction(instruction);
    }
    return true;
  }
  
}


/*
 *
 @LICENSE@
 */


package fp.passes;
import fp.flowgraph.*;
import java.util.*;
 
public class ConstantSelect extends Pass implements BlockPass {
 
  /*
    The purpose of this pass is to use constants to eliminate 
    select instructions.
  */
   
 
  public ConstantSelect(PassManager pm) {
    super(pm);
  }
 
  public boolean optimize(BlockNode node) {
    // For example, in the following instruction:
    // %a5 = select %TRUE %a3 %a4 predicate
    // This instruction can be removed and then
    // substitute %a3 for %a5 hereafter.

    // The predicate can be ignored, because a
    // subsequent use of %a5, for example, would 
    // be using the same predicate, or one that is
    // more inclusive.

    // Store substitution pair in a hash map.
    boolean steadyState = false;

    while (!steadyState) {

      steadyState = true;
      HashMap subsMap = new HashMap();  

      ArrayList list = node.getInstructions();
      ArrayList instRemovalList = new ArrayList();

      for(Iterator iter = list.iterator(); iter.hasNext(); ) {
	Instruction inst = (Instruction) iter.next(); 
	// Examine select instructions for constant condition value.
	if (Select.conforms(inst)) {
	  BooleanOperand bool = Select.getCondition(inst);
	  if (bool == BooleanOperand.TRUE) {
	    //System.out.println("Found a select with a TRUE");
	    Operand res = Select.getResult(inst);
	    Operand val1 = Select.getVal1(inst);
	    subsMap.put(res, val1);
	    instRemovalList.add(inst);
	    steadyState = false;
	  } else if (bool == BooleanOperand.FALSE) {
	    //System.out.println("Found a select with a FALSE");
	    Operand res = Select.getResult(inst);
	    Operand val2 = Select.getVal2(inst);
	    subsMap.put(res, val2);
	    instRemovalList.add(inst);
	    steadyState = false;
	  }
	}
      }

      for(Iterator iter = instRemovalList.iterator(); iter.hasNext(); ) {
	Instruction inst = (Instruction) iter.next(); 
	node.removeInstruction(inst);
      }

      if (subsMap.size() > 0) {
	HashMap instMap = new HashMap();

	for(Iterator iter = list.iterator(); iter.hasNext(); ) {
	  Instruction inst = (Instruction) iter.next(); 

	  // Check uses for any substitutions.
	  int numDefs = inst.getNumberOfDefs();
	  int numUses = inst.getNumberOfUses();
	  for(int i = numDefs; i < (numDefs + numUses); i++) {
	    Operand use = inst.getOperand(i);
	    Operand useSub = (Operand)(subsMap.get(use));

	    // Record the substitution we will make later
	    if (useSub != null) {
	      instMap.put(inst, use);
	    }
	  }
	}

	Set instSet = instMap.keySet();

	// Remove all the instructions that need substitutions 
	for(Iterator iter = instSet.iterator(); iter.hasNext(); ) {
	  Instruction inst = (Instruction) iter.next(); 
	  node.removeInstruction(inst);
	}

	// make the substitution
	for(Iterator iter = instSet.iterator(); iter.hasNext(); ) {
	  Instruction inst = (Instruction) iter.next(); 
	  Operand use = (Operand)instMap.get(inst);
	  Operand useSub = (Operand)(subsMap.get(use));
	  inst.replaceOperand(use, useSub);
	  node.addInstruction(inst);
	}
      }
    }
    
    return true;
  }
 
  public String name() {
    return "ConstantSelect";
  }
 
 
}


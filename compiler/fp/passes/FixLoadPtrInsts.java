/*
 *
 @LICENSE@
 */


package fp.passes;
 
import java.util.*;
 
import fp.flowgraph.*;

/**
 * This class changes all loads that use a pointer to atomic types.  The type 
 * is changed from the pointer to atomic to the atomic type.
 */
public class FixLoadPtrInsts extends Pass implements BlockPass {
  public FixLoadPtrInsts(PassManager pm) {
    super(pm);
  }

  public String name() { return "FixLoadPtrInsts"; }


  /**
   * This method fixes all load pointer insts that point to non-arrays and 
   * non-pointers.  The type is changed to the type being pointed to.
   */
  public boolean optimize(BlockNode bn) {
    // For each instruction in this block...
    for(Iterator iIt = bn.getInstructions().iterator(); iIt.hasNext(); ) {
      Instruction inst = (Instruction) iIt.next();
      // If this instruction is a pointer load
      if(inst.isLoad() && inst.type().isPointer()) {
	Type type = ((PointerType)inst.type()).getType();
	// If the pointed-to type isn't an array or pointer, then change 
	// the type to the pointed-to type.
	if((!type.isArray()) && (!type.isPointer())) {
	  //System.out.println("changing instruction:");
	  //System.out.println(inst);
	  inst.setType(type);
	  //System.out.println(inst);
	}
      }
    }
 
    return true;
  }

}

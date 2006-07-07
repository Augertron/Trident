/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.util.BooleanEquation;
import fp.flowgraph.*;

// java imports
import java.util.*;

/**
 * A pass for calculating the defhash and the usehash.  This pass
 * should not be necessary if all other passes are careful to use the
 * proper approach to add and delete instructions and when updating an
 * instruction.
 *
 * @author Justin Tripp
 */


public class CalcDefUseHash extends Pass implements BlockPass {

  public CalcDefUseHash(PassManager pm) {
    super(pm);
  }

  public String name() { return "CalcDefUseHash"; }

  public boolean optimize(BlockNode node) {
    
    // Der Plan!
    // clone ArrayList.
    // delete all instructions
    // add the back again.

    ArrayList orig_instructions = node.getInstructions();
    ArrayList instructions = (ArrayList)orig_instructions.clone();
    // wipe instructions 
    orig_instructions.clear();
    // wipe hashes
    node.getDefHash().clear();
    node.getUseHash().clear();

    for(Iterator iter = instructions.iterator(); iter.hasNext(); ) 
      node.addInstruction((Instruction)iter.next());
    
    return true;
  }
  
}

/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.hardware.*;


/** this was created to add type to primals.  I'm not sure if this is still 
 *  necessary, because I think Christine changed the parser to add types to all 
 *  operands, but anyway, I need this so that I can get the width and size of 
 *  arrays which is only reachable through the type.
 * 
 * @author Kris Peterson
 */
public class AddTypeToPrimals extends Pass implements GraphPass {
   
   
  /** "@param pm
   * 
   * @param pm 
   */
  public AddTypeToPrimals(PassManager pm) {
    super(pm);
  }
  
  /** find primal operands on store or load instructions and set their type 
   *  equal to the type attached to the instruction.
   * 
   * @param graph_BlockGraph 
   */
  public boolean optimize(BlockGraph graph_BlockGraph) {
    for (Iterator vIt = graph_BlockGraph.getAllNodes().iterator(); 
                vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
       
      ArrayList list_ArrayList = node.getInstructions();
      for (Iterator it = ((ArrayList)list_ArrayList).iterator(); 
             it.hasNext(); ) {
        Instruction instr = (Instruction)it.next();
        if(instr.isStore() || instr.isLoad()) {
          int total = instr.getNumberOfOperands();
          for(int i = 0; i < total; i++) {
            Operand op_Operand = instr.getOperand(i);
            if(op_Operand == null) continue;
            if(op_Operand.isPrimal()) {
              op_Operand.setType(instr.type());
            }
          }
           
           
        }
      }
    }
     
     
    return true;
  }
  
   
  public String name() { 
    return "AddTypeToPrimals";
  }
   
}


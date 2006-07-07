/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.util.*;
import fp.hardware.*;


/** With this pass, kann man Subs mit Invs umtauschen.
 * 
 * @author Kris Peterson
 */
public class FixFSubInstructions extends Pass implements GraphPass {
   

  public FixFSubInstructions (PassManager pm) {
    super(pm);
  }
  
  /** do yo' thang, little buddy!
   * 
   * @param graph_BlockGraph 
   * @return success of being able to return a true
   */
  public boolean optimize(BlockGraph graph) {
    boolean scheduledOk = true;
     
    for (Iterator vIt = graph.getAllNodes().iterator(); 
             vIt.hasNext();) {
      BlockNode node = (BlockNode) vIt.next();
      for (Iterator vIti = ((ArrayList)node.getInstructions().clone()).iterator(); 
             vIti.hasNext();) {
        Instruction inst = (Instruction) vIti.next();
      
        if(inst.operator.opcode == Operators.SUB_opcode) {
	 //ooooh, nooo, I actually have to do something, maybe!
	  float num=1;
	  Operand val1 = Binary.getVal1(inst);
	  if(val1 instanceof DoubleConstantOperand) {
	    DoubleConstantOperand dval1 = (DoubleConstantOperand)val1;
	    num=(float)dval1.getValue();
	  }
	  else if(val1 instanceof FloatConstantOperand) {
	    FloatConstantOperand fpval1 = (FloatConstantOperand)val1;
	    num=(float)fpval1.getValue();
	  }
	  else if(val1 instanceof IntConstantOperand) {
	    IntConstantOperand ival1 = (IntConstantOperand)val1;
	    num=(float)ival1.getValue();
	  } 
	  if(num==0) {
	  ///nein, jetzt muss ich was unternehmen!!!
	    BooleanEquation eq = inst.getPredicate();
	    Type type = inst.type();
	    Instruction replacementInv = new Instruction(Operators.INV, type, 2, eq);
	    
	    Operand out = Binary.getResult(inst);
	    Operand in = Binary.getVal2(inst);
	    replacementInv.putOperand(0, out);
	    replacementInv.putOperand(1, in);
	    
	    node.removeInstruction(inst);
	    node.addInstruction(replacementInv);
	    
	    //inst.operator = Operators.INV;
	  }
	}
      
      }
      
    }
     
    return true;
  }
  
   
  public String name() { 
    return "FixFSubInstructions";
  }
   
}


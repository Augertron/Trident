/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.graph.Node;
import fp.graph.Edge;


public class PhiLowering extends Pass implements GraphPass, Operators {
  
  /*
  
  */
   
  private LinkedList _pass_schedule;
  
  public PhiLowering(PassManager pm) {
    super(pm);
    _pass_schedule = new LinkedList();
  }
  
  public boolean optimize(BlockGraph graph_BlockGraph) {
    int verbose = pm.getVerbose();
    
    for (Iterator vIt = graph_BlockGraph.getAllNodes().iterator(); vIt.hasNext();){
      BlockNode node_BlockNode = (BlockNode) vIt.next();
      // find phi statements
      // if phi examine each input pair
      // for P <- Tmp is store
      // for tmp <- P is load
      // for tmp3 <- tmp5 replace and eliminate tmp5 
      // for P <- P ???? 
       
      ArrayList list_ArrayList = (ArrayList)node_BlockNode.getInstructions().clone();
      for(Iterator instIt = list_ArrayList.listIterator(); instIt.hasNext(); ) {
        Instruction instruction = (Instruction)instIt.next();
        
        if (Phi.conforms(instruction)) {
          node_BlockNode.removeInstruction(instruction);
          System.out.println("Removing ... "+instruction);
          Type type = instruction.type();
           
          Operand result = Phi.getResult(instruction);
          int op_count = instruction.getNumberOfOperands();
          int def_count = instruction.getNumberOfDefs();
          for(int i = 0; i<(op_count-def_count)/2; i++) {
            LabelOperand label = Phi.getValLabel(instruction, i);
            Operand value = Phi.getValOperand(instruction, i);
            if(value == result) continue; //I found an example where x = phi(x,...) 
            BlockNode destination = findNode(node_BlockNode, label);
            /*boolean destissource = false;
            if(destination == node_BlockNode)
              destissource = true;
            addOperation(result, value, type, destination, graph_BlockGraph, destissource);
            */
            addOperation(result, value, type, destination, graph_BlockGraph);
          }
           
        } else break;
          // Since all phis must be first we can quit once we don't find
        // them.
      }
    }      
    return true;
  }
  
  BlockNode findNode(BlockNode node_BlockNode, LabelOperand operand) {
    for(Iterator inIter = node_BlockNode.getInEdges().iterator(); inIter.hasNext(); ) {
      Edge edge_Edge = (Edge)inIter.next();
      BlockNode source_Operand = (BlockNode)edge_Edge.getSource(); 
      LabelOperand source_label = source_Operand.getLabel();
      if (source_label == operand) 
        return source_Operand;
    }
    
    for(Iterator outIter = node_BlockNode.getOutEdges().iterator();outIter.hasNext(); ) {
      Edge edge_Edge = (Edge)outIter.next();
      BlockNode sink_BlockNode = (BlockNode)edge_Edge.getSink(); 
      LabelOperand sink_label = sink_BlockNode.getLabel();
      if (sink_label == operand) 
        return sink_BlockNode;
    }
    
    return (BlockNode)null;
  }
   
  
  void addOperation(Operand result, Operand source_Operand, Type type, 
  BlockNode node_BlockNode, BlockGraph graph_BlockGraph) {
    // BlockNode node_BlockNode, BlockGraph graph_BlockGraph, boolean destIsSource) {
      
      if (result.isPrimal()) {
        if (source_Operand.isPrimal()) {
          // P2 <- P1
          // tmp <- P1 load
          // P2 <- tmp store
          AddrOperand temp = Operand.nextAddr("phi.tmp");
          Instruction load = Load.create(LOAD, type, temp, source_Operand);
          node_BlockNode.addInstruction(load);
          System.out.println("Adding instruction "+load);
          Instruction store = Store.create(STORE, type, result, temp);
          node_BlockNode.addInstruction(store);
          System.out.println("Adding instruction "+store);          
        } else {
          // P <- tmp store
          //System.out.println("primal to tmp copy ....");
          //System.out.println(" result "+result+" "+result.isPrimal());
          //System.out.println(" source_Operand "+source_Operand+" "+source_Operand.isPrimal());	
          Instruction instruction = Store.create(STORE, type, 
                                                (PrimalOperand)result, 
                                                 source_Operand);
          System.out.println("Adding instruction "+instruction);
          node_BlockNode.addInstruction(instruction);
        }
      } else {
        if (source_Operand.isPrimal()) {
          // tmp <- P load
          //System.out.println("tmp to primal copy ....");
          //System.out.println(" result "+result+" "+result.isPrimal());
          //System.out.println(" source_Operand "+source_Operand+" "+source_Operand.isPrimal());	
          Instruction instruction = Load.create(LOAD, type, result, 
                                               (PrimalOperand)source_Operand);
          System.out.println("Adding instruction "+instruction);
          node_BlockNode.addInstruction(instruction);
        } else {
          // tmp3 <- tmp5 replace 
	  //doing a replace it too difficult.  For example if we had a phi in a loop that said:
	  //x = phi entry 1 loop y
	  //and later in the loop block had 
	  //y = x +1
	  //if we did replacements and moved the x = 1 to entry we'd end up with 
	  //y = y +1 or
	  //x = x + 1
	  //in the loop body, depending on what we decided to replace.  It's easier
	  //just to let primalpromotion fix this, because otherwise we'd have to do a lot
	  //of analyzing of what instructions are using the operand before replacing and all of 
	  //this is already done by primalpromotion
          Instruction instruction = Store.create(STORE, type, 
                                                 (result), 
                                                 source_Operand);
          System.out.println("Adding instruction "+instruction);
          node_BlockNode.addInstruction(instruction);
          //System.exit(-1); // for now.
        }
      }
    }
    
    
    
    
    public void add(Pass pass) {
      // check dependencies 
      // see if CFG is consistent 
      // and if this requires it to be.
      // add appropriate passes.
      _pass_schedule.add(pass);
    }
    
    
    public String name() { 
      return "PhiLowering";
    }
    
    
    
  }
  

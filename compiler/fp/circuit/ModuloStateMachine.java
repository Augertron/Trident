/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

import java.util.*;

import fp.util.BooleanEquation;
import fp.util.BooleanOp;
import fp.flowgraph.*;

public class ModuloStateMachine extends StateMachine {
  
  public ModuloStateMachine(int states, BlockNode bn) {
    super(states);
    System.out.println("Generating a ModuloStateMachine w/ II="+bn.getII());
    BooleanEquation backedge = getBackEdge(bn);
    if(backedge != null)
      addLoopConditionsToTable(backedge);
    collectInputs();    // this must be done after the edges are setup
    //System.out.println("State Machine: \n"+this);
  }

  /**
   * This method changes the last cycle in the FSM so that it goes (loops) 
   * back to the first cycle if the exit condition is not met.
   */
  private void addLoopConditionsToTable(BooleanEquation backedge) {
    _table[_states-2][0] = backedge;
    _table[_states-2][_states-1] = ((BooleanEquation)backedge.clone()).not();
  }

}

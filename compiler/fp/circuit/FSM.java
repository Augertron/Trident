/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

import java.util.*;

import fp.util.BooleanEquation;

public abstract class FSM extends Node {

  // these are portinfos -- inputs and outputs are limited to states.
  //LinkedList inputs;
  //LinkedList outputs;
  private StateMachine _transitions;
  
  public FSM(Circuit parent, String name, StateMachine transitions) {
    super(parent, name);
    _transitions = transitions;
    
    /*
    int sm_states = _transitions.getStates();
    BooleanEquation[][] table = _transitions.getTable();
    int tb_states = table.length;

    System.out.println("FSM states:");
    System.out.println(" declared  "+_states);
    System.out.println(" table     "+tb_states);
    System.out.println(" sm_object "+sm_states);

    if (sm_states != _states) {
      System.err.println("State Machine states != declared states");
    }
    if (tb_states != _states) {
      System.err.println("State Machine table != declared states");
    }
    if (tb_states != sm_states) {
      System.err.println("State Machine table != State Machine states");
    }
    */


  }

  public int getStates() { return _transitions.getStates(); }
  protected StateMachine getTransitions() { return _transitions; }


}

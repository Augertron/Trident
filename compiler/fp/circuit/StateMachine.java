/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

import java.util.*;

import fp.util.BooleanEquation;
import fp.util.BooleanOp;
import fp.flowgraph.*;


public abstract class StateMachine {

  public static final String FSM_OUT = "o_s%";
  
  protected int _states;
  private int _start_state;

  protected BooleanEquation[][] _table;

  private HashMap _inputs;

  public StateMachine(int states) {
    _states = states;
    _start_state = states - 1;
    initTable();
    _inputs = new HashMap();

    // this must be done after the edges are setup
    //collectInputs();  // instead, do this in subclasses?
  }

  public int getStates() { return _states; }

  public int getStartState() { return _start_state; }
  void setStartState(int s) { _start_state = s; }

  public BooleanEquation[][] getTable() { return _table; }

  public HashMap getInputs() { return _inputs; }
  public void addInput(String a, Object o) { _inputs.put(a,o); }

  void initTable() {
    _table = new BooleanEquation[_states][_states];
    for(int i=0; i < _states; i++) {
      for(int j=0; j < _states; j++) {
	if(i == j-1)
	  _table[i][j] = new BooleanEquation(true);
	else if((i==_states-1) && (j==0)) {
	  BooleanOperand boolop = Operand.newBoolean("start");
	  _table[i][j] = new BooleanEquation(boolop, true);
	} else if((i==_states-1) && (j==_states-1)) {
	  BooleanOperand boolop = Operand.newBoolean("start");
	  _table[i][j] = new BooleanEquation(boolop, false);
	} else
	  _table[i][j] = new BooleanEquation(false);
      }
    }
  }

  void collectInputs() {
    for(int i=0; i < _states; i++) {
      for(int j=0; j < _states; j++) {
	BooleanEquation eq = _table[i][j];
	if (eq.isTrue() || eq.isFalse()) continue;
	LinkedList operands = eq.listBooleanOperands();
	for(ListIterator iter=operands.listIterator(); iter.hasNext();) {
	  BooleanOperand op = (BooleanOperand) iter.next();
	  // why hash both ??
	  addInput(op.getFullName(), op);
	}
      }
    }
  }

  public HashMap getExitConditions() {
    HashMap map = new HashMap();
    for(int i = 0; i < (_states-1); i++) 
      map.put(new Integer(i), _table[i][_states-1]);
    return map;
  }

  /**
   * Returns the predicate associated with the backedge pointing to the given 
   * block.  Null is returned if no backedge is found.
   */
  static BooleanEquation getBackEdge(BlockNode bn) {
    // For each outedge in bn...
    for(Iterator eIt = bn.getOutEdges().iterator(); eIt.hasNext(); ) {
      BlockEdge edge = (BlockEdge) eIt.next();
      // If this outedge points to this blocknode...
      if(((BlockNode)edge.getSink()).equals(bn))
	return edge.getPredicate();
    }
    return (BooleanEquation) null;
  }

  public String toString() {
    int cycles = _states;
    StringBuffer sbuf = new StringBuffer("State Transition Table\n");
    sbuf.append("From  :  -> to State\n");
    for (int i= 0;i<cycles;i++) {
      sbuf.append("Cycle : ");
      sbuf.append(Integer.toString(i)).append("      ");

      for(int j=0;j<cycles;j++) {
	sbuf.append(_table[i][j]).append(" ");
      }
      sbuf.append(";\n");
    }
    return sbuf.toString();
  }

}

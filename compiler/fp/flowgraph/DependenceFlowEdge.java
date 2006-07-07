/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.graph.Edge;

/** 
 * A directed edge in a data-flow graph
 * 
 * @author Nathan Kitchen
 * copied and modified by Kris Peterson
 */
public class DependenceFlowEdge extends Edge {
  /** 
   * An object that identifies the condition for which this
   * transition is taken
   */
  Object _label;
  /** is this edge a data dependence edge?
   */
  boolean _isDataEdge;
   
  /** does this edge effect the next iteration of the loop, or the current?
   */
  boolean _isBackWardsPointing;
   
  /** 
   * Construct a new data-flow edge with no tag, no source, and no sink
   */
  public DependenceFlowEdge() {
    this(null);
  }
  
  /** 
   * Construct a new data-flow edge with the given tag, no source, and
   * no sink
   */
  public DependenceFlowEdge(Object tag) {
    super(tag);
    setDotAttribute("label", "");
    _isBackWardsPointing = false;
    _isDataEdge = false;
    if(_isDataEdge)       
      setLabel("Data Dependency Flow Edge: forwards pointing");
    else 
      setLabel("Control Dependency Flow Edge: forwards pointing");
  }
  
  /** 
   * Construct a new data-flow edge with no tag from <code>source</code>
   * to <code>sink</code>
   */
  public DependenceFlowEdge(DependenceFlowNode source, DependenceFlowNode sink){
    this(source, sink, null);
  }
  
  /** 
   * Construct a new data-flow edge with the given tag from
   * <code>source</code> to <code>sink</code>
   */
  public DependenceFlowEdge(DependenceFlowNode source, DependenceFlowNode sink, 
                            Object tag) {
    super(source, sink, tag);
    setDotAttribute("label", "");
    _isBackWardsPointing = false;
    _isDataEdge = false;
  }
   
  /** defines this edge as a Data Dependence Edge (as opposed to a Control 
   *  Dependence Edge--the default).
   */
  public void setIsDataEdge(){_isDataEdge = true;}
  /** return if this edge is a data dependence edge instead of a control 
   *  dependence edge
   * 
   * @return true if data dependence edge; false if control dependence edge
   */
  //public boolean getIsDataEdge(){return _isDataEdge;}
  public final boolean getIsDataEdge() {
    return this instanceof DependenceDataFlowEdge;
  }
  public final boolean getIsCntrlEdge() {
    return this instanceof DependenceCntrlFlowEdge;
  }
   
  /** save if this edge is a recursive edge going over the loop boundary.
   * 
   * @param b_boolean whether or not the edge is recursive
   */
  public void setisBackWardsPointing(boolean b_boolean) {
    _isBackWardsPointing = b_boolean;
     
    if(_isDataEdge) {
      setDotAttribute("color", "purple");
      if(b_boolean)
        setLabel("Data Dependency Flow Edge: backwards pointing");
    }
    else {
      setDotAttribute("color", "orange");
      if(b_boolean)
        setLabel("Control Dependency Flow Edge: backwards pointing");
    }
  }
  /** save if this edge is a recursive edge going over the loop boundary.
   * 
   * @param b_boolean whether or not the edge is recursive
   */
  public void setBackWardsPointing() {
    _isBackWardsPointing = true;
     
    if(_isDataEdge) {
      setDotAttribute("color", "purple");
      setLabel("Data Dependency Flow Edge: backwards pointing");
    }
    else {
      setDotAttribute("color", "orange");
      setLabel("Control Dependency Flow Edge: backwards pointing");
    }
  }
  /** return if this edge is a recursive edge going over the loop boundary.
   * 
   * @return true if edge is recursive; false if not
   */
  public boolean getisBackWardsPointing(){return _isBackWardsPointing;}
  
  /** 
   * Assigns the object that identifies the condition for which this
   * transition is taken
   */
  public void setLabel(Object label) {
    _label = label;
    setDotAttribute("label", label == null ? "" : label.toString());
  }
}

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
public class DependenceCntrlFlowEdge extends DependenceFlowEdge {
  /** 
   * An object that identifies the condition for which this
   * transition is taken
   */
  //Object _label;
   
   
  /** 
   * Construct a new data-flow edge with no tag, no source, and no sink
   */
  public DependenceCntrlFlowEdge() {
    this(null);
  }
  
  /** 
   * Construct a new data-flow edge with the given tag, no source, and
   * no sink
   * 
   * @param tag 
   */
  public DependenceCntrlFlowEdge(Object tag) {
    super(tag);
    setDotAttribute("label", "");
    setDotAttribute("color", "red");
  }
  
  /** 
   * Construct a new data-flow edge with no tag from <code>source</code>
   * to <code>sink</code>
   * 
   * @param source source node this edge is connected to
   * @param sink sink node this edge is connected to
   */
  public DependenceCntrlFlowEdge(DependenceFlowNode source, 
                                 DependenceFlowNode sink) {
    this(source, sink, null);
  }
  
  //public boolean getIsDataEdge(){return false;}
  
  /** 
   * Construct a new data-flow edge with the given tag from
   * <code>source</code> to <code>sink</code>
   * 
   * @param source source node this edge is connected to
   * @param sink sink node this edge is connected to
   * @param tag 
   */
  public DependenceCntrlFlowEdge(DependenceFlowNode source, 
                                 DependenceFlowNode sink, Object tag) {
    super(source, sink, tag);
    setDotAttribute("label", "");
    setDotAttribute("color", "red");
    setLabel("Control Dependency Flow Edge: forwards pointing");
  }
   
  
  /** 
   * Assigns the object that identifies the condition for which this
   * transition is taken
   * 
   * @param label text label of edge
   */
  public void setLabel(Object label) {
    _label = label + " is backward pointing? " + _isBackWardsPointing;
    setDotAttribute("label", label == null ? "" : label.toString());
  }
}

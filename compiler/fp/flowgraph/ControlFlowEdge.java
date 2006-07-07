/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.graph.Edge;

/**
 * A directed edge in a control-flow graph.  Each edge has some Object as a
 * label that identifies the condition for it to be taken.  This label is
 * completely independent of the arbitrary tag that each edge has.
 *
 * <p>
 * <table border=1>
 * <tr><th colspan=2>Typical Labels</th>
 * <tr> <th>Edge type  <th>Label
 * <tr> <td>Jump</td> <td><code>null</code></td>
 * <tr> <td>Branch</td>
 * <td><code>Boolean.TRUE<br>Boolean.valueOf(false)</code> (in v1.4.0 or higher)</td>
 * <tr> <td>Case</td> <td><code>new Integer(1)<br>"default"</code></td>
 * </table>
 *
 * @author Nathan Kitchen
 */
public class ControlFlowEdge extends Edge {
  public static final int UNKNOWN_EDGE  = -1;
  public static final int TREE_EDGE     =  0;
  public static final int FORWARD_EDGE  =  1;
  public static final int CROSS_EDGE    =  2;
  public static final int BACKWARD_EDGE =  3;

  private int _edge_type = UNKNOWN_EDGE;


  /**
   * The type of unconditional edges (which may be fall-through edges, not
   * just jumps)
   */
  public static final int JUMP = 1;

  /**
   * The type of edges that come from a two-way branch, like an if statement
   */
  public static final int BRANCH = 2;

  /**
   * The type of edges that go to clauses of a case or switch statement
   */
  public static final int CASE = 3;

  /**
   * An object that identifies the condition for which this
   * transition is taken
   */
  Object _label;
  
  /**
   * Indicates which kind of control-flow edge this is
   *
   * @see #JUMP
   * @see #BRANCH
   * @see #CASE
   */
  private int _type;
  
  /**
   * Construct a new control-flow edge with no tag, no source, and no sink
   */
  public ControlFlowEdge() {
    this(null);
  }

  /**
   * Construct a new control-flow edge with the given tag, no source, and
   * no sink
   */
  public ControlFlowEdge(Object tag) {
    this(null, null, tag);
  }

  /**
   * Construct a new control-flow edge with no tag from <code>source</code>
   * to <code>sink</code>
   */
  public ControlFlowEdge(ControlFlowNode source, ControlFlowNode sink) {
    this(source, sink, null);
  }

  /**
   * Construct a new control-flow edge with the given tag from
   * <code>source</code> to <code>sink</code>
   */
  public ControlFlowEdge(ControlFlowNode source, ControlFlowNode sink, Object tag) {
    super(source, sink, tag);
  }

  /**
   * Returns an object that identifies the condition for which this
   * transition is taken
   */
  public Object getLabel() {
    return _label;
  }

  /**
   * Returns the type of this edge
   *
   * @see #JUMP
   * @see #BRANCH
   * @see #CASE
   */
  public int getType() {
    return _type;
  }

  /**
   * Assigns the object that identifies the condition for which this
   * transition is taken
   */
  public void setLabel(Object label) {
    _label = label;
    setDotAttribute("label", label == null ? "" : label.toString());
  }
  
  /**
   * Assigns the type of this edge
   *
   * @see #JUMP
   * @see #BRANCH
   * @see #CASE
   */
  public void setType(int type) {
    switch (type) {
    case JUMP:
    case BRANCH:
    case CASE:
      _type = type;
      break;
    default:
      throw new ControlFlowException("Unvalid type: " + type);
    } // end of switch ()
  }

  /**
   * This method compares the depths of the head and tail, if the head is
   * deeper or equal, this is a forward edge, otherwise the edge is a backward
   * edge
   */
  public boolean isForward() {
    return  (_edge_type!= BACKWARD_EDGE);
  }


  public boolean isTreeEdge() { return _edge_type == TREE_EDGE; }
  public boolean isBackwardEdge() { return _edge_type == BACKWARD_EDGE; }
  public boolean isForwardEdge() { return _edge_type == FORWARD_EDGE; }
  public boolean isCrossEdge() { return _edge_type == CROSS_EDGE; }

  void setTreeEdge() { _edge_type = TREE_EDGE; }
  void setBackwardEdge() { _edge_type = BACKWARD_EDGE; }
  void setForwardEdge() { _edge_type = FORWARD_EDGE; }
  void setCrossEdge() { _edge_type = CROSS_EDGE; }

}

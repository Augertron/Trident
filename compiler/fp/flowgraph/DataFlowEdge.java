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
 */
public class DataFlowEdge extends Edge {
  /**
   * Construct a new data-flow edge with no tag, no source, and no sink
   */
  public DataFlowEdge() {
    this(null);
  }

  /**
   * Construct a new data-flow edge with the given tag, no source, and
   * no sink
   */
  public DataFlowEdge(Object tag) {
    super(tag);
    setDotAttribute("label", "");
  }

  /**
   * Construct a new data-flow edge with no tag from <code>source</code>
   * to <code>sink</code>
   */
  public DataFlowEdge(DataFlowNode source, DataFlowNode sink) {
    this(source, sink, null);
  }

  /**
   * Construct a new data-flow edge with the given tag from
   * <code>source</code> to <code>sink</code>
   */
  public DataFlowEdge(DataFlowNode source, DataFlowNode sink, Object tag) {
    super(source, sink, tag);
    setDotAttribute("label", "");
  }
}

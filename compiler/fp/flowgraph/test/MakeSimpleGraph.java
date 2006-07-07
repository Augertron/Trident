/*
 *
 @LICENSE@
 */


package fp.flowgraph.test;

import fp.flowgraph.*;

public class MakeSimpleGraph implements Operators {

  public static void main(String args[]) {
    BlockGraph cfg = new BlockGraph();
    BlockNode entry, exit, a, b;

    a = (BlockNode)cfg.addNode();
    a.setName("Block_A");

    b = (BlockNode)cfg.addNode();
    b.setName("Block_B");

    ((ControlFlowEdge)cfg.addEdge(cfg.ENTRY, a)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)cfg.addEdge(a,b)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)cfg.addEdge(b,cfg.EXIT)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)cfg.addEdge(a,cfg.EXIT)).setLabel(Boolean.FALSE);

    cfg.writeDotFile("simple_graph.dot");
  }

}

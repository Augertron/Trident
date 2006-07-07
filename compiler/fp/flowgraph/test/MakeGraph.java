/*
 *
 @LICENSE@
 */


package fp.flowgraph.test;

import fp.flowgraph.*;

public class MakeGraph implements Operators {
  BlockGraph cfg;

  MakeGraph() {
    cfg = new BlockGraph();

    BlockNode a;
    a = (BlockNode)cfg.addNode();

    MakeBlock mb = new MakeBlock(a);
    
    ((ControlFlowEdge)cfg.addEdge(cfg.ENTRY, a)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)cfg.addEdge(a,a)).setLabel(Boolean.TRUE);
    ((ControlFlowEdge)cfg.addEdge(a,cfg.EXIT)).setLabel(Boolean.FALSE);

  }


  public static void main(String args[]) {
    MakeGraph mg = new MakeGraph();
    mg.cfg.writeDotFile("mg_test.dot");

  }

}

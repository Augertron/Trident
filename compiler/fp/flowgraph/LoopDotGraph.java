/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.graph.Edge;
import fp.graph.Node;
import fp.graph.Graph;

import java.util.*;
import java.io.*;

public class LoopDotGraph extends Graph {
  

public LoopDotGraph(LoopNode entry, String name) {
  setName(name);
  buildLoopDotGraph((Node)null, entry);
}

void buildLoopDotGraph(Node parent, LoopNode loopNode) {
  Node n = null;

  //if loop, create node LOOP
  if (loopNode instanceof Loop) {
    n = new Node("LOOP: " + ((Loop)loopNode).getName());
    n.setDotAttribute("shape", "ellipse");
  } else {
  //if block, create node BLOCK
    n = new Node("BLOCK: " + ((BlockNode)loopNode).getName());
    n.setDotAttribute("shape", "box");
  }

  //set its label
  n.setDotLabelFromToString();

  //add Node to graph
  addNode(n);

  //connect to parent (if there is one)
  if (parent != null) {
    addEdge(parent, n);
  }

  //get children
  if (loopNode instanceof Loop) {
    Set children = ((Loop)loopNode).getNodes();

    for(Iterator it=children.iterator(); it.hasNext();) {
      LoopNode nextChild = (LoopNode) it.next();

      //buildGraph for each child
      buildLoopDotGraph(n, nextChild);
    }
  }
}

}


				  

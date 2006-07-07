/*
 *
 @LICENSE@ 
 */ 
package fp.circuit.dot;

import java.util.Iterator;
import java.util.HashMap;

import fp.circuit.*;



public class DotFSM extends FSM implements Dot {

  DotFSM(Circuit parent, String name, StateMachine transitions) {
    super(parent, name, transitions);
  }


  
  public void build(String name, Object[] arg_o) {
    System.out.println("Building DotFSM");
  }


  public String toDot() {
    
    StringBuffer s = new StringBuffer();
    s.append("\""+getName()+"\"");
    s.append("[label=\"");
    s.append("{");
    
    Iterator in = getInPorts().iterator();
    if (in.hasNext()) {
      for ( s.append("{").append(((PortTag)in.next()).toDot()); in.hasNext() ; ) {
	s.append("|");
	s.append(((PortTag)in.next()).toDot());
      }
      s.append("}");
    }

    s.append("|"+getRefName()+"|");

    Iterator out = getOutPorts().iterator();
    if (out.hasNext()) {
      for ( s.append("{").append(((PortTag)out.next()).toDot()); out.hasNext() ; ) {
	s.append("|");
	s.append(((PortTag)out.next()).toDot());
      }
      s.append("}");
    }

    s.append("}\"]");
    s.append(";\n");
    return s.toString();
  }
  
  protected String vertexDotLabels() {
    return getRefName()+"\\n";
  }
 
  protected String vertexDotProperties() {
    //    return vertexDotProperties();
    return "shape=box" ;
  }  


}

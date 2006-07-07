/*
 *
 @LICENSE@ 
 */ 
package fp.circuit.dot;

import java.util.Iterator;
import fp.circuit.*;



public class DotRegister extends Register implements Dot {

  DotRegister(Circuit parent, String name, int width, String value) {
    super(parent, name, width, value);
  }


  
  public void build(String name, Object[] arg_o) {
    System.out.println("Building DotRegister");
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

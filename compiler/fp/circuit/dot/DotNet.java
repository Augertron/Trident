/*
 *
 @LICENSE@ 
 */ 
package fp.circuit.dot;

import java.util.Iterator;
import fp.circuit.*;



public class DotNet extends Net implements Dot {

  public DotNet(Circuit parent, String name) {
    super(parent, name);
  }


  public void build(String name, Object[] arg_o) {
    System.out.println("Building DotNet");
  }
    

  public String toDot() {
    StringBuffer s = new StringBuffer();
    for (Iterator in = sources.iterator(); in.hasNext(); ) {
      PortTag i = (PortTag)in.next();
      for (Iterator out = sinks.iterator(); out.hasNext(); ) {
        s.append("\""+i.getParent().getName()+"\":\""+i.getName()+"\"");
        s.append("->");
        PortTag p = (PortTag)out.next();
        s.append("\""+p.getParent().getName()+"\":\""+p.getName()+"\"");
                                                                                     
        String name = getName();
        if( name != null ) {
          s.append( " [ label = \"" + name + "("+width+")\" ]" );
        }
        s.append(";\n");
      }
    }
    return s.toString();
  }

}

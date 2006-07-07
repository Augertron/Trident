/*
 *
 @LICENSE@
*/



package fp.util.vhdl.generator;

import java.util.*;

public class InterfaceList extends LinkedList implements VHDLout {


  public InterfaceList() {
    super();
  }


  // could have a special add that rejects other object types ...
  

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    int count = 0;
    for(ListIterator list_iter = listIterator(); list_iter.hasNext(); ) {
      VHDLout out = (VHDLout)list_iter.next();
      if (count != 0) {
	s.append(";\n");
      }
      out.toVHDL(s,pre);
      count++;
    }
    s.append("\n");

    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }
  

}  

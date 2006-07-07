/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class AssociationList extends LinkedList implements VHDLout {

  boolean _wrap = true;

  public AssociationList(boolean wrap) {
    super();
    _wrap = wrap;
  }

  public AssociationList() {
    this(true);
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    int count = 0;
    s.append(pre);
    for(ListIterator list_iter = listIterator(); list_iter.hasNext(); ) {
      VHDLout out = (VHDLout)list_iter.next();
      if (count != 0) {
	if (_wrap) 
	  s.append(",\n").append(pre);
	else
	  s.append(", ");
      }
      out.toVHDL(s,"");

      count++;
    }
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }
  

}  





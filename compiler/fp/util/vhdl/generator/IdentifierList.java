/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class IdentifierList extends LinkedList implements VHDLout {

  public IdentifierList() {
    super();
  }

  public IdentifierList(Identifier id) {
    super();
    add(id);
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    int count = 0;
    for(ListIterator list_iter = listIterator(); list_iter.hasNext(); ) {
      Identifier id = (Identifier)list_iter.next();
      if (count != 0) {
	s.append(", ");
      }
      id.toVHDL(s,"");
      count++;
    }
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }
  
}  

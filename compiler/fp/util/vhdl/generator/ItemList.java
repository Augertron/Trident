/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class ItemList extends LinkedList implements VHDLout {


  // could have a special add that rejects other object types ...
  

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    for(ListIterator list_iter = listIterator(); list_iter.hasNext(); ) {
      VHDLout out = (VHDLout)list_iter.next();
      out.toVHDL(s,pre);
    }
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }
  

}  



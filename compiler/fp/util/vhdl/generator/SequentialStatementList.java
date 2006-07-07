/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;


public class SequentialStatementList extends LinkedList implements VHDLout {
  
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    for(ListIterator list_iter = listIterator(); list_iter.hasNext();) {
      VHDLout vo = (VHDLout)list_iter.next();
      vo.toVHDL(s,pre);
    }
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }




}  

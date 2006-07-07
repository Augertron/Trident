/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class SensitivityList extends LinkedList implements VHDLout  {

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    int count = 0;
    for(ListIterator list_iter = listIterator(); list_iter.hasNext();) {
      VHDLout vo = (VHDLout)list_iter.next();
      if (count == 0) 
	s.append("( ");
      else 
	s.append(", ");

      vo.toVHDL(s,"");
      count++;
    }
    if (count != 0) {
      s.append(" )");
    }
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }


}  

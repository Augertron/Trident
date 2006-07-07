/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class ChoiceList extends LinkedList implements VHDLout {
  
  // This is not super safe, but more so

  public ChoiceList(Choice c) {
    super();
    if (c != null)
      add(c);
  }

  public ChoiceList() {
    this(null);
  }


  public boolean add(Object o) {
    if (o instanceof Choice) 
      return super.add(o);
    else {
      System.out.println("Attempt was made to add Non-choice to choice list");
      return false;
    }
  }

  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    int count = 0;
    for(ListIterator list_iter = listIterator(); list_iter.hasNext();) {
      if (count != 0) s.append(" | ");
	
      VHDLout vo = (VHDLout)list_iter.next();
      vo.toVHDL(s,"");
      count++;
    }
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }




}  

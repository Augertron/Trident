/*
 *
 @LICENSE@
 */


package fp.synthesis;

import java.util.*;

public class MemInfoList extends ArrayList implements Text {
  
  private int  _next_index;

  public MemInfoList() {
    super();
    _next_index = 0;
  }

  public boolean add(MemInfo info) {
    info.setIndex(_next_index);
    _next_index++;
    return super.add(info);
  }

  public String toText(String prefix) {
    StringBuffer sbuf = new StringBuffer();
    sbuf.append(prefix);
    sbuf.append(START);
    sbuf.append("memory");
    sbuf.append(LINE);
      
    for(Iterator iter = iterator(); iter.hasNext(); ) {
      MemInfo reg = (MemInfo)iter.next();
      sbuf.append(reg.toText(prefix+TAB));
    }
    sbuf.append(prefix).append(END).append(LINE);
    return sbuf.toString();
  }

  public static void sort(List list) {
    class MemInfoCompare implements Comparator {
      public int compare(Object o1, Object o2) {
	if((o1 instanceof MemInfo) &&
	   (o2 instanceof MemInfo)) 
	  return (((MemInfo) o1).getName()
		  .compareToIgnoreCase(((MemInfo)o2).getName()));
	else
	  throw new ClassCastException("Not a MemInfo!");
      }
    }
    Collections.sort(list, new MemInfoCompare());
  }

}

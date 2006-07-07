/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class IndexConstraint implements Constraint, VHDLout {
  
  LinkedList _ranges;
  boolean is_ranges = true;

  public IndexConstraint(LinkedList ranges) {
    _ranges = ranges;
  }

  public IndexConstraint(SubType sub) {
    this(new LinkedList());
    _ranges.add(sub);
    is_ranges = false;
  }
    
  public IndexConstraint(Range sub) {
    this(new LinkedList());
    is_ranges = true;
    _ranges.add(sub);
  }


  public void add(SubType sub) {
    if (!is_ranges) 
      _ranges.add(sub);
  }

  public void add(Range range) {
    if (is_ranges)
      _ranges.add(range);
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);

    int count = 0;
    for(ListIterator list_iter = _ranges.listIterator(); list_iter.hasNext(); ) {
      VHDLout out = (VHDLout) list_iter.next();
      if (count == 0) {
	s.append("(");
      } else {
	s.append(", ");
      }
      out.toVHDL(s,"");
      count++;
    }
    s.append(")");
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }



}  

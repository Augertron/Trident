/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class IndexedName implements VHDLout, Name {

  private Name _prefix;
  private LinkedList _expressions;

  public IndexedName(Name name, Expression exp) {
    _prefix = name;
    _expressions = new LinkedList();
    _expressions.add(exp);
  }

  public void add(Expression exp) {
    _expressions.add(exp);
  }

  public String getName() { return _prefix.getName(); }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append(_prefix.getName()).append("(");
    int count = 0;
    for(ListIterator iter=_expressions.listIterator(); iter.hasNext();) {
      Expression exp = (Expression)iter.next();
      if (count !=0) s.append(", ");
      exp.toVHDL(s,"");
      count++;
    }
    s.append(")");
    return s;
  }

   
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }
  
}

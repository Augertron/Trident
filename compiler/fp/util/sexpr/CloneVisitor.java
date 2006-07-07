/*
 *
 @LICENSE@
 */


package fp.util.sexpr;

import java.util.*;

class CloneVisitor extends StackVisitor {
  Object clone = null;

  HashMap updates = null;

  CloneVisitor(HashMap up) {
    updates = up;
  }

  public void forSymbol(Symbol that) {
    String s = update(that.toString());
    ((Vector)stack.peek()).add(new Symbol(s));
  }

  public void forVector(Vector that) {
    Vector v = new Vector();
    if (!stack.empty()) {
      ((Vector)stack.peek()).add(v);
    }
    stack.push(v);
  }

  public void forString(String that) {
    String s = update(that);
    ((Vector)stack.peek()).add(that);
  }

  public void forNumber(Number that) {
    ((Vector)stack.peek()).add(that);
  }

  public void forUnknown(Object that) {
    // ??
    ((Vector)stack.peek()).add(that);
  }


  String update(String s) {
    if (updates != null) {
      String result = s;
      for(Iterator iter = updates.keySet().iterator(); iter.hasNext(); ) {
	String var = (String)iter.next();
	String update = (String)updates.get(var);
	result = result.replaceAll("\\$"+var,update);
      }
      return result;
    } else { 
      return s;
    }
  }
					
  Object getClone() { return clone; }

  protected Object pop(Vector v) {
    clone = super.pop(v);
    return clone;
  }

}

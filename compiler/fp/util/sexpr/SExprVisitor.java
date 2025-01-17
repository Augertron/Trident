/*
 *
 @LICENSE@
 */


package fp.util.sexpr;

import java.util.*;

public class SExprVisitor {
  public void forSymbol(Symbol that) {}
  public void forVector(Vector that) {}
  public void forString(String that) {}
  public void forNumber(Number that) {}
  public void forUnknown(Object that) {}

  protected String getLabel(Vector v) {
    if (v.size() > 0) {
      Object o = v.elementAt(0);
      // is this really necessary?
      if (o instanceof Symbol || o instanceof String) {
	return v.elementAt(0).toString();
      }
    }
    return null;
  }

  public void visit(Object expr) {
    if (expr == null)
      return;
    else if (expr instanceof Number) {
      forNumber((Number)expr);
    } else if (expr instanceof Vector) {
      forVector((Vector)expr);
      for(int i=0; i < ((Vector)expr).size(); i++) {
	visit(((Vector)expr).elementAt(i));
      }
    } else if (expr instanceof String) {
      forString((String)expr);
    } else if (expr instanceof SExpr) {
      if (expr instanceof Enumeration) {
	for(Enumeration e = (Enumeration)expr; e.hasMoreElements(); ) {
	  Object element = e.nextElement();
	  visit(e);
	}
      } else if (expr instanceof Cons) {
	Cons c = (Cons) expr;
	System.out.println("Cons ");

	for(Enumeration e = c.elements(); e.hasMoreElements(); ) {
	  Object element = e.nextElement();
	  visit(e);
	}
      } else if (expr instanceof Symbol) {
	forSymbol((Symbol)expr);
      } else {
	System.out.println("SExpr ");
	forUnknown(expr);
      }
    } else {
      forUnknown(expr);
    }
  }
}

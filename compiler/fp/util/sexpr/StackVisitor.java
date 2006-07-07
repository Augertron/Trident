/*
 *
 @LICENSE@
 */


package fp.util.sexpr;

import java.util.*;

public class StackVisitor extends SExprVisitor {
  protected Stack stack = new Stack();

  protected Object pop(Vector v) {
    if (!stack.empty()) 
      return stack.pop();
    else
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
      // this popping is the major difference ...
      pop((Vector)expr);
      
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
        //System.out.println("Cons ");

        for(Enumeration e = c.elements(); e.hasMoreElements(); ) {
          Object element = e.nextElement();
          visit(e);
        }
      } else if (expr instanceof Symbol) {
        forSymbol((Symbol)expr);
      } else {
        //System.out.println("SExpr ");
        forUnknown(expr);
      }
    } else {
      forUnknown(expr);
    }
  }


}

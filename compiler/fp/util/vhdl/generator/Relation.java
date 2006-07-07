/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Relation extends CheckObject implements VHDLout, Operation {
  
  Object _right;
  Object _left;
  String _operator;

  static final Class[]  _classes = { 
    ShiftExpression.class,
    SimpleExpression.class, 
    Term.class, Factor.class, 
    Primary.class 
  };

  static final String[] _ops = { GE, GT, EQ, LE, LT, NE };


  public Relation(Object left, String op, Object right) {
    super(_classes, _ops, left, op, right);
  }

  public Relation(Object left) {
    this(left, null, null);
  }

  protected void addTerm(String op, Object term) { 
    if (objectAllowed(term) && operatorAllowed(op)) {
      _right = term;
      _operator = op;
    }
  }

  protected void addTerm(Object term) {
    if (objectAllowed(term)) 
      _left = term;
  }
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    ((VHDLout)_left).toVHDL(s,"");
    if (_operator != null) {
      s.append(" ").append(_operator);
    }
    if (_right != null) {
      s.append(" ");
      ((VHDLout)_right).toVHDL(s, "");
    }

    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }

  

   public static void main(String args[]) {
    // mult div mod rem

    SimpleName a = new SimpleName("a");
    SimpleName a1 = new SimpleName("a_1");
    SimpleName b = new SimpleName("b");
    SimpleName c = new SimpleName("c");

    System.out.println("Primaries "+a+" "+b+" "+c+"\n");

    Relation se = new Relation(a, LT, b);
    print(se);

    se = new Relation(a, GT, new Paren(new Mult(a,b)));
    print(se);

    se = new Relation(a, ROR, new Paren(new Mult(a,b)));
    print(se);

    se = new Relation(new Paren(new Add(a,b)), NE, c);
    print(se);

    se = new Relation(new Paren(new ShiftExpression(new Mult(a,b), 
						    SRL, 
						    new Paren(new Add(a,b,c)))));
    print(se);

    se = new Relation(new Div(c,b), EQ, new Mult(a,b));
    print(se);

    //Term t3 = new Div(new Paren(new Mult(a,b)), 
    //new Paren(new Mult(c,a1)));

    
  }

  public static void print(Relation f) {
    System.out.println("Relation: "+f);
  }



  
 
}

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class SimpleExpression extends CheckObject implements VHDLout, Operation, Choice, Primary {

  String _sign = EMPTY;
 
  // Terms should be fancier -- but not right now...

  // Set the check arrays
  static final Class[]  _classes = { Term.class, Factor.class, Primary.class };
  static final String[] _ops = { PLUS, MINUS, CONCAT };


  SimpleExpression(Object left, String op, Object right) {
    super(_classes, _ops, left, op, right);
  }


  public SimpleExpression(String sign, Term term) {
    this(term, null, null);
    if (PLUS.equals(sign) || MINUS.equals(sign)) {
      _sign = sign;
    }
  }

  public SimpleExpression(Object o) {
    this(o, null, null);
  }


  class ExpTerm {
    // should this be an interface ??
    String add_operator;
    Object term;

    ExpTerm(String s, Object t) {
      add_operator = s;
      term = t;
    }
  }

  protected void addTerm(String op, Object term) { 
    if (objectAllowed(term) && operatorAllowed(op)) 
      _operands.add(new ExpTerm(op, term)); 
  }

  protected void addTerm(Object term) {
    if (objectAllowed(term)) 
      _operands.add(new ExpTerm(NOP, term));
  }

  public SimpleExpression add(Object term) { 
    addTerm(PLUS, term); 
    return this;
  }

  public SimpleExpression sub(Object term) { 
    addTerm(MINUS, term); 
    return this;
  }

  public SimpleExpression concat(Object term) { 
    addTerm(CONCAT, term);
    return this;
  }

  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    s.append(_sign);
    for(ListIterator list_iter=_operands.listIterator(); list_iter.hasNext();) {
      ExpTerm term = (ExpTerm)list_iter.next();
      if (!EMPTY.equals(_sign)) 
	s.append(" ");
      if (!NOP.equals(term.add_operator)) {
	s.append(" ").append(term.add_operator).append(" ");
      }
      ((VHDLout)term.term).toVHDL(s, "");
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

    SimpleExpression se = new SimpleExpression(a, ADD, new Object());
    print(se);

    se = new SimpleExpression(a, ADD, new Paren(new Mult(a,b)));
    print(se);

    se = new SimpleExpression(new Paren(new Add(a,b)), SUB, c);
    print(se);

    se = new Sub(new Mult(a,b), new Paren(new Add(a,b,c)));
    print(se);

    //Term t3 = new Div(new Paren(new Mult(a,b)), 
    //new Paren(new Mult(c,a1)));

    
  }

  public static void print(SimpleExpression f) {
    System.out.println("SimpleExpression: "+f);
  }


  

}

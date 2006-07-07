/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class And extends Expression {

  public And(Object a, Object b) {
    super(a, AND, b);
  }

  public And(Object a, Object b, Object c) {
    this(new Object[] {a, b, c} );
  }

  public And(Object a, Object b, Object c, Object d) {
    this(new Object[] {a, b, c, d} );
  }

  public And(LinkedList l) {
    super(AND);
    for(ListIterator iter = l.listIterator(); iter.hasNext(); ) {
      addTerm(iter.next());
    }
  }

  public And(Object[] o) {
    super(AND);
    for (int i=0; i < o.length; i++) {
      addTerm(o[i]);
    }
  }

}

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Or extends Expression {

  public Or(Object a, Object b) {
    super(a, OR, b);
  }

  public Or(Object a, Object b, Object c) {
    this(new Object[] {a, b, c} );
  }

  public Or(Object a, Object b, Object c, Object d) {
    this(new Object[] {a, b, c, d} );
  }

  // both of these could use some size checking and an exception.

   public Or(LinkedList l) {
    super(OR);
    for(ListIterator iter = l.listIterator(); iter.hasNext(); ) {
      addTerm(iter.next());
    }
  }

  public Or(Object[] o) {
    super(OR);
    for (int i=0; i < o.length; i++) {
      addTerm(o[i]);
    }
  }



}

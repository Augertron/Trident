/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Xor extends Expression {

  public Xor(Object a, Object b) {
    super(a, XOR, b);
  }

  public Xor(Object a, Object b, Object c) {
    this(new Object[] {a, b, c} );
  }

  public Xor(Object a, Object b, Object c, Object d) {
    this(new Object[] {a, b, c, d} );
  }


  public Xor(Object[] o) {
    super(o[0]); // scary ... 
    setOp(XOR);
    for (int i=1; i < o.length; i++) {
      addTerm(o[i]);
    }
  }

  public Xor(LinkedList l) {
    super(XOR);
    for(ListIterator iter = l.listIterator(); iter.hasNext(); ) {
      addTerm(iter.next());
    }
  }


}

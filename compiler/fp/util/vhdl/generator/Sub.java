/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Sub extends SimpleExpression {


  public Sub(Object a, Object b) {
    super(a, SUB, b);
  }

  public Sub(Term a, Term b, Term c) {
    this(a,b);
    sub(c);
  }


  
  
}

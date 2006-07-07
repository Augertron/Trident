/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Concat extends SimpleExpression {

  public Concat(Object a) {
    super(a);
  }

  public Concat(Object a, Object b) {
    this(a);
    concat(b);
  }

  public Concat(Object a, Object b, Object c) {
    this(a,b);
    concat(c);
  }


  
  
}

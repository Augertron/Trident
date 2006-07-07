/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Add extends SimpleExpression {


  public Add(Object a, Object b) {
    super(a, ADD, b);
  }

  public Add(Object a, Object b, Object c) {
    super(a, ADD, b);
    add(c);
  }


  
  
}

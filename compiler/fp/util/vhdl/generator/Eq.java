/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Eq extends Relation {
  
  public Eq(Object left, Object right) {
    super(left, EQ, right);
  }

}

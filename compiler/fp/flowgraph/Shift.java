/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import fp.util.BooleanEquation;

public class Shift extends Binary {
  
  public static boolean conforms(Operator op) {
    return op.format == Shift_format;
  }
  
}

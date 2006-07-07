/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Mult extends Term implements VHDLout {

  public Mult(Object left, Object right) {
    super(left, Term.MULT, right);
  }

  public Mult(Primary left, Primary right) {
    super(left, Term.MULT, right);
  }

}

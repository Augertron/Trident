/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Div extends Term implements VHDLout {

  public Div(Object left, Object right) {
    super(left, Term.DIV, right);
  }

  public Div(Primary left, Primary right) {
    super(left, Term.DIV, right);
  }

}

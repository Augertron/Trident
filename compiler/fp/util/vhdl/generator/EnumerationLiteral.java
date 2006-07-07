/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class EnumerationLiteral extends Literal {
  private Identifier _identifier;
  private char _char_literal;
  
  public EnumerationLiteral(Identifier id) {
    _identifier = id;
    _string = id.toString();
  }

  public EnumerationLiteral(char c) {
    _char_literal = c;
    _string ="'"+c+"'";
  }

  public Identifier getId() { return _identifier; }

}

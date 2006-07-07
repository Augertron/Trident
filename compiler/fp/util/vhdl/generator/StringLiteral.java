/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class StringLiteral extends Literal {

  public StringLiteral(String string) {
    super();
    _string = "\""+string+"\"";
  }

  
  public static void main(String args[]) {
    Literal l = new StringLiteral("1010101");
    System.out.println("String "+l);
  }

}

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

// Identifier DOES implement VHDLout, so SimpleName does as well, since
// it extends Identifier.  Thus SimpleNames can be used where VHDLout 
// is required.


public class SimpleName extends Identifier implements Name, Primary {

  // This should check to see if it is legal.
  // this can be letter{"_",letter,digit}
  public SimpleName(String s) {
    super(s);
  }

  public SimpleName(Identifier id) {
    super(id.getId());
  }


  public String getName() {
    return toString();
  }

  
  public static void main(String args[]) {
    checkId("Bob");
    checkId("B_56ABab");
    // illegal
    checkId("5_abcde");
    checkId("abcde%");
    checkId("abcde.");
  }

  public static void checkId(String name) {
    System.out.println("SimpleName "+(new SimpleName(name)));
  }


}

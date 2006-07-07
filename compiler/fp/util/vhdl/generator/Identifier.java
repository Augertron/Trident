/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Identifier implements VHDLout, Choice {


  public static final Identifier OTHERS = new Identifier("others");

  // This should check to see if it is legal.
  // this can be letter{"_",letter,digit}
  String _ident;

  public Identifier(String ident) {
    // we check and if it is illegal we throw an exception?
    _ident = ident;
  }

  String getId() { return _ident; }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append(_ident);
    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
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
    System.out.println("Identifier "+(new Identifier(name)));
  }

   
}

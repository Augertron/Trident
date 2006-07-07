/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class BitStringLiteral extends Literal {
  public static final String B = "B";
  public static final String O = "O";
  public static final String X = "X";

  private String _base;
  private String _value;

  public BitStringLiteral(String base, String value) {
    super();
    // this can be B, O or X
    _base = base;

    // this 0-9A-Fa-f atleast
    _value = value;

    _string = _base + "\"" +_value +"\"";

  }


  
  
  public static void main(String args[]) {
    Literal l = new BitStringLiteral(B,"1010101");
    System.out.println("BitString "+l);
    l = new Char('1');
    System.out.println("EnumLiteral "+l);
    l = new Char('0');
    System.out.println("EnumLiteral "+l);
  }



}

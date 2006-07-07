/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class NumericLiteral implements Primary, VHDLout  {

  /*
    Literals can be several different things:
    
    numeric literal
    enumeration literal
    string literal
    bit string literal
    <b>null</b>

  */

  public static final NumericLiteral ONE = new NumericLiteral(1);
  public static final NumericLiteral ZERO = new NumericLiteral(0);


  protected String _string;

  // could accepter other types: float, double, etc.

  public NumericLiteral(String number) {
    _string = number;
  }

  public NumericLiteral(int i) {
    this(i+"");
  }

  public NumericLiteral(Integer i) {
    this(i.toString());
  }

  // this is for Physical Literals
  // this is real weak -- needs fixin'
  public NumericLiteral(int i, Name name) {
    _string = ""+i+" "+name;
  }

  public String getName() {
    return toString();
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append(_string);
    return s;
  }
  
  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }


  


}

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Literal implements Primary, VHDLout  {

  /*
    Literals can be several different things:
    
    numeric literal
    enumeration literal
    string literal
    bit string literal
    <b>null</b>

  */

  protected String _string;

  public Literal() {
    _string = "null";
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

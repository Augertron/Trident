/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class WaveformElement implements VHDLout {

  // expression [ after time_expression ]
  // null [after time_expression ]

  private Expression _value;
  private Expression _time;

  public WaveformElement(Expression value, Expression time) {
    _value = value;
    _time = time;
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    int count = 0;
    if (_value != null) {
      ((VHDLout)_value).toVHDL(s, "");
    } else {
      s.append("null");
    }
    if (_time != null) {
      s.append(" after ");
      ((VHDLout)_time).toVHDL(s, "");
    }
    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }


   
}

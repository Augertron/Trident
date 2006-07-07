/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class ExpressionGroup extends Expression implements Primary, VHDLout {

  VHDLout _expression;

  // this guy really does not care much.
  public ExpressionGroup(VHDLout e) {
    super(e);
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    s.append(" (");
    super.toVHDL(s,"");
    s.append(") ");
    return s;
  }



}

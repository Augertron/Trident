/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class DiscreteRange implements VHDLout {

  private Range _range;
  private SubType _type;

  public DiscreteRange(Range r) {
    _range = r;
  }

  public DiscreteRange(int a, int b) {
    this(new Range(a,b));
  }

  public DiscreteRange(SubType t) {
    _type = t;
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    if (_range != null) 
      _range.toVHDL(s,"");
    else
      _type.toVHDL(s,"");
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }

}


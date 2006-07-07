/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

// Identifier DOES implement VHDLout, so SimpleName does as well, since
// it extends Identifier.  Thus SimpleNames can be used where VHDLout 
// is required.


public class SliceName implements Name, Primary, VHDLout {

  // prefix
  private Name _name;
  private FunctionCall _function;
  // discrete range
  private DiscreteRange _range;


  public SliceName(Name n, DiscreteRange r) {
    _name = n;
    _range = r;
  }

  public SliceName(Name n, int a, int b) {
    this(n, new DiscreteRange(a,b));
  }

  public SliceName(FunctionCall f, DiscreteRange r) {
    this((Name)null, r);
    _function = f;
  }

  public String getName() {
    return _name.getName();
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    if (_name != null)
      s.append(_name.getName());
    else
      _function.toVHDL(s,"");
    s.append("(");
    _range.toVHDL(s,"");
    s.append(")");

    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }
  
}

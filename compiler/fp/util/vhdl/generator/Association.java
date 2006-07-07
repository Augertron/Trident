/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;


public class Association implements VHDLout {
  private FormalPart _formal;
  private ActualPart _actual;

  public Association(FormalPart fp, ActualPart ap) {
    _formal = fp;
    _actual = ap;
  }

  public Association(ActualPart ap) {
    this((FormalPart)null, ap);
  }

  public Association(Name name) {
    this((FormalPart)null, new ActualPart(name));
  }

  public Association(Name formal, Object actual) {
    this(new FormalPart(formal), new ActualPart(new Expression(actual)));
  }

  public Association(Name formal, Name actual, Expression e) {
    this(new FormalPart(formal), new ActualPart(actual, e));
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // these extra carriage returns may not bee the best idea...
    s.append(pre);
    if (_formal != null) {
      _formal.toVHDL(s,pre);
      s.append(" => ");
    }
    _actual.toVHDL(s,pre);
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }

}

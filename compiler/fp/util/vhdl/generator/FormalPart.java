/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;


public class FormalPart implements VHDLout {

  Name _designator;
  Name _function_type;

  public FormalPart(Name functor, Name design) {
    _designator = design;
    _function_type = functor;
  }

  public FormalPart(Name designator) {
    this(null, designator);
  }

 public StringBuffer toVHDL(StringBuffer s, String pre) {
    // these extra carriage returns may not bee the best idea...
    s.append(pre);
    if (_function_type != null) 
      s.append(_function_type).append("( ");
    s.append(_designator);
    if (_function_type != null) s.append(" )");
	       
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }



}

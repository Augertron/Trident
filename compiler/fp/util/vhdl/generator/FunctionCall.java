/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class FunctionCall implements Primary, VHDLout {

  private Name _name;
  private AssociationList _list;
  private HashMap _hash;

  public FunctionCall(Name name, AssociationList list) {
    _name = name;
    if (list == null) {
      _list = new AssociationList(false);
    } else {
      _list = list;
      _list._wrap = false;
    }
  }

  public FunctionCall(Name name) {
    this(name, null);
  }

  public void add(Association a) {
    _list.add(a);
  }

  public void add(Name name) {
    _list.add(new ActualPart(name));
  }

  public void add(Expression exp) {
    _list.add(new ActualPart(exp));
  }

  public void add(ActualPart ap) {
    _list.add(ap);
  }
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    ((VHDLout)_name).toVHDL(s,"");

    if (_list.size() > 0) {
      s.append("( ");
      _list.toVHDL(s,pre);
      s.append(" )");
    }
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



  

}

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Signal implements BlockItem, EntityItem, VHDLout {

  private LinkedList _identifiers;
  private SubType _type;
  private Identifier _kind;
  private Expression _expression;

  public Signal(LinkedList ids, SubType type, Identifier kind,
			   Expression exp) {
    if (ids != null) 
      _identifiers = ids;
    else 
      _identifiers = new LinkedList();
    _type = type;
    _kind = kind;
    _expression = exp;
  }

  public Signal(Identifier id, SubType type, Expression exp) {
    this(null, type, null, exp);
    _identifiers.add(id);
  }
			      

  public Signal(Identifier id, SubType type) {
    this(id, type, null);
  }

  public LinkedList getNames() {
    LinkedList names = new LinkedList();
    for(ListIterator list_iter = _identifiers.listIterator(); 
	list_iter.hasNext();) {
      Identifier id = (Identifier)list_iter.next();
      names.add(id.getId());
    }
    return names;
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("signal ");
    int count = 0;
    for(ListIterator list_iter = _identifiers.listIterator(); list_iter.hasNext();) {
      Identifier id = (Identifier)list_iter.next();
      if (count != 0) 
	s.append(", ");
      id.toVHDL(s,"");
    }
    s.append(" : ");
    _type.toVHDL(s,"");
    if (_kind != null) {
      s.append(" ");
      _kind.toVHDL(s,"");
      s.append(" ");
    }
    if (_expression != null) {
      s.append(" := ");
      _expression.toVHDL(s,"");
    }
    s.append(";\n");
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }


}


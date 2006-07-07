/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class InterfaceVariable implements VHDLout, InterfaceDeclaration {

  private IdentifierList _identifiers;
  private Mode _mode;
  private SubType _type;
  private Expression _expression;

  public InterfaceVariable(IdentifierList ids, Mode mode, SubType type,
			   Expression exp) {
    if (ids != null) 
      _identifiers = ids;
    else
      _identifiers = new IdentifierList();

    _mode = mode;
    _type = type;
    _expression = exp;
  }

  public InterfaceVariable(IdentifierList ids, Mode mode, SubType type) {
    this(ids, mode, type, null);
  }
  
  public InterfaceVariable(IdentifierList ids, SubType type) {
    this(ids, null, type, null);
  }

  public InterfaceVariable(Identifier id, SubType type) {
    this(new IdentifierList(id), null, type, null);
  }

  public void addIds(Identifier id) {
    _identifiers.add(id);
  }
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // label top.
    s.append(pre);
    // s.append("signal "); // ?? is allowed
    // s.append("variable ");
    // ModelSim does not like variable.
    _identifiers.toVHDL(s,"");
    s.append(" : ");
    if (_mode != null) {
      _mode.toVHDL(s,"");
      s.append(" ");
    }
    _type.toVHDL(s,"");
    if (_expression != null) {
      _expression.toVHDL(s,"");
    }

    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



}  

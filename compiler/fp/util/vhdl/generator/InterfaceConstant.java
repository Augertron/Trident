/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class InterfaceConstant implements VHDLout, InterfaceDeclaration {

  private IdentifierList _identifiers;
  private SubType _type;
  private Expression _expression;

  public InterfaceConstant(IdentifierList ids, SubType type, Expression exp) {
    if (ids != null) 
      _identifiers = ids;
    else
      _identifiers = new IdentifierList();
    _type = type;
    _expression = exp;
  }

  public InterfaceConstant(IdentifierList ids, SubType type) {
    this(ids, type, null);
  }
  
  public InterfaceConstant(Identifier id, SubType type) {
    this(new IdentifierList(id), type, null);
  }

  
  public InterfaceConstant(Identifier id, SubType type, Expression exp) {
    this(new IdentifierList(id), type, exp);
  }

  public void addIds(Identifier id) {
    _identifiers.add(id);
  }
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // label top.
    s.append(pre);
    // s.append("signal "); // ?? is allowed
    s.append("constant ");
    _identifiers.toVHDL(s,"");
    s.append(" : in ");
    _type.toVHDL(s,"");
    if (_expression != null) {
      s.append(" := ");
      _expression.toVHDL(s,"");
    }

    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



}  

/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class InterfaceSignal implements VHDLout, InterfaceDeclaration {

  private IdentifierList _identifiers;
  private Mode _mode;
  private SubType _type;
  private boolean _bus;
  private Expression _expression;

  public InterfaceSignal(IdentifierList ids, Mode mode, SubType type,
				    boolean bus, Expression exp) {
    if (ids != null) 
      _identifiers = ids;
    else
      _identifiers = new IdentifierList();

    _mode = mode;
    _type = type;
    _bus = bus;
    _expression = exp;
  }

  public InterfaceSignal(IdentifierList ids, Mode mode, SubType type, 
				    Expression exp) {
    this(ids, mode, type, false, exp);
  }

  public InterfaceSignal(IdentifierList ids, Mode mode, SubType type) {
    this(ids, mode, type, false, null);
  }
  
  public InterfaceSignal(IdentifierList ids, SubType type) {
    this(ids, null, type, false, null);
  }

  public InterfaceSignal(Identifier id, SubType type) {
    this(new IdentifierList(id), null, type, false, null);
  }

  public InterfaceSignal(Identifier id, Mode mode, SubType type) {
    this(new IdentifierList(id), mode, type, false, null);
  }



  public void addIds(Identifier id) {
    _identifiers.add(id);
  }
  
  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // label top.
    s.append(pre);
    // s.append("signal "); // ?? is allowed
    s.append("signal ");
    _identifiers.toVHDL(s,"");
    s.append(" : ");
    if (_mode != null) {
      _mode.toVHDL(s,"");
      s.append(" ");
    }
    _type.toVHDL(s,"");
    if (_bus) {
      s.append(" bus ");
    }
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

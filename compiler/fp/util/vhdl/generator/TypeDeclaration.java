/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class TypeDeclaration implements VHDLout, BlockItem, EntityItem {

  /*
    Two kinds full type declaration and incomplete.  Not super different.
    Incomplete just has less information.  I combine them here.  If it
    really matters I can provide a wrapper class for both.
  */

  private Identifier _name;
  private TypeDefinition _def;
  
  private SubType _type;


  public TypeDeclaration(Identifier type, TypeDefinition def) {
    _name = type;
    _def = def;
  }

  // this may not be fancy enough ...
  public SubType getType() {
    if (_type == null) {
      _type = new SubType(new SimpleName(_name));
    }
    return _type;
  }

  public LinkedList getNames() {
    LinkedList names = new LinkedList();
    names.add(_name.getId());
    return names;
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // label top.

    s.append(pre).append("type ");
    _name.toVHDL(s,"");
    if (_def != null) {
      s.append(" is ");
      ((VHDLout)_def).toVHDL(s, "");
    }
    s.append(";\n"); 
    
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



}  

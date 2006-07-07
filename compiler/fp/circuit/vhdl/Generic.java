/*
 *
 @LICENSE@
 */


package fp.circuit.vhdl;

import fp.util.vhdl.generator.Expression;
import fp.util.vhdl.generator.InterfaceConstant;
import fp.util.vhdl.generator.Primary;
import fp.util.vhdl.generator.SimpleName;
import fp.util.vhdl.generator.SubType;

public class Generic {
  public Generic(String s) {
    name = new SimpleName(s);
  }

  SubType type;
  Primary value;
  SimpleName name;
  
  InterfaceConstant getInterfaceConstant() {
    return new InterfaceConstant(name, type, new Expression(value));
  }

}

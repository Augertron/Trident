/*
 *
 @LICENSE@
 */


package fp.circuit.vhdl;

import fp.circuit.PortTag;

import fp.util.vhdl.generator.IndexConstraint;
import fp.util.vhdl.generator.InterfaceSignal;
import fp.util.vhdl.generator.Mode;
import fp.util.vhdl.generator.Primary;
import fp.util.vhdl.generator.SimpleName;
import fp.util.vhdl.generator.SubType;

public class ParsePort {
  public ParsePort(String s) {
    if ("in".equals(s)) {
      mode = Mode.IN;
    } else if ("out".equals(s)) {
      mode = Mode.OUT;
    } else if ("inout".equals(s)) {
      mode = Mode.INOUT;
    } else {
      System.err.println("Unparsed Mode "+s);
    }
  }

  Mode mode;
  // just the basic type...
  SubType type;
  
  Primary value;

  private SimpleName name;
  private String _name;

  String tag;
  String open;
  IndexConstraint size;
  // this is for me.
  int width;
  
  InterfaceSignal getInterfaceSignal() {
    // this is more complicated ...
    if (size != null) {
      if (type == SubType.STD_LOGIC) {
	type = new SubType(new SimpleName("std_logic_vector"),
			   size);
      } else {
	System.err.println("Ach -- not the right type errror.");
	System.exit(-1);
      }
    }
    return new InterfaceSignal(name, mode, type);
  }

  void setName(String new_name) {
    _name = new_name;
    name = new SimpleName(new_name);
  }

  public String getName() { return _name; }
  public SimpleName getSimpleName() { return name; }

  public int getDirection() { 
    if (mode == Mode.IN) {
      return PortTag.IN;
    } else if (mode == Mode.OUT) {
      return PortTag.OUT;
    } else if (mode == Mode.INOUT) {
      return PortTag.INOUT;
    }
    return 0;
  }

  public int getWidth() { return width; }

}

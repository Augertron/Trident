/*
 *
 @LICENSE@ 
 */ 
package fp.circuit.vhdl;

import java.util.Iterator;

import fp.circuit.Net;
import fp.circuit.Circuit;


import fp.util.vhdl.generator.*;

public class VHDLNet extends Net {
  
  Bus _bus;
  SimpleName _name;

  VHDLNet(Circuit parent, String name) {
    super(parent, name);
  }


  public void build(String name, Object[] arg_o) {
    //System.out.println("Building VHDLNet "+getName());

    DesignUnit du = ((VHDLCircuit)getParent()).getVHDLParent();

    LibraryUnit lu = du.getLibraryUnit();
    //Entity e = lu.getEntity();

    Architecture a = lu.getArchitecture();
    // this is assuming the widths are correct in the wires.
    // the width = 0 thing is a kludge...
    if (getWidth() == 1
	|| getWidth() == 0) {
      a.addItem(new Signal(getVHDLName(), SubType.STD_LOGIC));
    } else {
      a.addItem(new Signal(getVHDLName(), SubType.STD_LOGIC_VECTOR(getWidth() - 1, 0)));
    }
  }

  public Bus getBus() {
    if (_bus == null) {
      if (getWidth() == 1
	  || getWidth() == 0) {
	_bus = new Bus(getName());
      } else {
	_bus = new Bus(getName(), getWidth() - 1, 0);
      }
    }
    return _bus;
  }
    
  public SimpleName  getVHDLName() {
    if (_name == null) {
      // this is probably expensive
      String name = getName();
      if (name.matches("^[a-zA-Z][a-zA-Z0-9_]*$")) {
	if (name.indexOf("__") < 0) {
	  _name = new SimpleName(name);
	} else {
	  _name = new SimpleName('\\'+name+'\\');
	}
      } else {
	_name = new SimpleName('\\'+name+'\\');
      }
    }
    return _name;
  }

}

/*
 *
 @LICENSE@
 */


package fp.circuit.vhdl;

import java.util.*;

import fp.util.vhdl.generator.Component;
import fp.util.vhdl.generator.InterfaceConstant;
import fp.util.vhdl.generator.InterfaceSignal;
import fp.util.vhdl.generator.SimpleName;


public class LibObject {

  private Library _library;
  private String _name;
  private String _lib_name;

  private HashMap _ports;
  private HashMap _generic_map;
  private HashMap _port_map;

  private VHDLModule _vhdl_module;

  public LibObject(String in_name, String lib_name, Library lib) {
    _library = lib;
    _name = in_name;
    _lib_name = lib_name;
    _ports = new HashMap();
    _generic_map = new HashMap();
    _port_map = new HashMap();

    String libobject = in_name;
    String objectname = lib_name;

    SimpleName name = new SimpleName(libobject);
    Component comp = new Component(name);

    _vhdl_module = new VHDLModule(libobject, objectname,
				  lib.name, lib.include, comp);
    
  }

  VHDLModule getVHDLModule() { return _vhdl_module; }

  void addPortMap(String first, Object second) {
    // second can be a String or a Number.
    // do I need two copies?
    _vhdl_module.getPortMap().put(first, second);
  }

  void addGenericMap(String first, Object second) {
    _vhdl_module.getGenericMap().put(first, second);
  }

  void addGeneric(Generic gen) {
    InterfaceConstant ic = gen.getInterfaceConstant();
    _vhdl_module.getComponent().addGeneric(ic);
  }

  void addPort(ParsePort port) {
    InterfaceSignal is = port.getInterfaceSignal();
    // this is more than just this...
    // maps, gmaps, open, ...
    //System.out.println("Adding port "+port);
    _ports.put(port.getName(), port); // this is not two copies, because ParsePort and
		      // InterfaceSignal are different.

    _vhdl_module.getComponent().addPort(is);
    _vhdl_module.getOpenMap().put(port.getSimpleName().toString(), port.open);
    _vhdl_module.getTagMap().put(port.getSimpleName().toString(), port.tag);
      // IDs ???
  }

  // this could be dangerous...
  public HashMap getPorts() { return _ports; }
    


}

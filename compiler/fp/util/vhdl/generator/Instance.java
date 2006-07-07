/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

/*
  This should be fixed to more closely match the VHDL93 syntax file.

*/


public class Instance extends Statement implements VHDLout, ConcurrentStatement {

  // label exists in statement;
  //Identifier _label;
  // instatiated unit
  private SimpleName unit_name;

  private GenericMap _gen_map;
  private PortMap _port_map;
  private String _type;


  public Instance(Identifier label, Component comp) {
    super("",label);
    // need to get stuff from Component -- no accessors, now.
    _type = "component";

    // using the component was less useful than I thought.
    unit_name = new SimpleName(comp.getId());
    PortList port_list = comp.getPorts();
    if (port_list.size() > 0) 
      _port_map = new PortMap();
    GenericList gen_list = comp.getGenerics();
    if (gen_list.size() > 0) 
      _gen_map = new GenericMap();
  }

  public Instance(Identifier label, Entity entity) {
    super("",label);
    _type = "entity";

    unit_name = new SimpleName(label);
    //PortList port_list = comp.getPorts();
    //GenericList gen_list = comp.getGenerics();

  }

  /*
    // this will need to be programmed when there 
    // actually is a configuration.
  public Instance(Configuration conf) {
  }
  */

  public void setPortMap(PortMap pm) {
    _port_map = pm;
  }

  public void setGenericMap(GenericMap gm) {
    _gen_map = gm;
  }

  public void addPortMap(Association a) {
    _port_map.add(a);
  }

  public void addPortMap(Name a, Object b) {
    _port_map.add(new Association(a,b));
  }

  public void addPortMap(Name a, Name b, Expression e) {
    _port_map.add(new Association(a,b,e));
  }

  public void addGenericMap(Association a) {
    _gen_map.add(a);
  }

  public void addGenericMap(Name a, Object b) {
    _gen_map.add(new Association(a, b));
  }

  public void addGenericMap(Name a, Name b, Expression e) {
    _gen_map.add(new Association(a, b, e));
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    getLabel().toVHDL(s,"");
    s.append(" : ").append(_type).append(" ");
    unit_name.toVHDL(s,"");
    if (_gen_map != null) {
      s.append("\n");
      _gen_map.toVHDL(s, pre+TAB);
    }
    if (_port_map != null) {
      s.append("\n");
      _port_map.toVHDL(s, pre+TAB);
    }
    // this may need help.
    s.append(";\n");
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }


}

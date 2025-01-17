/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;


import java.util.*;


public class Entity implements VHDLout {

  private Identifier _identifier;
  // entity header
  private GenericList _generics;
  private PortList _ports;
  private EntityItemList _items;

  // entity statememt list --which contains:
  // concurrent assertions, passive statements


  public Entity(Identifier identifier, GenericList generics,
		PortList ports, EntityItemList items) {
    _identifier = identifier;

    if (generics != null) 
      _generics = generics;
    else
      _generics = new GenericList();
    if (ports != null) 
      _ports = ports;
    else
      _ports = new PortList();
    
    if (items != null) 
      _items = items;
    else
      _items = new EntityItemList();
  }

  
  public Entity(Identifier identifier, GenericList generics,
		PortList ports) {
    this(identifier, generics, ports, null);
  }

  public Entity(Identifier identifier, PortList ports) {
    this(identifier, null, ports, null);
  }

  public Entity(Identifier identifier) {
    this(identifier, null, null, null);
  }
    


  GenericList getGenerics() { return _generics; }
  public void addGeneric(InterfaceDeclaration s) { _generics.add(s); }

  PortList getPorts() { return _ports; }
  public void addPort(InterfaceDeclaration s) { _ports.add(s); }

  EntityItemList getItems() { return _items; }
  public void addItem(EntityItem s) { _items.add(s); }

   public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("entity ");
    _identifier.toVHDL(s,"");
    s.append(" is\n");
    if (_generics.size() > 0)
      _generics.toVHDL(s,pre+TAB);
    if (_ports.size() > 0)
      _ports.toVHDL(s,pre+TAB);
    if (_items.size() > 0)
      _items.toVHDL(s,pre+TAB);

    // statement list would go here...

    s.append(pre).append("end entity ");
    _identifier.toVHDL(s,"");
    s.append(";\n");
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }


}

/*
 *
 @LICENSE@ 
 */ 
package fp.circuit.vhdl;

import java.util.*;

import fp.circuit.*;
import fp.util.vhdl.generator.*;




public class VHDLMemory extends Memory {

  VHDLMemory(Circuit parent, String name, int width, 
	     int address_width, int[] value) {
    super(parent, name, width, address_width, value);
  }

  /*
    What does the build need to do and know?

    - Remember that there will be a backside bus 
    - This bus needs to be pushed up and out of the 
    higher level cell.

  */

  public void build(String name, Object[] arg_o) {
    System.out.println("Building VHDLMemory");

    // okay, now the work starts.


    VHDLCircuit parent = (VHDLCircuit)getParent();

    /*
      DesignUnit du = parent.getVHDLParent();
      LibraryUnit lu = du.getLibraryUnit();
      //Entity e = lu.getEntity();
      Architecture a = lu.getArchitecture();
    */
    
    Circuit my_circuit = makeVHDLCircuit(parent);
    // add some nets ??
    my_circuit.build(name, arg_o);

    // this is where it is different ???
    // in FSM I build inside the DU, but for 
    // this I insert a file and should be done....
    
    DesignUnit du = ((VHDLCircuit)my_circuit).getVHDLParent();
    
    LibraryUnit lu = du.getLibraryUnit();
    Architecture a = lu.getArchitecture();

    SimpleName i_name = new SimpleName(getRefName());
    SimpleName o_name = new SimpleName(getObjectName());
    // Component -- identifier
    // generics
    // ports 
    


    HashMap ports = getPortMap();
    //Object[] parameters = getParameters();
    // slightly dangerous ...
    //SimpleName vhdl_obj_name = new SimpleName((String)parameters[0]);
    //HashMap generics = (HashMap)parameters[1];

    //Component comp = new Component(vhdl_obj_name);

    /*
    PortMap inst_ports = new PortMap();
    GenericMap inst_generics = new GenericMap();
    

    for(Iterator p_iter = ports.keySet().iterator(); p_iter.hasNext(); ) {
      String wire = (String)p_iter.next();
      PortInfo pi = (PortInfo)ports.get(wire);
      
      Mode m;
      if (pi.getType() == PortInfo.IN) 
	m = Mode.IN;
      else if (pi.getType() == PortInfo.OUT) 
	m = Mode.OUT;
      else 
	m = Mode.INOUT;

      SubType t;
      if (pi.getWidth() > 1) 
	t = SubType.STD_LOGIC_VECTOR(pi.getWidth() - 1, 0);
      else
	t = SubType.STD_LOGIC;

      SimpleName port_name = new SimpleName(pi.getName());
      InterfaceSignal signal = new InterfaceSignal(port_name, m, t);
						   
      comp.addPort(signal);
      inst_ports.add(new Association(port_name, new SimpleName(wire) ));
    }

    for (Iterator g_iter = generics.keySet().iterator(); g_iter.hasNext(); ) {
      String g_name = (String) g_iter.next();
      SimpleName generic_name = new SimpleName(g_name);
      Object o = generics.get(g_name);
      
      SubType t;
      if (o instanceof Integer) {
	t = SubType.INTEGER;
	Expression e = new Expression(new NumericLiteral((Integer)o));
	inst_generics.add(new Association(generic_name, e));
      } else if (o instanceof String) {
	t = SubType.CHARACTER;
	Name p = new SimpleName(o.toString());
	inst_generics.add(new Association(generic_name, p));
      } else if (o instanceof Boolean) {
	t = SubType.BOOLEAN;
	// this may be wronge
	Name p = new SimpleName(o.toString());
	inst_generics.add(new Association(generic_name, p));
      } else {
	t = new SubType(new SimpleName(o.toString()));
	Name p = new SimpleName(o.toString());
	inst_generics.add(new Association(generic_name, p));
      }
      

      InterfaceVariable var = new InterfaceVariable(generic_name, t);
      comp.addGeneric(var);
    }

    System.out.println(" ports "+ports);

    a.addItem(comp);

    Instance instance = new Instance(i_name, comp);
    
    instance.setPortMap(inst_ports);
    instance.setGenericMap(inst_generics);

    a.addStatement(instance);

    du = new DesignUnit("custom",getObjectName());
    ((VHDLCircuit)my_circuit).getDesignFile().addDesignUnit(du);
    //du.setExternalFile(getObjectName());
    */
  }


  VHDLCircuit makeVHDLCircuit(Circuit parent) {
    VHDLCircuit circuit = new VHDLCircuit(parent, null, getRefName());
    
    for(Iterator iter=getInPorts().iterator(); iter.hasNext(); ) {
      PortTag in = (PortTag)iter.next();
      int size = in.getWidth();
      String in_net = in.getNet().getName();
      circuit.insertInPort(in_net,in_net,in_net,size);
    }

    for(Iterator iter=getOutPorts().iterator(); iter.hasNext(); ) {
      PortTag out = (PortTag)iter.next();
      int size = out.getWidth();
      String out_net = out.getNet().getName();
      circuit.insertOutPort(out_net,out_net,out_net,size);
    }

    return circuit;
  }



}

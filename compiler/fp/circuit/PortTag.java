/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

import fp.util.Nameable;
// should this be in util?

public class PortTag extends Nameable implements Direction {
  
  //public final static int IN = 0;
  //public final static int OUT = 1;
  //public final static int INOUT = 2;

  public static final String[] port_name = {"IN", "OUT", "INOUT" };

  // There parents are nodes -- they are subjegated to Node underlings
  private Node _parent;
  private Net _net;
  private int _type;
  private int _width;

  
  public PortTag(Node parent, String name, int type, int width, Net net) {
    super(name);
    _parent = parent;
    _type = type;
    _width = width;
    _net = net;
  }

  public PortTag(Node parent, String name, int type, int width) {
    this(parent, name, type, width, null);
  }

  public Net getNet() { return _net; }
  void setNet(Net e) { _net = e; }

  public Node getParent() { return _parent; } 
  void setParent(Node p) { _parent = p; }

  public int getType() { return _type; }

  public String getTypeString() {
    if (_type >= IN && _type <= INOUT) 
      return port_name[_type];
    else 
      return null;
  }

  public int getWidth() { return _width; }
  void setWidth(int w) { _width = w; }

  public String toString() {
    StringBuffer sbuf = new StringBuffer("Port ");
    sbuf.append(getName()).append(" (").append(_width);
    sbuf.append(") : ").append(getTypeString());
    return sbuf.toString();
  }


  public String toDot() {
    return "<"+getName()+"> "+getName()+" : "+_width+" ";
  }
                                                                                    
}

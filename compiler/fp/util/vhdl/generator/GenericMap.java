/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

public class GenericMap implements VHDLout {

  private AssociationList _list; 
  private static final String _type = new String("generic");

  public GenericMap() {
    _list = new AssociationList();
  }

  public GenericMap(AssociationList list) {
    this();
    _list = list;
  }

  public void add(Association a) {
    _list.add(a);
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append(_type +" map(\n");
    _list.toVHDL(s,pre+TAB);
    s.append("\n");
    s.append(pre).append(")");
    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }



}  

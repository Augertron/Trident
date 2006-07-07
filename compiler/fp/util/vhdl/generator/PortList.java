/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

public class PortList extends InterfaceList {

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("port(\n");
    super.toVHDL(s,pre+TAB);
    s.append(pre).append(");\n");
    return s;
  }
  

}  

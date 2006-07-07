/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

public class GenericList extends InterfaceList {

  // This is also a generic clause, since a generic list ist just 
  // an InterfaceList.

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("generic(\n");
    super.toVHDL(s,pre+TAB);
    s.append(pre).append(");\n");
    return s;
  }
  

}  

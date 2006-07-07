/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

abstract public class Block extends VHDLBase implements VHDLout {

  private String _type_name;

  Block(String name, String type_name) {
    super(name);
    _type_name = type_name;
  }

  void setType(String type) { _type_name = type; }
  public String getType() { return _type_name; }

  protected void appendName(StringBuffer sbuf, String pre) { 
    sbuf.append(pre).append(getType()).append(" ");
    sbuf.append(getName()).append("\n");
  }

  protected void appendHeader(StringBuffer sbuf, String pre) {
    super.appendHeader(sbuf, pre);
    appendName(sbuf, pre);
  }

  protected void appendFooter(StringBuffer sbuf, String pre) {
    appendEnd(sbuf, pre);
    super.appendFooter(sbuf, pre);
  }

  /*
  abstract protected void appendBody(StringBuffer sbuf, String tabs);
    sbuf.append(tabs).append("\n");
  }
  */

  protected void appendEnd(StringBuffer sbuf, String pre) {
    sbuf.append(pre).append("end ").append(getName()).append(";\n");
  }




}

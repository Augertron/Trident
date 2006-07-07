/*
 *
 @LICENSE@
 */

package fp.hwdesc;

import java.util.*;

public class Platform extends BaseConfig {
  String hw_file_name;
  String interface_file_name;

  public Platform() { }

  void setHWFileName(String s) { hw_file_name = s; }
  public String getHWFileName() { return hw_file_name; }

  void setInterfaceFileName(String s) { interface_file_name = s; }
  public String getInterfaceFileName() { return interface_file_name; }

  public String toString() {
    StringBuffer sbuf = new StringBuffer("(platform ");
    sbuf.append(name).append("\n");
    if (isDefault()) {
      sbuf.append("\t(default yes)\n");
    }
    sbuf.append("\t(name ").append(name).append(" )\n");
    sbuf.append("\t(class ").append(class_name).append(" )\n");
    sbuf.append("\t(hardware ").append(hw_file_name).append(" )\n");
    sbuf.append("\t(interface ").append(interface_file_name).append(" )\n");
    sbuf.append(")\n");
    return sbuf.toString();
  }
  
}

/*
 *
 @LICENSE@
 */

package fp.hwdesc;

import java.util.*;

public class Library extends BaseConfig {
  String short_name;
  String file_name;
  LinkedList required_files;

  public Library() { 
    required_files = new LinkedList();
  }

  void setShortName(String s) { short_name = s; }
  public String getShortName() { return short_name; }

  void setFileName(String s) { file_name = s; }
  public String getFileName() { return file_name; }

  void addRequiredFile(String s) { required_files.add(s); }
  public LinkedList getRequiredFiles() { return required_files; }

  public String toString() {
    StringBuffer sbuf = new StringBuffer("(library ");
    sbuf.append(name).append("\n");
    if (isDefault()) {
      sbuf.append("\t(default yes)\n");
    }
    sbuf.append("\t(name ").append(name).append(" )\n");
    if (short_name != null) {
      sbuf.append("\t(short_name ").append(short_name).append(" )\n");
    }
    sbuf.append("\t(file ").append(file_name).append(" )\n");
    sbuf.append("\t(class ").append(class_name).append(" )\n");

    for(Iterator iter=required_files.iterator(); iter.hasNext(); ) {
      String _file = (String)iter.next();
      sbuf.append("\t(require ").append(_file).append(" )\n");
    }
    sbuf.append(")\n");
    return sbuf.toString();
  }
}

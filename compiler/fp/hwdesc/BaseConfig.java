/*
 *
 @LICENSE@
 */

package fp.hwdesc;

import java.util.*;

public class BaseConfig {
  boolean is_default = false;
  String name;
  String class_name;

  void setDefault(boolean b) { is_default = b; }
  public boolean isDefault() { return is_default; }

  void setName(String s) { name = s; }
  public String getName() { return name; }

  void setClassName(String s) { class_name = s; }
  public String getClassName() { return class_name; }
  
}

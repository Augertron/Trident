/*
 *
 @LICENSE@
 */


package fp.hwdesc;

public abstract class Base {
  public String name = null;
  public int count = 0;
  public int size = 0;
  public int width = 0;
  public int depth = 0;
  public String type = "none";
  public int typeCode = 0;
  public String[] typeName = {};

  public Base() {}

  public void setTypeCode() {
    for (int i = 0; i < typeName.length; i++) {
      if (type.compareTo(typeName[i]) == 0) {
	typeCode = i;
	return;
      }
    }
    System.out.println("Description for " + name + " contains invalid type:  " + type);
  }
}

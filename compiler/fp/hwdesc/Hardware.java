/*
 *
 @LICENSE@
 */


package fp.hwdesc;
import java.util.*;

public class Hardware extends Base {
  public ArrayList chip;
  public Class chipClass = Chip.class;

  public static final int NONE = 0;

  public Hardware() {
    chip = new ArrayList();
    typeName = new String[] {"none"};
  }
  

  public String toString() {
    StringBuffer sbuf = new StringBuffer("(hardware ");
    
    sbuf.append(name).append("\n");

    for(Iterator iter = chip.iterator(); iter.hasNext(); ) {
      sbuf.append(((Chip)iter.next()).toString());
    }

    sbuf.append(")\n");
    return sbuf.toString();
  }


} 

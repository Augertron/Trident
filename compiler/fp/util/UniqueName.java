/*
 *
 @LICENSE@
 */


package fp.util;

import java.util.Hashtable;
import java.lang.Integer;

import fp.flowgraph.Operand;

/** A hashtable that will create a unique name for any given arbitrary
    name.
**/
public class UniqueName extends Hashtable {

  protected static final int INITIAL_INT = 0;
  protected static final String BASESTRING_INT = "I";
  protected String SEPARATOR = "_";

  public UniqueName() {
    super();
  }
  
  public UniqueName( String separator ) {
    this();
    SEPARATOR = separator;
  }

  public String getUniqueName(String base) {
    String basename = base;

    if (basename == null) {
      basename = BASESTRING_INT;
    }

    Integer i = (Integer) get(basename);

    if (i==null) {
      put(basename,new Integer(INITIAL_INT+1));
      return uniqueMergedName(basename,INITIAL_INT);
    } else {
      int index = i.intValue();
      String newname = uniqueMergedName(basename,index);
      put(basename,new Integer(index+1));
      return newname;
    }
  }

  public int getNextIndex(String name) {
    Integer i = (Integer) get(name);
    if (i == null) {
      return INITIAL_INT;
    }
    return i.intValue();
  }

  protected String uniqueMergedName(String basename,int index) {
    String newstring = new String(basename+SEPARATOR+index);
    // check to see if new string exists (i.e. someone added a_0);
    if (get(newstring) != null) {
      return uniqueMergedName(new String(basename+SEPARATOR),index);
    }
    return newstring;
  }

  public static void main(String args[]) {
    UniqueName u = new UniqueName();
    testName(u,"a");
    testName(u,"a_0");
    testName(u,"a");
    testName(u,"b_0");
    testName(u,"b__0");
    testName(u,"b");
    testName(u,null);
    testName(u,null);
  }
  public static void testName(UniqueName u,String name) {
    System.out.println("name="+name+" newname="+u.getUniqueName(name));
  }


}

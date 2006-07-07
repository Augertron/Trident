/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Ne extends Relation {
  
  public Ne(Object left, Object right) {
    super(left, NE, right);
  }

}

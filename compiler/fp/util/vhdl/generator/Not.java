/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Not extends Factor implements VHDLout {


  public Not(Primary left) {
    super(Factor.NOT, left);
  }

}

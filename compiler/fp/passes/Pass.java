/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

public abstract class Pass {
  private static final LinkedList empty_list = new LinkedList();
  PassManager pm;


  Pass(PassManager pm) {
    // ?? what does this accomplish -- I guess we can
    // have user passes included ...
    pm.register(this);
    this.pm = pm;
  }



  public void setup() {};
  abstract public String name();

  public LinkedList requires() { return empty_list;  }

  public boolean modifiesCFG() { return true; }

  // debug
  // timing/cost analysis
  // registration.

}
    

/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.LinkedList;

public interface UtilPass {
  
  public boolean optimize();

  public void setup();

  public String name();

  // for dependence creation ?
  public LinkedList requires(); 
}
    

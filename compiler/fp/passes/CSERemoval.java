/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.flowgraph.*;
import fp.util.BooleanEquation;
import fp.util.UseHash;
import fp.util.Bool;




public class CSERemoval extends Pass implements BlockPass {
  
  /*
    This is fairly limited CSE analysis -- it only looks for a pair of 
    binary operations that match.  It does not build anything more fancy and
    look for larger matches.

    For example:
    
    c = a + b;        -->   tmp = a + b;
    d = a + b;                c = tmp;
                              d = tmp;
    However,
    
    t1 = a + b;
    c = t1 + f;
    t2 = a + f;
    d = t2 + b;
    
    does not change at all.
			      
  */


  public CSERemoval(PassManager pm) {
    super(pm);
  }

  public String name() { return "CSERemoval"; }

  public boolean optimize(BlockNode node) {
    
    /* sorry, mate, but I needed to use this so made a separate class
     * to hold the code
     */

    CSERemovalCode theCSErminator = new CSERemovalCode();
    return theCSErminator.removeEmCSEs(node);
  }
  

}


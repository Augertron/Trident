/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Abs extends Factor implements VHDLout {

  public Abs(Primary left) {
    super(Factor.ABS, left);
  }

}

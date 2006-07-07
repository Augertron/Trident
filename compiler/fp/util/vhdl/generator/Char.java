/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class Char extends EnumerationLiteral {
  public final static Char ONE = new Char('1');
  public final static Char ZERO = new Char('0');

  public Char(char c) {
    super(c);
  }
}


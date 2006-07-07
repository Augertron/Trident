/*
 *
 @LICENSE@
 */


/*
 *  SExpr.java
 *
 *  Copyright 1997 Massachusetts Institute of Technology.
 *  All Rights Reserved.
 *
 *  Author: Ora Lassila
 *
 *  $Id$
 */

package fp.util.sexpr;

import java.io.PrintStream;

/**
 * Interface for all new s-expression subtypes.
 */
public interface SExpr {

  /**
   * Print a representation of the s-expression into the output stream.
   */
  public void printExpr(PrintStream out);

  public String toString();

}

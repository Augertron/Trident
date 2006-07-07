/*
 *
 @LICENSE@
 */


/*
 *  SimpleReadtable.java
 *
 *  Copyright 1997 Massachusetts Institute of Technology.
 *  All Rights Reserved.
 *
 *  Author: Ora Lassila
 *
 *  $Id$
 */

package fp.util.sexpr;

/**
 * Basic implementation of the Readtable interface, a dispatch table.
 */
public class SimpleReadtable implements Readtable {

  private SExprParser parsers[];

  /**
   * Initializes an empty dispatch table (no associations).
   */
  public SimpleReadtable() {
    parsers = new SExprParser[256];
  }

  /**
   * Copy constructor.
   */
  public SimpleReadtable(SimpleReadtable table) {
    parsers = new SExprParser[256];
    for (int i = 0; i < 256; i++)
      parsers[i] = table.parsers[i];
  }

  /*
    jt

    This interface seems strange.  I don't understand why you
    would want to split on chars.  Somethings like strings might
    be okay or the vectors might work, but in general you may want
    to parse whole delimited chunks -- atleast, I would think so.

  */


  public SExprParser getParser(char key) {
    return parsers[(int)key];
  }

  public SExprParser addParser(char key, SExprParser parser) {
    parsers[(int)key] = parser;
    return parser;
  }

}

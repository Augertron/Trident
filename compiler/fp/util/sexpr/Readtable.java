/*
 *
 @LICENSE@
 */


/*
 *  Readtable.java
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
 * An interface for read tables.
 */
public interface Readtable {

  /**
   * Find the parser associated with the <i>key</i> dispatch character.
   */
  public SExprParser getParser(char key);

  /**
   * Associate a parser with the <i>key</i> dispatch character.
   */
  public SExprParser addParser(char key, SExprParser parser);

}

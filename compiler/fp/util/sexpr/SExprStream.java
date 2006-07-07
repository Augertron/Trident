/*
 *
 @LICENSE@
 */


/*
 *  SExprStream.java
 *
 *  Copyright 1997 Massachusetts Institute of Technology.
 *  All Rights Reserved.
 *
 *  Author: Ora Lassila
 *
 *  $Id$
 */

package fp.util.sexpr;

import java.io.IOException;
import java.util.Map;

/**
 * An interface for a full s-expression parser.
 */
public interface SExprStream extends SExprParser {

  /**
   * Parse a single object from the stream.
   * @exception SExpParseException if the input stream cannot be parsed.
   * @exception IOException if the input stream cannot be opened or read.
   */
  public Object parse() throws SExprParserException, IOException;

  /**
   * Access the symbol table of the parser.
   */
  public Map getSymbols();

  /**
   * Assign the symbol table of the parser.
   */
  public Map setSymbols(Map symbols);

  /**
   * Access the dispatch table of the parser.
   */
  public Readtable getReadtable();

  /**
   * Assign the dispatch table of the parser.
   */
  public Readtable setReadtable(Readtable readtable);

  /**
   * Associate an input character with a "sub-parser."
   */
  public SExprParser addParser(char key, SExprParser parser);

  /**
   * Checks whether lists are to be parsed as Vectors or Cons cells.
   */
  public boolean getListsAsVectors();

  /**
   * Controls whether parsed lists are Vectors or Cons cells.
   */
  public boolean setListsAsVectors(boolean listsAsVectors);

  /**
   * Accesses an empty string buffer available temporary storage.
   */
  public StringBuffer getScratchBuffer();

  /**
   * Reads from the stream, skipping whitespace.
   */
  public char readSkipWhite() throws IOException;

  /**
   * Read a single character from the stream.
   * This method is here because there is no InputStream interface in the
   * java.io package (JavaSoft please take notice!).
   */
  public int read() throws IOException;

}

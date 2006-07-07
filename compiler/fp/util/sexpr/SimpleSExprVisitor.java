/*
 *
 @LICENSE@
 */


package fp.util.sexpr;

import java.util.*;

public class SimpleSExprVisitor extends SExprVisitor {

  public void forSymbol(Symbol that) {
    System.out.println("Have Symbol "+that);
  }
  
  public void forVector(Vector that) {
    System.out.println("Have Vector "+that);
  }

  public void forString(String that) {
    System.out.println("Have String "+that);
  }
  
  public void forNumber(Number that) {
    System.out.println("Have Number "+that);
  }

  public void forUnknown(Object that) {
    System.out.println("Have Unknown "+that);
  }
}

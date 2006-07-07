/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;


/**
 * Implemented by Objects in order to perfrom a multiway branch or
 * switch, on an <code>Operation</code>/ It corresponds to the Visitor
 * in the the Visitor pattern (see <i>Design Patterns</i>, 1995).
 *
 * @see Operation
 *
 * @author Justin L. Tripp
 */



public interface OperationSwitch {

  public void caseAdd();
  public void caseAnd();
  public void caseBuf();
  public void caseConcat();
  public void caseExpand();
  public void caseMult();
  public void caseMux();
  public void caseNeg();
  public void caseNot();
  public void caseOr();
  public void caseShl();
  public void caseShr();
  public void caseSub();
  public void caseUshr();
  public void caseXor();
  public void caseDiv();
  public void caseSqrt();
  public void caseSlice();

  public void defaultCase(Operation op);

}




  

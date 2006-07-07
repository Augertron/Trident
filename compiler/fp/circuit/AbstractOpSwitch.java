/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

/**
 * Basic implementation of <code>OperationSwitch</code>
 *
 * @author Nathan Kitchen
 * @author Justin Tripp
 */
public abstract class AbstractOpSwitch implements OperationSwitch {
  /**
   * Holds the result of computation
   */
  private Object _result;
  
  /**
   * Sole constructor. (For invocation by subclass constructors, typically
   * implicit.) 
   */
  protected AbstractOpSwitch() {
  }

  public void caseAdd() { defaultCase(Operation.ADD); }
  public void caseAnd() { defaultCase(Operation.AND); }
  public void caseBuf() { defaultCase(Operation.BUF); }
  //public void caseDiv() { defaultCase(Operation.DIV); }
  public void caseMult() { defaultCase(Operation.MULT); }
  public void caseOr() { defaultCase(Operation.OR); }
  public void caseShl() { defaultCase(Operation.SHL); }
  public void caseShr() { defaultCase(Operation.SHR); }
  public void caseSub() { defaultCase(Operation.SUB); }
  public void caseUshr() { defaultCase(Operation.USHR); }
  public void caseXor() { defaultCase(Operation.XOR); }
  public void caseNeg() { defaultCase(Operation.NEG); }
  public void caseMux() { defaultCase(Operation.MUX); }

  public void caseExpand() { defaultCase(Operation.EXPAND); }
  public void caseConcat() { defaultCase(Operation.CONCAT); }
  public void caseSlice() { defaultCase(Operation.SLICE); }

  public void defaultCase(Operation op) { }

  /**
   * Returns the last object passed to <code>setResult</code>
   */
  public final Object getResult() {
    return _result;
  }

  /**
   * Stores <code>result</code> to be returned by the next call to
   * <code>getResult</code>.  By using these two methods together, the switch
   * can be used to compute a different result for each kind of operation.
   */
  public final void setResult(Object result) {
    _result = result;
  }
}

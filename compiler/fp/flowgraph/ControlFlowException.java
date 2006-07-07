/*
 *
 @LICENSE@
 */


package fp.flowgraph;

/**
 * Exception used by control flow classes in this package
 * 
 * @author Nathan Kitchen
 */
public class ControlFlowException extends RuntimeException {
  public ControlFlowException() {
    super();
  }

  public ControlFlowException(String message) {
    super(message);
  }
}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

/**
 * Exception used by data flow classes in this package
 * 
 * @author Nathan Kitchen
 */
public class DataFlowException extends RuntimeException {
  public DataFlowException() {
    super();
  }

  public DataFlowException(String message) {
    super(message);
  }
}

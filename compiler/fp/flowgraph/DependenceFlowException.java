/*
 *
 @LICENSE@
 */


package fp.flowgraph;

/**
* Exception used by data flow classes in this package
* 
* @author Nathan Kitchen
* copied and modified by Kris Peterson
*/
public class DependenceFlowException extends RuntimeException {
  public DependenceFlowException() {
    super();
  }
  
  public DependenceFlowException(String message) {
    super(message);
  }
}

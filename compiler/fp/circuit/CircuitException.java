/*
 *
 @LICENSE@
 */


package fp.circuit;

public class CircuitException extends RuntimeException {
  public CircuitException() {
    super();
  }

  public CircuitException(String message) {
    super("\n" + message);
  }
}

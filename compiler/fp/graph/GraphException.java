/*
 *
 @LICENSE@
 */


package fp.graph;

/**
 * Runtime exceptions for this graph package
 *
 * @author Nathan Kitchen
 */
public class GraphException extends RuntimeException {
  public GraphException() {
    this(null);
  }

  public GraphException(String message) {
    super(message);
  }
}

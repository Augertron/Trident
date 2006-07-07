/*
 *
 @LICENSE@
 */


package fp.synthesis;

public class SynthesisException extends RuntimeException {
  public SynthesisException() {
    super();
  }

  public SynthesisException(String message) {
    super("\n" + message);
  }
}

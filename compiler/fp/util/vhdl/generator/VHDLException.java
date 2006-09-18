/*
 *
 @LICENSE@
 */


package fp.util.vhdl.generator;

public class VHDLException extends RuntimeException {
  public VHDLException() {
    super();
  }

  public VHDLException(String message) {
    super("\n" + message);
  }
}

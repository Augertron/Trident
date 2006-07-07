/*
 *
 @LICENSE@
 */


package fp.hardware;

/**
* Exception used by schedulers
* 
* @author Nathan Kitchen
* copied and modified by Kris Peterson
*/
public class HardwareException extends RuntimeException {
  public HardwareException() {
    super();
  }
  
  public HardwareException(String message) {
    super("\n" + message);
  }
}

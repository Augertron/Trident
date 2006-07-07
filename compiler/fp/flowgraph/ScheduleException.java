/*
 *
 @LICENSE@
 */


package fp.flowgraph;

/**
* Exception used by schedulers
* 
* @author Nathan Kitchen
* copied and modified by Kris Peterson
*/
public class ScheduleException extends RuntimeException {
  public ScheduleException() {
    super();
  }
  
  public ScheduleException(String message) {
    super("\n" + message);
  }
}

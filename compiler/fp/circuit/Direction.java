/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

public interface Direction {
  public static final int IN    = 1;
  public static final int OUT   = 2;
  public static final int INOUT = 3;

  public static final int _TOP = 1;
  public static final int _CELL = 2;
  public static final int _MODULE_GROUP = 3;
  public static final int _DATA_PATH = 4;

  public static final int _TOP_INPUT = 1;
  public static final int _LOCAL_INPUT = 2;
  
}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public class IllegalOperand extends RuntimeException
{
  public IllegalOperand() {}

  public IllegalOperand(String s)
  {
    super(s);
  }
}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public class IllegalOpcode extends RuntimeException
{
  public IllegalOpcode() {}

  public IllegalOpcode(String s)
  {
    super(s);
  }
}

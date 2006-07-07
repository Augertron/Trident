/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

public interface VHDLout {
  public static final String TAB = "   ";

  public StringBuffer toVHDL(StringBuffer s, String pre);
}  

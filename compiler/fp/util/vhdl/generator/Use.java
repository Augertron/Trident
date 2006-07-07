/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

public class Use extends Line implements VHDLout {
  
  public Use(String c) {
    super(c);
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("use ").append(getText()).append(";\n");;
    return s;
  }

}

/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

class Comment extends Line implements VHDLout {
  
  public Comment(String c) {
    super(c);
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append("-- ").append(getText()).append("\n");
    return s;
  }

}

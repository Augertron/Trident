/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

public class Line implements VHDLout {
  private String _text;
  
  public Line(String c) {
    _text = c;
  }

  void setText(String c) { _text = c; }
  String getText() { return _text; }


 public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre).append(_text);
    s.append("\n");
    return s;
  }

  public String toString() {
    // add the comment string
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf, "").toString();
  }
  
}

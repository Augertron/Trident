/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

abstract class VHDLBase implements VHDLout {

  protected String _name;
  protected LinkedList _header;
  protected LinkedList _footer;

  VHDLBase(String name) {
    _name = name;
    _header = new LinkedList();
    _footer = new LinkedList();
  }

  VHDLBase() {
    this(null);
  }

  void setName(String n) { _name = n; }
  public String getName() { return _name; }

  LinkedList getHeader() { return _header; }
  void addHeader(VHDLout v) { _header.add(v); }

  public void addHeaderComment(String c) {
    addHeader(new Comment(c));
  }

  LinkedList getFooter() { return _footer; }
  void addFooter(VHDLout v) { _footer.add(v); }

  public void addFooterComment(String c) {
    addFooter(new Comment(c));
  }


  protected void appendHeader(StringBuffer sbuf, String pre) {
    for(ListIterator iter=_header.listIterator();iter.hasNext();) {
      ((VHDLout)iter.next()).toVHDL(sbuf, pre);
    }
    sbuf.append("\n");
  } 

  protected void appendFooter(StringBuffer sbuf, String pre) {
    sbuf.append("\n");
    for(ListIterator iter=_footer.listIterator();iter.hasNext();) {
      ((VHDLout)iter.next()).toVHDL(sbuf, pre);
    }
  }

  abstract protected void appendBody(StringBuffer sbuf, String tabs);

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    appendHeader(s, pre);
    appendBody(s, pre);
    appendFooter(s, pre);
    return s;
  }


  public String toString() {
    StringBuffer sbuf = new StringBuffer();

    return toVHDL(sbuf,"").toString();
  }


  public static void main(String args[]) {

  }
}




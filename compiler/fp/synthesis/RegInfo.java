/*
 *
 @LICENSE@
 */


package fp.synthesis;

import java.util.*;

public class RegInfo {
  
  private String _name;
  private int    _width;
  private int    _depth;
  private long   _addr;
  private long   _addr_simple;
  private long   _index;

  public static final String START = "(";
  public static final String END = ")";
  public static final String LINE = "\n";
  public static final String TAB = "\t";

  RegInfo(String name, int width, int depth) {
    _name = name; 
    _width = width;
    _depth = depth;
    _addr = -1;
    _index = -1;
    _addr_simple = -1;

  }

  RegInfo(String name, int width) {
    this(name, width, 1);
  }

  public String getName() {  return _name; }
  public void setName(String name) { _name = name; }

  public int getWidth() { return _width; }
  public void setWidth(int width) { _width = width; }

  public int getDepth() { return _depth; }
  public void setDepth(int depth) { _depth = depth; }
  
  //public int getAddressOffset() { return _address_offset; }
  //public void setAddressOffset(int addr) { _address_offset = addr; }
  
  public long getAddr() {  return _addr; }
  public String getAddrHexStr() {  return "0x"+Long.toHexString(_addr); }
  public void setAddr(long addr) {  _addr = addr; }

  public void setAddrSimple(long addr) { _addr_simple = addr; }
  public long getAddrSimple() { return _addr_simple; }

  public long getIndex() { return _index; }
  public void setIndex(long l) { _index = l; }
  
  public String toString() {
    return "[ "+_name+", "+_index+", "+_width+" ]";
  }
  
  public String toText(String prefix) {
    StringBuffer sbuf = new StringBuffer();
    sbuf.append(prefix);
    sbuf.append(START);
    sbuf.append("register");
    sbuf.append(" ").append(_name).append(LINE);

    addTextInt(sbuf, prefix, "width", _width);
    addTextInt(sbuf, prefix, "depth", _depth);
    addTextStr(sbuf, prefix, "address", getAddrHexStr());
    addTextLong(sbuf, prefix, "index", _index);

    sbuf.append(prefix).append(END);
    sbuf.append(LINE);

    return sbuf.toString();
  }

  void addTextLong(StringBuffer sbuf, String prefix, String name, 
		     long value) {
    sbuf.append(prefix).append(TAB);
    sbuf.append(START).append(name);
    sbuf.append(" ").append(value).append(END);
    sbuf.append(LINE);
  }

  void addTextInt(StringBuffer sbuf, String prefix, String name, 
		     int value) {
    sbuf.append(prefix).append(TAB);
    sbuf.append(START).append(name);
    sbuf.append(" ").append(value).append(END);
    sbuf.append(LINE);
  }

  void addTextStr(StringBuffer sbuf, String prefix, String name, 
		     String value) {
    sbuf.append(prefix).append(TAB);
    sbuf.append(START).append(name);
    sbuf.append(" ").append(value).append(END);
    sbuf.append(LINE);
  }
      
  public static void sort(List a) {
    class RegInfoCompare implements Comparator {
      public int compare(Object o1, Object o2) {
	if((o1 instanceof RegInfo) &&
	   (o2 instanceof RegInfo)) 
	  return (((RegInfo) o1).getName().compareToIgnoreCase(((RegInfo)o2).getName()));
	else
	  throw new ClassCastException("Not a RegInfo!");
      }
    }
    Collections.sort(a, new RegInfoCompare());
  }

}

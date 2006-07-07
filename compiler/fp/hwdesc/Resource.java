/*
 *
 @LICENSE@
 */


package fp.hwdesc;

public class Resource extends Base {
  public int dimension1 = 0;
  public int dimension2 = 0;
  public int address = 0;

  // resource types

  public static final int NONE = 0;
  public static final int BLOCKRAM_TYPE = 1;
  public static final int MULTIPLIER_TYPE = 2;
  public static final int PROCESSOR_TYPE = 3;
  public static final int DSPBLOCK_TYPE = 4;
  public static final int LUTRAM_TYPE = 5;
  public static final int SRL16_TYPE = 6;
  public static final int REGISTER_TYPE = 7;

  public Resource() {
    typeName = new String[] {
      "none",
      "blockram",
      "multiplier",
      "processor",
      "dspblock",
      "lutram",
      "srl16",
      "register",
    };
  }
 

  public String toString() {
    StringBuffer sbuf = new StringBuffer("(resource ");
    sbuf.append(type).append("\n");
    sbuf.append("(count ").append(count).append(" )\n");
    sbuf.append("(name \"").append(name).append("\" )\n");

    sbuf.append("(address 0x").append(Integer.toHexString(address)).append(" )\n");
    sbuf.append("(size ").append(size).append(" )\n");
    sbuf.append("(width ").append(width).append(" )\n");
    sbuf.append("(depth ").append(depth).append(" )\n");
    sbuf.append("# dimensions is not really implemented\n");
    sbuf.append("(dimensions ").append(dimension1).append(" )\n");

    sbuf.append(")\n");
    
    return sbuf.toString();
  }
    
 
}

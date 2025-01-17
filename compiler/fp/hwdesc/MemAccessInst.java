/*
 *
 @LICENSE@
 */


package fp.hwdesc;

public class MemAccessInst extends Base {
  public int latency = 0;
  public float area = 0;
  public int sliceCnt = 0;
  
  // instruction types
  public static final int ALOAD = 0;
  public static final int ASTORE = 1;


  public MemAccessInst() { 
    typeName = new String[] {
      "aload",
      "astore"
    };
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer("(memAccessInst ");
    sbuf.append(type).append("\n");
    
    sbuf.append("(name ").append(name).append(" )\n");
    sbuf.append("(latency ").append(latency).append(" )\n");
    sbuf.append("(area ").append(area).append(" )\n");
    sbuf.append("(sliceCnt ").append(sliceCnt).append(" )\n");

    sbuf.append(")\n");
    
    return sbuf.toString();
  }
    
}

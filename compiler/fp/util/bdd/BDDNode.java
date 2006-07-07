/*
 *
 @LICENSE@ 
 */ 

package fp.util.bdd;

public class BDDNode {

  public static final BDDNode ZERO = new BDDNode();
  public static final BDDNode ONE = new BDDNode();

  public final static int MAXREF = 0x3FF;

  static final int NO_LEVEL = -1;

  BDDNode low, high;
  BDDNode next;
  BDDNode hash;
  
  int level;
  int refcount;

  boolean marked;
  int index;
  String label;
  
  
  public BDDNode(BDDNode l, BDDNode h, int lev, int i) {
    index = i;
    low = l;
    high = h;
    level = lev;
    hash = null;
  }

  

  public BDDNode(int i) {
    this(null, null, NO_LEVEL, i);
  }

  public BDDNode() {
    this(null, null, NO_LEVEL, NO_LEVEL);
  }

  public BDDNode getLow() { return low; }
  void setLow(BDDNode l) { low = l; }

  public BDDNode getHigh() { return high; }
  void setHigh(BDDNode h) { high = h; }

  public int getLevel() { return level; }
  void setLevel(int l) { level = l; }

  BDDNode getNext() { return next; }
  void setNext(BDDNode n) { next = n; }

  int getRefCount() { return refcount; }
  void setRefCount(int i) { refcount = i; }
  void setMaxRefCount() { refcount = MAXREF; }

  public BDDNode incRefCount() { 
    if (refcount < MAXREF) refcount++; 
    return this;
  }

  public BDDNode decRefCount() { 
    if (refcount < MAXREF) refcount--; 
    return this;
  }

  public int getRoot() { return index; }
  void setRoot(int i) { index = i; }

  BDDNode getHash() { return hash; }
  void setHash(BDDNode h) { hash = h; }

  public boolean isZero() { return this == ZERO; }
  public boolean isOne() { return this == ONE; }
  public boolean isConst() { return isZero() || isOne(); }

  void mark() {
    if (isConst()) return;
    marked = true;
  }

  void unmark() {
    if (isConst()) return;
    marked = false;
  }

  void setMark() {
    if (isConst()) return;
    if (marked || low == null) return;
    marked = true;
    
    low.setMark();
    high.setMark();
  }
  
  void clearMark() {
    if (isConst()) return;
    
    if (!marked || low == null) return;
    marked = false; 
    
    low.clearMark();
    high.clearMark();
  }

  boolean getMark() { return marked; }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();

    sbuf.append("Root: "+index);
    sbuf.append(" Level: "+level);
    if (index > 1) {
      sbuf.append(" l: "+(low == null ? "null" : Integer.toString(low.getRoot())));
      sbuf.append(" h: "+(high == null ? "null" : Integer.toString(high.getRoot())));
      sbuf.append(" ref: "+getRefCount());
    }
    sbuf.append(" hash: "+(hash == null ? "null" : Integer.toString(getHash().getRoot())));
    sbuf.append(" next: "+(next == null ? "null" : Integer.toString(getNext().getRoot())));
    
    return sbuf.toString();
  }
		  
      
    

  public String toDot(BDDNode level2var[] ) {
    if (isConst() || getMark())
      return "";
    StringBuffer sbuf = new StringBuffer();
    sbuf.append(getRoot()+" ");
    sbuf.append("[label=\"");
    sbuf.append(getRoot()+" "+level2var[getLevel()].getRoot()+"/"+getLevel()+"\"];\n");
    if (getLow() == null) 
      System.err.println(getRoot()+": Low has null -- not good ");
    else 
      sbuf.append(getRoot()+" -> "+getLow().getRoot());
    sbuf.append(" [style=dotted];\n");
    sbuf.append(getRoot()+" -> "+getHigh().getRoot());
    sbuf.append(" [style=filled];\n");
     
    mark();
    if (getLow() != null) 
      sbuf.append(getLow().toDot(level2var));
    sbuf.append(getHigh().toDot(level2var));
    
    return sbuf.toString();
  }
    

}

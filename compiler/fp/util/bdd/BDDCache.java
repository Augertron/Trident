/*
 *
 @LICENSE@ 
 */ 

package fp.util.bdd;

public class BDDCache {

  int tablesize;
  BDDCacheData[] table;

  BDDCache(int size) {
    int n;

    table = new BDDCacheData[size];
    
    for(n=0; n < size; n++) {
      table[n] = new BDDCacheData();
      table[n].a = null;
    }
    tablesize = size;
  }

  void reset() {
    for(int n=(tablesize-1); n>=0; n--) table[n].a = null;
  }
  
  
  BDDCacheData lookup(int hash) {
    return table[hash % tablesize];
  }
  

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    for(int n = 0; n < tablesize - 1; n++) {
      sbuf.append("[").append(n).append("]").append(table[n]).append("\n");
    }

    return sbuf.toString();
  }
  
}







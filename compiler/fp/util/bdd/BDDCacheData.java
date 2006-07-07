/*
 *
 @LICENSE@ 
 */ 

package fp.util.bdd;

public class BDDCacheData {

  double dres;
  Object a, b, c, res;

  String toApplyCache() {
    StringBuffer sbuf = new StringBuffer();
    
    if (a != null) 
      sbuf.append("a: root ").append(((BDDNode)a).getRoot());
    else 
      sbuf.append(" a: null");
    if (b != null) 
      sbuf.append(" b: root ").append(((BDDNode)b).getRoot());
    else
      sbuf.append(" b: null");
    if (c != null) 
      sbuf.append(" c: ").append(BDD.op_name[((Integer)c).intValue()]);
    else
      sbuf.append(" c: null");
    
    return sbuf.toString();
  }

}

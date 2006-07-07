/*
 *
 @LICENSE@ 
 */ 

package fp.util;

import java.util.LinkedList;
import java.util.ListIterator;


public class TwoLevelBooleanEquation extends LinkedList {
  


  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    
    LinkedList[] lists = (LinkedList[])this.toArray( new LinkedList[this.size()] );
    
    for( int i = 0; i < lists.length; i++ ) {
      BooleanOp[] terms = (BooleanOp[])lists[i].toArray( new BooleanOp[lists[i].size()] );
      if( terms.length == 0 ) {
	sbuf.append("empty ");
      } else {
	for( int j = 0; j < terms.length; j++ ) {
	  sbuf.append(terms[j].toString());
	  sbuf.append(" ");
	}
      }
      if( i != lists.length-1 ) {
	sbuf.append( "OR " );
        }
    }
    return sbuf.toString();
  }


}

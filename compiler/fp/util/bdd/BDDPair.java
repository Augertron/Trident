/*
 *
 @LICENSE@ 
 */ 

package fp.util.bdd;

class BDDPair {
  BDDNode result[]; // ?
  
  int last;
  int id;
  BDDPair next;

  void setLast(int l) { last = l; }
  int getLast() { return last; }

  void setId(int i) { id = i; }
  int getId() { return id; }
  
  void setNext(BDDPair b) { next = b; }
  BDDPair getNext() { return next; }

  void setResults(BDDNode b[]) { result = b; }
  BDDNode[] getResults() { return result; }

}

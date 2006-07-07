/*
 *
 @LICENSE@ 
 */ 

package fp.util.bdd;

class BDDPairs {

  int pairs_id;
  BDDPair pairs;
  BDD bdd;

  BDDPairs(BDD my_bdd) {
    pairs_id = 0;
    pairs = null;
    bdd = my_bdd;
  }

  int updatePairsId() {
    pairs_id++;
    
    if (pairs_id == (Integer.MAX_VALUE >> 2)) {
      BDDPair p;
      pairs_id = 0;
      for (p=pairs ; p!=null; p=p.getNext()) 
	p.setId(pairs_id++);
      // bdd_operator_reset !!
      bdd.bdd_operator_reset();

    }
    return pairs_id;
  }

  void registerPair(BDDPair p) {
    p.setNext(pairs);
    pairs = p;
  }

  void resize(int varnum) {
    BDDPair p;
    
    for(p=pairs; p!=null; p=p.getNext()) {
      p.setResults(new BDDNode[varnum]);
    }
  }

}

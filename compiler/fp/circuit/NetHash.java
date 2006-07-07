/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

import java.util.HashMap;
import java.util.Iterator;

public class NetHash {
  
  private HashMap _hash;
  private Circuit _parent;
  
  NetHash(Circuit p) {
    _hash = new HashMap();
    _parent = p;
  }
  
  void addSink( String net, PortTag sink ) {
    if( _hash.containsKey( net ) ) {
      ((Net)_hash.get( net )).addSink( sink );
    } else {
      Net n = _parent.newNet(_parent,net).addSink(sink);
      _hash.put( net, n);
    }
  }
  
  void addSource( String net, PortTag source ) {
    if( _hash.containsKey( net ) ) {
      ((Net)_hash.get( net )).addSource( source );
    }  else {
      Net n = _parent.newNet(_parent,net).addSource(source);
      _hash.put( net, n);
    }
  }

  /*
  void setNetPullup(String net) {
    if (_hash.containsKey(net)) {
      ((Net)_hash.get(net)).setPullup(true);
    } else {
      System.out.println("Net " + net + " not yet created. Cannot create pulldown");
    }
  }

  void setNetPulldown(String net) {
    if (_hash.containsKey(net)) {
      ((Net)_hash.get(net)).setPulldown(true);
    } else {
      System.out.println("Net " + net + " not yet created. Cannot create pulldown.");
    }
  }
  */  

  public Net getNet( String name ) {
    return (Net)_hash.get( name );
  }
  
  void checkNets() {
    for(Iterator iter = _hash.keySet().iterator(); iter.hasNext(); ) {
      String name = (String)iter.next();
      Net n = (Net)_hash.get( name );
      System.out.println( "name: " + name );
    }
  }
}

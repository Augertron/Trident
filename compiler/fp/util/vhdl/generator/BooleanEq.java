/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

class BooleanEq implements VHDLout {
  
  // this only applies to std_ulogic and std_ulogic_vector
  // from the std_logic_1164 library.

  public static final String AND = "and";
  public static final String OR = "or";
  public static final String NOT = "not";
  public static final String XOR = "xor"; //?
  public static final String EQ = "=";

  
  LinkedList _bools;

  BooleanEq(Bus name) {
    _bools = new LinkedList();
    if (name != null) {
      _bools.add(new Bool(name));
    }

  }



  class Bool {
    Bus _s;
    String _op;
    boolean _not;
    
    Bool(Bus s, String op, boolean ed) {
      _s = s;
      _op = op;
      _not = ed;
    }

    Bool(Bus s) {
      this(s, null, false);
    }

    public String toString() {
      StringBuffer sbuf = new StringBuffer();
      if (_op != null) {
	sbuf.append(_op).append(" ");
      }
      if (_not) {
	sbuf.append(NOT).append(" ");
      }
      sbuf.append(_s.toString());
      return sbuf.toString();
    }

  }


  void eq(Bus name) {
    // should check size = 1
    _bools.add(new Bool(name, EQ, false));
  }

  void and(Bus name) {
    _bools.add(new Bool(name, AND, false));
  }

  void and_not(Bus name) {
    _bools.add(new Bool(name, AND, true));
  }
   
  void or(Bus name) {
    _bools.add(new Bool(name, OR, false));
  }
 
  void or_not(Bus name) {
    _bools.add(new Bool(name, OR, true));
  }

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    for(ListIterator iter = _bools.listIterator(); iter.hasNext(); ) {
      s.append(iter.next().toString());
    }
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }
}

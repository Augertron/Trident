/*
 *
 @LICENSE@
*/



package fp.util.vhdl.generator;

import java.util.*;

public class Aggregate extends LinkedList implements VHDLout, Primary {

  
  public static final Aggregate OTHERS_IS_ZERO = 
    new Aggregate(ElementAssociation.OTHERS_IS_ZERO);
  public static final Aggregate OTHERS_IS_ONE = 
    new Aggregate(ElementAssociation.OTHERS_IS_ONE);


  public Aggregate() {
    super();
  }

  public Aggregate(Object obj) {
    this();
    add(obj);
  }


  // could have a special add that rejects other object types ...
  

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    int count = 0;
    s.append("(");
    for(ListIterator list_iter = listIterator(); list_iter.hasNext(); ) {
      VHDLout out = (VHDLout)list_iter.next();
      if (list_iter.hasNext()) s.append(", ");
      out.toVHDL(s,"");
    }
    s.append(")");

    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }
  

}  

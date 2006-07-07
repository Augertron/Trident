/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;

public class PointerType extends Type {

  private Type _type;

  public PointerType(Type type) {
    super(getHash(type), 32, FIRSTCLASS | POINTER);
    _type = type;
  }

  public Type getType() { return _type; }

  public static String getHash(Type type) { 
    return "pointer_"+"_"+type.getHash();
  }

  // needs fancy code for toString()

}
    

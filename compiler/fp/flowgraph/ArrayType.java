/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;

public class ArrayType extends Type {

  private Type _type;
  private int _len;

  public ArrayType(Type type, int len) {
    super(getHash(type), type.getWidth(), ARRAY);
    _type = type;
    _len = len;
  }

  public Type getType() { return _type; }
  public int getLen() { return _len; }

  public int getWidth() { 
    return (_len * _type.getWidth()); 
  }

  public static String getHash(Type type) { 
    return "array_"+type.getHash() ;
  }

  // needs fancy code for toString()

}
    

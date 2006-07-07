/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

public abstract class Port extends Node {

  private int _width;
  private int _direction;

  public Port(Circuit parent, String name, int width, int direction) {
    super(parent, name);
    
    _width = width;
    _direction = direction;
    
  }

  public int getWidth() { return _width; }
  void setWidth(int i) { _width = i; }
  
  public int getDirection() { return _direction; }
  void setDirection(int i) { _direction = i; }

  // need toDot()


}

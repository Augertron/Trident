/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

public abstract class Memory extends Node {

  private int _width;
  private int _address_width;
  private int[] _values;


  public Memory(Circuit parent, String name, int width, int address_width, 
		int[] values) {
    super(parent, name);
    _width = width;
    _address_width = address_width;
    _values = values;
  }

  public Memory(Circuit parent, int width, int address_width, int[] values) {
    this(parent, "memory", width, address_width, values);
  }


}

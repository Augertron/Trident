/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

public class Bus extends Type {
  
  public Bus(String name, int start, int stop) {
    super(name, start, stop);
  }

  public Bus(String name) {
    super(name, 0, 0);
  }

}


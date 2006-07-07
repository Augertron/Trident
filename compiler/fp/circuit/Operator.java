/*
 *
 @LICENSE@ 
 */ 
package fp.circuit;

public abstract class Operator extends Node {

  // inports
  // outports

  private Operation _type;

  public Operator(Circuit parent, String name, Operation type) {
    super(parent, name);
    _type = type;
  }

  
  public Operation getOperation() { return _type; }



}

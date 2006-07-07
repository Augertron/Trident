/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class BlockOperand extends Operand {
  
  // there should be a way to get the next assignment if
  // you don't already have one.
  public BlockOperand(String name, int assign) {
    _name = name;
    _assignment = assign;
  }

  public Operand copy() { 
    return new BlockOperand(_name, _assignment);
  }

  public Operand getNext() {
    return Operand.nextBlock(_name);
  }

  public boolean isIntegerOperand() {
    return true;
  }

  public boolean isFloatingPointOperand() {
    return true;
  }

}

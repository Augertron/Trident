/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public final class MemoryOperand extends Operand {
  
  int size;

  MemoryOperand(String name, int size) {
    _name = name;
    this.size = size;
    _assignment = NOT_ASSIGNED;
  }

  public Operand copy() { 
    return new MemoryOperand(_name, size);
  }

  public Operand getNext() {
    return Operand.nextMemory(_name);
  }
}

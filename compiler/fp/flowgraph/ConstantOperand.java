/*
 *
 @LICENSE@
 */


package fp.flowgraph;

public abstract class ConstantOperand extends Operand {
  
  ConstantOperand(String name) {
    _name = name;
    _assignment = NOT_ASSIGNED;
  }
  
}

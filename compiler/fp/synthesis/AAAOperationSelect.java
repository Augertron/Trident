/*
 *
 @LICENSE@
 */


package fp.synthesis;

import fp.*;
import fp.circuit.Operation;
import fp.flowgraph.Instruction;
import fp.flowgraph.Operators;
import fp.flowgraph.IllegalOpcode;

public class AAAOperationSelect extends OperationSelect implements Operators {
  
  public Operation operationSelect(Instruction instruction) throws IllegalOpcode {
    String op_name = instruction.operator.toString();
    char op_code = instruction.operator.opcode;
    Operation operation = null;

    // Need to assign specific op_codes to circuit operations
    switch (op_code) {
    case AAA_IADD_opcode:
      operation = Operation.ADD;
      break;
    case AAA_ISUB_opcode:
      operation = Operation.SUB;
      break;
    case AAA_IMUL_opcode:
      operation = Operation.MULT;
      break;
    case AAA_IINV_opcode:
      operation = Operation.NEG;
      break;
    case AAA_AND_opcode:
      operation = Operation.AND;
      break;
    case AAA_OR_opcode:
      operation = Operation.OR;
      break;
    case AAA_XOR_opcode:
      operation = Operation.XOR;
      break;
    case AAA_NOT_opcode:
      operation = Operation.NOT;
      break;
    case AAA_SHL_opcode:
      operation = Operation.SHL;
      break;
    case AAA_SHR_opcode:
      operation = Operation.SHR;
      break;
    case AAA_LOAD_opcode:
      System.err.println("No Load allowed here.");
      operation = null;
      break;
    case AAA_STORE_opcode:
      System.err.println("No Store allowed here.");
      operation = null;
      break;
    case AAA_SELECT_opcode:
      operation = Operation.MUX;
      break;
    default:
      operation = Operation.get(op_name);
      // check for failure here.  Eventually make Operation.get throw an exception?
      if (operation == null) {
	throw new IllegalOpcode("There is no circuit operation for opcode " + op_name);
      }
    }
    return operation;
  }

}


/*
 *
 @LICENSE@
 */


package fp.synthesis;

import fp.circuit.Operation;
import fp.flowgraph.Instruction;

public abstract class OperationSelect {
  
  abstract public Operation operationSelect(Instruction instruction);

}


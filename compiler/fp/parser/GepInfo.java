/*
 *
 @LICENSE@
 */


package fp.parser;
import fp.flowgraph.Operand;
import fp.flowgraph.Type;
import fp.util.UniqueName;
import java.util.*;

class GepInfo {
  public Type type;
  public LinkedList pairs;
  public Operand operand;
  public final static HashMap addrOperandHashMap = new HashMap();
  public final static UniqueName names = new UniqueName();
  
  public GepInfo(Type t, LinkedList p, Operand o) {
	type = t;
	pairs = p;
	operand = o;
  }

  public Type getType() {
    return type;
  }

  public LinkedList getPairs() {
    return pairs;
  }

  public Operand getOperand() {
    return operand;
  }
}


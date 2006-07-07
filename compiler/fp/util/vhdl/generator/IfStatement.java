/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class IfStatement extends Statement implements SequentialStatement, VHDLout {
  
  private LinkedHashMap _conditions;
  private SequentialStatementList _last_else; // ?
  
  public IfStatement(Identifier label, Expression condition, 
		     SequentialStatementList statements) {
    super("if", label);

    _conditions = new LinkedHashMap();
    _last_else = null;
    // this could be dangerous, what if you re-use the same condition
    // it will overwrite. 
    if (condition != null) 
      _conditions.put(condition, statements);
  }


  public IfStatement(Expression condition, SequentialStatementList statements) {
    this(null, condition, statements);
  }

  public IfStatement() {
    this(null, null, null);
  }
		      

  public SequentialStatementList addElseIf(Expression condition, 
					   SequentialStatementList statements) {
    _conditions.put(condition, statements);
    return statements;
  }

  public SequentialStatementList addElseIf(Expression condition) {
    return addElseIf(condition, new SequentialStatementList());
  }
    

  public SequentialStatementList addFinalElse(SequentialStatementList statements) {
    _last_else = statements;
    return _last_else;
  }

  public SequentialStatementList addFinalElse(SequentialStatement statement) {
    SequentialStatementList list = new SequentialStatementList();
    list.add(statement);
    return addFinalElse(list);
  }

  public SequentialStatementList addFinalElse() {
    return addFinalElse(new SequentialStatementList());
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    // label top.
    Identifier label = getLabel();
    if (label != null) {
      s.append(pre);
      pre += TAB;
      label.toVHDL(s,"");
      s.append(":\n");
    }

    int count = 0;
    for(Iterator iter = _conditions.entrySet().iterator(); iter.hasNext();) {
      Map.Entry entry = (Map.Entry)iter.next();
      Expression condition = (Expression)entry.getKey();
      SequentialStatementList statements = (SequentialStatementList)entry.getValue();
      
      s.append(pre);
      if (count == 0) {
	s.append("if ");
      } else {
	s.append("elsif ");
      }
      condition.toVHDL(s, "");
      s.append(" then\n");
      count++;
      
      statements.toVHDL(s,pre+TAB);
    }

    if (_last_else != null) {
      s.append(pre).append("else\n");
      _last_else.toVHDL(s,pre+TAB);
    }

    s.append(pre).append("end if");
    if (label != null) {
      label.toVHDL(s," ");
    }
    s.append(";\n"); //?

    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



}

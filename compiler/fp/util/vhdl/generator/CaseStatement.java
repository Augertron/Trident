/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;

public class CaseStatement extends Statement implements VHDLout, SequentialStatement {
  
  private Expression _index;
  private LinkedHashMap _alternatives;

  public CaseStatement(Identifier label, Expression index) {
    super("case",label);
    _index = index;
    _alternatives = new LinkedHashMap();
  }

  public CaseStatement(Expression index) {
    this(null, index);
  }
    
  public SequentialStatementList addAlternative(ChoiceList cl, 
						SequentialStatementList ssl) {
    _alternatives.put(cl, ssl);
    return ssl;
  }

  public SequentialStatementList addAlternative(ChoiceList cl) {
    return addAlternative(cl, new SequentialStatementList());
  }

  public SequentialStatementList addAlternative(Choice choice) {
    return addAlternative(new ChoiceList(choice), 
			  new SequentialStatementList());
  }

  public SequentialStatementList addDefault() {
    return addAlternative(new ChoiceList(Identifier.OTHERS),
			  new SequentialStatementList());
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

    s.append(pre).append("case ");
    _index.toVHDL(s,"");
    s.append(" is\n");

    int count = 0;
    for(Iterator iter = _alternatives.entrySet().iterator(); iter.hasNext();) {
      Map.Entry entry = (Map.Entry)iter.next();
      ChoiceList choices = (ChoiceList)entry.getKey();
      SequentialStatementList statements = (SequentialStatementList)entry.getValue();
      
      s.append(pre+TAB).append("when ");
      choices.toVHDL(s,"");
      s.append(" =>\n");
      
      statements.toVHDL(s, pre+TAB+TAB);
    }
    
    s.append(pre).append("end case ");
    if (label != null) {
      label.toVHDL(s,"");
    }
    s.append(";\n"); //?
    
    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }



}  

/*
 *
 @LICENSE@ 
 */ 
package fp.util.vhdl.generator;

import java.util.*;


public class ActualPart implements VHDLout {

  public static final SimpleName OPEN = new SimpleName("open");

  Expression _expression;
  Name _designator;
  Name _function_type;

  ActualPart(Expression expression, Name functor, Name design) {
    _expression = expression;
    _designator = design;
    _function_type = functor;
  }

  public ActualPart(Name designator) {
    this(null, null, designator);
  }

  public ActualPart(Expression e) {
    this(e, null, null);
  }

  public ActualPart(Name a, Name b) {
    this(null, a, b);
  }

  public ActualPart(Name a, Expression e) {
    this(e, a, null);
  }

 public StringBuffer toVHDL(StringBuffer s, String pre) {
    // these extra carriage returns may not bee the best idea...
    s.append(pre);
    if (_expression != null) {
      if (_designator != null && _function_type == null) {
	s.append(_designator).append("( ");
	_expression.toVHDL(s,"");
	s.append(" )");
      } else {
	_expression.toVHDL(s,"");
      }
      
    } else {
      if (_function_type != null) 
	s.append(_function_type).append("(");
      s.append(_designator);
      if (_function_type != null) s.append(")");
    }
    return s;
  }

  public String toString() {
    return toVHDL(new StringBuffer(),"").toString();
  }



}

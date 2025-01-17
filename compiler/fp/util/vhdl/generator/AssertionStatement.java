/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class AssertionStatement extends Statement 
  implements VHDLout, SequentialStatement {

  /*
 assertion ::=
    assert condition

          [ report expression ]
          [ severity expression ] 

 assertion_statement ::=
    [ label : ] assertion ;
  */

  // Static Expressions for Severity ...
  // note, warning, error, failure

  public static final Expression NOTE    = 
    new Expression(new SimpleName("note"));
  public static final Expression WARNING = 
    new Expression(new SimpleName("warning"));
  public static final Expression ERROR   = 
    new Expression(new SimpleName("error"));
  public static final Expression FAILURE = 
    new Expression(new SimpleName("failure"));



  private Expression _condition;
  private Expression _report;
  private Expression _severity;

  public AssertionStatement(Identifier label, Expression condition,
			    Expression report, Expression severity) {
    super("assertion_statement", label);
    _condition = condition;
    _report = report;
    _severity = severity;
  }

  public AssertionStatement(Expression condition, 
			    Expression report, Expression severity) {
    this(null, condition, report, severity);
  }

  public AssertionStatement(Expression condition, Expression report) {
    this(null, condition, report, null);

  }
  

  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    if (getLabel() != null) {
      getLabel().toVHDL(s,"");
      s.append(": ");
    }

    s.append("assert ");
    _condition.toVHDL(s,"");
    if (_report != null) {
      s.append("\n").append(pre).append(TAB).append("report ");
      _report.toVHDL(s,"");
    }
    if (_severity != null) {
      s.append("\n").append(pre).append(TAB).append("severity ");
      _severity.toVHDL(s,"");
    }
    s.append(";\n");

    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }

  
  public static void main(String args[]) {
    // mult div mod rem

    SimpleName N = new SimpleName("N");

    // this is not the way to do this in general, 
    // use fp.circuit.vhdl.VHDLConstant.genConstant() ...
    Literal value = new BitStringLiteral(BitStringLiteral.X, "0000_000A");
    Literal first = new StringLiteral("N wasn't set correctly to 10  (N=");
    FunctionCall to_string = new FunctionCall(new SimpleName("to_string"));
    to_string.add(N);
    Literal second = new StringLiteral(") tb_test_a.vhd");
    

    AssertionStatement sa = 
      new AssertionStatement(new Expression(new Eq(N,value)),
			     new Expression(new Concat(first,to_string,
						       second)),
			     AssertionStatement.ERROR);
    print(sa);


  }

  public static void print(Statement f) {
    System.out.println("AssertionStatement:\n"+f);
  }




}

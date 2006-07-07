/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;


public class ElementAssociation implements VHDLout {

  public static final ElementAssociation OTHERS_IS_ZERO = 
    new ElementAssociation(Identifier.OTHERS, Expression.ZERO);
  public static final ElementAssociation OTHERS_IS_ONE = 
    new ElementAssociation(Identifier.OTHERS, Expression.ONE);
  
  private ChoiceList _choices;
  private Expression _expression;

  public ElementAssociation(ChoiceList choices, Expression expression) {
    if (choices == null) 
      _choices = new ChoiceList();
    else 
      _choices = choices;
    _expression = expression;
  }

  public ElementAssociation(Object choice, Expression expression) {
    this(null, expression);
    _choices.add(choice);
  }


  public ElementAssociation(Expression expression) {
    this(null, expression);
  }


  public void addChoice(Object choice) {
    _choices.add(choice);
  }

  
  public StringBuffer toVHDL(StringBuffer s, String pre) {

    if (_choices.size() > 0) {
      _choices.toVHDL(s,"");
      s.append(" => ");
    }

    _expression.toVHDL(s, "");
    return s;
  }


  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }

}  



  

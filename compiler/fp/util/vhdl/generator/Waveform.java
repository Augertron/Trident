/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class Waveform implements VHDLout {

  // this should have several waveform_elemnts, but not yet.

  // these should be more than expressions:
  // expression [ after time_expression ]
  // null [after time_expression ]
  LinkedList expressions;

  public Waveform(Object exp1, Object exp2) {
    expressions = new LinkedList();
    if (exp1 != null) {
      if (exp2 != null) {
	addExpression(exp1, exp2);
      } else {
	addExpression(exp1);
      }
    }
  }

  public Waveform(Object exp) {
    this(exp,null);
  }

  void addExpression(Object exp) {
    addWaveformElement(new WaveformElement(new Expression(exp), null)); 
  }
  
  void addExpression(Object exp1, Object exp2) {
    addWaveformElement(new WaveformElement(new Expression(exp1), 
					   new Expression(exp2)));
  }

  void addWaveformElement(WaveformElement we) {
    expressions.add(we);
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
    s.append(pre);
    int count = 0;
    for(ListIterator list_iter = expressions.listIterator(); 
	list_iter.hasNext(); ) {
      if (count != 0) {
	s.append(", ");
      }
      ((VHDLout)list_iter.next()).toVHDL(s,"");
    }
    return s;
  }
  
  public String toString() {
    return toVHDL(new StringBuffer(), "").toString();
  }


  public static void main(String args[]) {
    Waveform wf = new Waveform(new SimpleName("Bob"));
    System.out.println("Waveform: "+wf);
    
  }

   
}

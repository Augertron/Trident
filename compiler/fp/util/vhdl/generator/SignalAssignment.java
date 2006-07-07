/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

public class SignalAssignment extends Statement implements VHDLout, SequentialStatement {
  
  private Name _target;
  // delay_mechanism -- ignored.
  private Waveform _wave;

  public SignalAssignment(Identifier label, Name target, Waveform wave) {
    super("signal_assign", label);
    _target = target;
    _wave = wave;
  }

  public SignalAssignment(Name target, Waveform wave) {
    this(null, target, wave);
  }

  // lazy expression ...
  public SignalAssignment(Name target, Name source) {
    this(null, target, new Waveform(source));
  }


  public StringBuffer toVHDL(StringBuffer s, String pre) {
     s.append(pre);
    if (getLabel() != null) {
      getLabel().toVHDL(s,"");
      s.append(": ");
    }

    ((VHDLout)_target).toVHDL(s,"");
    s.append(" <= ");
    // delay mechanism
    _wave.toVHDL(s,"");

    s.append(" ;\n");

    return s;
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer();
    return toVHDL(sbuf,"").toString();
  }

  
  public static void main(String args[]) {
    // mult div mod rem

    SimpleName a = new SimpleName("a");
    SimpleName b = new SimpleName("b");
    SimpleName c = new SimpleName("c");
    SimpleName d = new SimpleName("d");
    SimpleName clk = new SimpleName("clk");

    Identifier my_wait = new SimpleName("my_wait");

    Expression ex = new Expression(new Eq(clk, Char.ONE));
    Expression ex1 = new Expression(new Gt(new Add(a,c), b));
    SensitivityList sl = new SensitivityList();
    sl.add(clk);

    SignalAssignment sa = new SignalAssignment(a,new Waveform(b));
    print(sa);

    sa = new SignalAssignment(d,new Waveform(Char.ONE));
    print(sa);

    sa = new SignalAssignment(b,new Waveform(new Sub(c,d)));
    print(sa);


  }

  public static void print(SignalAssignment f) {
    System.out.println("SignalAssignment: "+f);
  }




}

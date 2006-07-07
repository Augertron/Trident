/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

class Statement {
  private String _state;
  private Identifier _label;

  Statement(String s, Identifier label) {
    _state = s;
    _label = label;
  }

  public Identifier getLabel() { return _label; }

  public String getString() { return _state; }
  public String toString() { return getString(); }


}

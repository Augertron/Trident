/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.*;

class Library {
  private HashSet _libraries;
  private HashSet _uses;

  Library(String lib) {
    _libraries = new HashSet();
    if (lib != null) {
      addLibrary(lib);
    }
    _uses = new HashSet();
  }

  public void addLibrary(String lib) {
    _libraries.add(lib);
  }

  public void addUse(String use) {
    _uses.add(use);
  }

  public String toString() {
    StringBuffer sbuf = new StringBuffer("library ");
    int lib_count = _libraries.size();
    for(Iterator iter = _libraries.iterator(); iter.hasNext(); ) {
      String lib = (String)iter.next();
      if (lib_count > 1) {
	sbuf.append(lib);
	sbuf.append(", ");
      } else {
	sbuf.append(lib);
	sbuf.append(";\n");
      }
      lib_count--;
    }
    
    for(Iterator iter = _uses.iterator(); iter.hasNext(); ) {
      String use = (String)iter.next();
      sbuf.append("use ");
      sbuf.append(use);
      sbuf.append(".all");
      sbuf.append(";\n");
    }
    
    return sbuf.toString();
  }

}


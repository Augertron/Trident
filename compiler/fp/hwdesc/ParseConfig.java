/*
 *
 @LICENSE@
 */

package fp.hwdesc;

import java.io.*;
import java.util.*;

import fp.util.sexpr.SExprStream;
import fp.util.sexpr.SExprParserException;
import fp.util.sexpr.SimpleSExprStream;

import fp.util.FileTree;

public class ParseConfig {
  private Config _config;

  public ParseConfig(String filename) {
    //System.out.println("Parsing file "+filename);

    SExprStream p = null;
   
    InputStream is = fp.GlobalOptions.ft.getStream(filename);
    p = new SimpleSExprStream(is);

    p.setListsAsVectors(true);
    Object o = null;
    try { 
      o = p.parse();
      ParseConfigVisitor phv = new ParseConfigVisitor();
      phv.visit(o);
      _config = phv.getConfig();
    } catch (IOException e) {
      System.err.println("ParseConfig: IOException: Could not parse "
			 +filename);
      System.exit(-1);
    } catch (SExprParserException e) {
      System.err.println("ParseConfig: ParserException: Could not parse "
			 +filename);
      System.exit(-1);
    }

    //System.out.println("ParseConfig::_compiler "+_compiler);

  }

  public Config getConfig() { return _config; }

  public static void main(String args[]) {
    if (args.length == 1) {
      ParseConfig ph = new ParseConfig(args[0]);
      Config c = ph.getConfig();
      System.out.println("Config "+c);
    }
  }

}

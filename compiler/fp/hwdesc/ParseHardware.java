/*
 *
 @LICENSE@
 */


package fp.hwdesc;

import java.io.*;
import java.util.*;

import fp.util.sexpr.ForVisitor;
import fp.util.sexpr.SExprStream;
import fp.util.sexpr.SExprParserException;
import fp.util.sexpr.SimpleSExprStream;

import fp.util.FileTree;

public class ParseHardware {
  private Hardware _hardware;

  public ParseHardware(String filename) {
    //System.out.println("Parsing file "+filename);

    SExprStream p = null;
   
    InputStream is = fp.GlobalOptions.ft.getStream(filename);
    p = new SimpleSExprStream(is);

    /*
    try {
      //InputStream is = new FileInputStream(filename);
      InputStream is = FileTree.getStream(filename);
      p = new SimpleSExprStream(is);

    } catch (FileNotFoundException e) {
      System.err.println("Could not open "+filename);
      System.exit(-1);
    }
    */
    p.setListsAsVectors(true);
    Object o = null;
    try { 
      o = p.parse();
      ForVisitor fv = new ForVisitor();
      fv.visit(o);
      ParseHardwareVisitor phv = new ParseHardwareVisitor();
      phv.visit(o);
      _hardware = phv.getHardware();
    } catch (IOException e) {
      System.err.println("ParseHardware: IOException: Could not parse "
			 +filename);
      System.exit(-1);
    } catch (SExprParserException e) {
      System.err.println("ParseHardware: ParserException: Could not parse "
			 +filename);
      System.exit(-1);
    }

    //System.out.println("ParseHardware::_hardware "+_hardware);

  }

  public Hardware getHardware() { return _hardware; }


  public static void main(String args[]) {
    if (args.length == 1) {
      ParseHardware ph = new ParseHardware(args[0]);
      Hardware h = ph.getHardware();
      System.out.println("Hardware "+h);
    }
  }

}

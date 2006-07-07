/*
 *
 @LICENSE@
 */


package fp.circuit.vhdl;

import java.io.*;
import java.util.*;

import fp.util.sexpr.ForVisitor;
import fp.util.sexpr.SExprStream;
import fp.util.sexpr.SExprParserException;
import fp.util.sexpr.SimpleSExprStream;

public class ParseModule {
  
 // actual variables
  ParseModuleVisitor _pmv;

  public ParseModule(String filename) {
    SExprStream p = null;

    InputStream is = fp.GlobalOptions.ft.getStream(filename);
    p = new SimpleSExprStream(is);

    /*
    try {
      InputStream is = new FileInputStream(filename);
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
      
      // resolve for statments...
      ForVisitor fv = new ForVisitor();
      fv.visit(o);
      // parse my stuff ...
      _pmv = new ParseModuleVisitor();
      _pmv.visit(o);

    } catch (IOException e) {
      System.err.println("Could not parse "+filename);
      System.exit(-1);
    } catch (SExprParserException e) {
      System.err.println("Could not parse "+filename);
      System.exit(-1);
    }
  }

  public String getLibrary() { return _pmv.getLibrary(); }
  public HashMap getModules() { return _pmv.getModules(); }
  public Library getLib() { return _pmv.getLib(); }

  public static void main(String args[]) {
    if (args.length == 1) {
      ParseModule pm = new ParseModule(args[0]);
      // could be fancier.
      HashMap modules = pm.getModules();
      System.out.println("Modules "+modules);
    }
  }



}

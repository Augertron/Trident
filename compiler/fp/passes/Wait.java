/*
 *
 @LICENSE@
 */


package fp.passes;

import java.io.*;

import fp.flowgraph.BlockGraph;

/**
  * The Wait pass is a debugging tool to wait until the user presses 
  * the return key before continuing execution.  This should not 
  * normally be committed as a required pass.
  * <p>
  * Unfortunately this doesn't work under ant test because System.in 
  * doesn't seem to be attached to the keyboard.
  * @author Neil Steiner
  */
public class Wait extends Pass implements GraphPass {

  /**
    * Constructor.
    * @param pm the PassManager object.
    */
  public Wait(PassManager pm) { super(pm); }

  /** Returns the name of this optimizer. */
  public String name() { return "Wait"; }

  public boolean optimize(BlockGraph graph) {

    // some of these objects may throw exceptions
  	try { new BufferedReader(new InputStreamReader(System.in)).readLine(); }

    // if that happens, we'll just ignore them
	catch(IOException ioe) {}

    // tell the PassManager that all is well
    return true;
  }

}



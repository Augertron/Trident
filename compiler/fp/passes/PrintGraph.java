/*
 *
 @LICENSE@
 */


package fp.passes;

import fp.flowgraph.BlockGraph;

public class PrintGraph extends Pass implements GraphPass {

  /*
    The purpose of this pass is to print a copy of the current graph.
    This might be used to see what the result of various passes are and
    for debugging.

    It could make a big dot file -- but not nearly as big as the final 
    circuit as a dot file. :)
  */
  

  private String _name = null;
  static private int count = 0;


  public PrintGraph(PassManager pm) {
    this(pm, null);
  }

  public PrintGraph(PassManager pm, String s) {
    super(pm);
    _name = s;
  }

  public boolean optimize(BlockGraph graph) {
    String debug = "";
    String count_string = "";
    
    if (_name != null) {
      debug = _name;
      count_string = "_"+(count++);
    }

    // this could include the whole path 
    String file_name = graph.getName();

    // we need to get just the file basename and path from it
    int index = file_name.lastIndexOf(System.getProperty("file.separator"));
    String basename = file_name.substring(index+1);
    String path = file_name.substring(0, index+1);

    // now create the changed file name
    file_name = path + debug + basename + count_string + ".dot";

    graph.writeDotFile(file_name);
    // should this be static ???
    // this should ask pm about a verbosity level ...
    if (pm.getVerbose() >= PassManager.VERBOSE_L2) 
      System.err.println("writing "+file_name);
    
    return true;
  }

  public String name() { 
    return "PrintGraph";
  }

  // ??
  public static int incrementCount() { return count++; }


}


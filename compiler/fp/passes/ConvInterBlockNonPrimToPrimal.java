/*
 *
 @LICENSE@
 */


package fp.passes;

import java.util.*;

import fp.*;
import fp.flowgraph.*;
import fp.util.*;

//This pass looks for temp operands that are used in multiple blocks, and converts them to primals


/** This is pass probably needs to be changed, but it looks for nonprimals that 
 *  are used in one block and defined in another and then it promotes these 
 *  nonprimals to primal, unless the two blocks in which they exist would later 
 *  be merged together.
 * 
 * @author Kris Peterson
 */
public class ConvInterBlockNonPrimToPrimal extends Pass implements GraphPass {
   
   
  /** ignore if there are interiteration loop dependencies and schedule using 
   *  force directed anyway?
   */
  private boolean _ignoreInterItDataDep;
  private HyperBlockList _hyperBlocks;
  
  public ConvInterBlockNonPrimToPrimal(PassManager pm) {
    this(pm, GlobalOptions.noModSched);
  }
  public ConvInterBlockNonPrimToPrimal(PassManager pm, HyperBlockList hyperBlocks) {
    this(pm, GlobalOptions.noModSched);
    _hyperBlocks = hyperBlocks;
  }
  public ConvInterBlockNonPrimToPrimal(PassManager pm, boolean ignoreDataDep) {
    super(pm);
    _ignoreInterItDataDep = ignoreDataDep;
  }
  
  public boolean optimize(BlockGraph graph) {
     PrimalPromotion promotPrims = new PrimalPromotion(graph);
     
     promotPrims.primalPromotion(_hyperBlocks);
     return true;
  }
  
  public String name() { 
    return "ConvInterBlockNonPrimToPrimal";
  }
   
}


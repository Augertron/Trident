/*
 *
 @LICENSE@
 */


package fp.parser;
import fp.flowgraph.Operand;
import fp.flowgraph.IllegalOperand;
import fp.flowgraph.Type;
import fp.flowgraph.Instruction;
import fp.flowgraph.Phi;
import fp.parser.Varid;
import java.util.*;

class PhiInfo {
  // list of pairs (phi instruction, list of vals to patch)
  public final static LinkedList patchList = new LinkedList();

  public int index;
  public String basename, origname;
  public int version;
  
  public PhiInfo(int ind, String oname, String bname, int vers ) {
	index = ind;
	origname = oname;
	basename = bname;
	version  = vers;
  }

  public String toString() {
    return ("PhiInfo[" + index + ", " + origname + ", " + basename + ", " + version + "]");
  }

  public static void patch() throws IllegalOperand {
    for(ListIterator iter = patchList.listIterator(); iter.hasNext(); ) {
      Instruction phiI = (Instruction)iter.next();
      //System.out.println("Phi inst patched:" + phiI.toString());
      LinkedList phiInfoList = null;
      if (iter.hasNext()) {
        phiInfoList = (LinkedList)iter.next();
      } else continue;

      for (ListIterator iter2 = phiInfoList.listIterator(); iter2.hasNext(); ) {
	PhiInfo pi = (PhiInfo)iter2.next();
        //System.out.println(pi.toString());
	// I don't know if it's a primal or not, so don't know how to do the lookup!
	// We try for a nonprimal first, then primal (based on the original name)
	Operand o = null;
	o = Operand.getOperand(pi.basename, pi.version);
	if (o == null) {
	  o = Operand.getOperand(pi.origname, Operand.NOT_ASSIGNED);
	}
	if (o == null) {
	 //throw IllegalOperand exception 
	 System.err.println("Operand for phi operation not set anywhere: " + pi.basename + pi.version + "...  In phi instruction: " + phiI.toString());
	 //throw new IllegalOperand("Operand for phi operation not set anywhere: " + pi.basename + pi.version + "...  In phi instruction: " + phiI.toString());
	} else {
	  Phi.setVal(phiI, pi.index, o, Phi.getValLabel(phiI, pi.index));
	}
      }
    }
  }

}


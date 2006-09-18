/*
 *
 @LICENSE@
 */


package fp.hwdesc;

import java.util.*;
import fp.hardware.AllocateArrays;
import fp.hardware.ArrayToArrayInfoMap;
import fp.flowgraph.Operator;
import fp.flowgraph.Operand;
import fp.flowgraph.BlockGraph;
import fp.flowgraph.BlockNode;
import fp.hwdesc.memClasses.IndexMatch;

public interface Memory {

  public static IndexMatch matchTester = new IndexMatch();

  public void addArrayInfo(AllocateArrays.ArrayInfo a);
  public void removeArrayInfo(AllocateArrays.ArrayInfo a);
  public AllocateArrays.ArrayInfo getArrayInfo(String aName);
  public HashMap getArrayInfos();
  public String getName();
  public void setName(String name);
  public String getChipName();
  public void setChipName(String cName);
  public int getWidth();
  public void setWidth(int cWidth);
  public int getDepth();
  public void setDepth(int cDepth);
  public void setAddressOffset(long addr);
  public long getAddressOffset();
  public int getVarGroupingsSize();
  public HashSet getVarGroupingsPtr(); 
  public void addToVarGroupingsPtr(HashSet arrSet); 
  public void saveALoadOp(Operator aload);
  public Operator getALoadOp();
  public void saveAStoreOp(Operator astore);
  public Operator getAStoreOp();
  public long getMemSizeLeft();
  public void setMemSizeLeft();
  public void subSpace(long diff);
  public void addSpace(long diff);
  public int getNumOfWriteBus();
  public int getNumOfReadBus();
  public boolean getonlyOneAddy();
  public void displayMemContents(ArrayToArrayInfoMap arrToArrInf);
  public int findSlowestReadLat();
  public int findSlowestWriteLat();
  public boolean saveToPackedArrCollect(ArrayToArrayInfoMap.ArrayInfo array,
                                        ArrayToArrayInfoMap arrToArrInf); 
  public boolean noPackdArrAtLoc(ArrayToArrayInfoMap.ArrayInfo array, 
                                 ArrayToArrayInfoMap arrToArrInf); 
  public void resetPorts();
  public boolean allocateArray(ArrayToArrayInfoMap.ArrayInfo array, 
                               ArrayToArrayInfoMap arrToArrInf); 
  public void deAllocateArray(ArrayToArrayInfoMap.ArrayInfo array);
  public void loadIndexes(BlockGraph designBGraph);
  public int trueCost(BlockNode bNode, ArrayToArrayInfoMap arrToArrInf);
  public int cost(BlockNode bNode, ArrayToArrayInfoMap arrToArrInf);
  public int addLoadTest(BlockNode bNode, int time, Operand array);
  public int addLoadTestCnt(BlockNode bNode, int time, Operand array);
  public int addLoad(BlockNode bNode, int time, Operand array);
  public int addLoadCnt(BlockNode bNode, int time, Operand array);
  public int subLoad(BlockNode bNode, int time, Operand array);
  public int subLoadCnt(BlockNode bNode, int time, Operand array);
  public int addStoreTest(BlockNode bNode, int time, Operand array);
  public int addStoreTestCnt(BlockNode bNode, int time, Operand array);
  public int addStore(BlockNode bNode, int time, Operand array);
  public int addStoreCnt(BlockNode bNode, int time, Operand array);
  public int subStore(BlockNode bNode, int time, Operand array);
  public int subStoreCnt(BlockNode bNode, int time, Operand array);
}

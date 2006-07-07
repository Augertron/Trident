/*
 *
 @LICENSE@
 */


package fp.hwdesc;
import java.util.*;

public class PortUsageSplitter {
  private ArrayList _port;
  public PortUsageSplitter(ArrayList portList) {
    _port = portList;
  }
  
  public Port findBestAddyLoadPort(int time) {
    return findLoadAddyPort(time, true);
  }
  
  public Port findBestDataLoadPort(int time) {
    return findLoadDataPort(time, true);
  }
  
  public Port findWorstAddyLoadPort(int time) {
    return findLoadAddyPort(time, false);
  }
  
  public Port findWorstDataLoadPort(int time) {
    return findLoadDataPort(time, false);
  }
  
  
  public Port findLoadAddyPort(int time, boolean findBest) {
    Port bestPort = null;
    Port port = null;
    int cost = 0;
    for (Iterator portIt = _port.iterator(); 
         portIt.hasNext(); ) {
      port = (Port)portIt.next();
      if(bestPort == null) bestPort = port;
      if((port.typeCode == Port.ADDRESS_READ_TYPE)||
    	 (port.typeCode == Port.ADDRESS_RW_TYPE)) { 
        int portCost = port.getPortUseCnter().testPortUse(time);
        if((((cost==0)||(cost > portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_READ_TYPE)))
	       && findBest) || //find best port
	   (((cost==0)||(cost < portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_RW_TYPE)))
	       && !findBest)) {  //find worse port

    	  cost = portCost;
          bestPort = port;

        }
      }
    }
    return bestPort;
  }
  
  public Port findLoadDataPort(int time, boolean findBest) {
    Port bestPort = null;
    Port port = null;
    int cost = 0;
    for (Iterator portIt = _port.iterator(); 
         portIt.hasNext(); ) {
      port = (Port)portIt.next();
      if(bestPort == null) bestPort = port;
      if((port.typeCode == Port.DATA_READ_TYPE)||
    	 (port.typeCode == Port.DATA_RW_TYPE)) { 
        int portCost = port.getPortUseCnter().testPortUse(time);
        if((((cost==0)||(cost > portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.DATA_READ_TYPE)))
	       && findBest) || //find best port
	   (((cost==0)||(cost < portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.DATA_RW_TYPE)))
	       && !findBest)) {  //find worse port

    	  cost = portCost;
          bestPort = port;

        }
      }
    }
    return bestPort;
  }
  
  public int subLoad(int time) {
  
    Port worstAddyPort = findWorstAddyLoadPort(time);
    Port worstDataPort = findWorstDataLoadPort(time);
    if((worstAddyPort == null)||(worstDataPort == null)) {
      return 0;
    }
    else {
      worstAddyPort.getPortUseCnter().subUse(time);
      worstDataPort.getPortUseCnter().subUse(time);
      //the reason I do this is because I want to take into account the read
      //latency with the cost.  That is, if there are two reads at the same
      //time on a port, they will be staggered by the scheduler--and I'm 
      //assuming that the staggering will be only 1 cycle)--and then the
      //cost is number of reads minus one plus the read latency.  That is
      //graphically, if we have these read attempts at the same cycle:
      //
      // |_____________|
      // |_____________|
      // |_____________|
      //
      //assuming the scheduler staggers each of these reads one cycle we'll 
      //get:
      //
      // |_____________|
      //  |_____________|
      //   |_____________|
      //
      //and then the cost is the number of reads (3) minus 1 plus the latency.
      //Even in this method, where we are subtracting a load, this holds true
      //because the number of loads has just been reduced by one and now 
      //the cost is (2-1) + read_latency
      return (worstDataPort.getPortUseCnter().getLoad(time)-1) + worstDataPort.read_latency;
    }
    
  }

  public int subLoadCnt(int time) {
  
    Port worstAddyPort = findWorstAddyLoadPort(time);
    Port worstDataPort = findWorstDataLoadPort(time);
    if((worstAddyPort == null)||(worstDataPort == null)) {
      return 0;
    }
    else {
      worstAddyPort.getPortUseCnter().subUse(time);
      worstDataPort.getPortUseCnter().subUse(time);
      return worstDataPort.getPortUseCnter().getLoad(time);
    }
    
  }

  public int addLoad(int time) {
  
    Port bestAddyPort = findBestAddyLoadPort(time);
    Port bestDataPort = findBestDataLoadPort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      bestAddyPort.getPortUseCnter().addUse(time);
      bestDataPort.getPortUseCnter().addUse(time);
      return (bestDataPort.getPortUseCnter().getLoad(time)-1) + bestDataPort.read_latency;
    }
    
  }

  public int addLoadCnt(int time) {
  
    Port bestAddyPort = findBestAddyLoadPort(time);
    Port bestDataPort = findBestDataLoadPort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      bestAddyPort.getPortUseCnter().addUse(time);
      bestDataPort.getPortUseCnter().addUse(time);
      return bestDataPort.getPortUseCnter().getLoad(time);
    }
    
  }

  public int addLoadTest(int time) {
  
    Port bestAddyPort = findBestAddyLoadPort(time);
    Port bestDataPort = findBestDataLoadPort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      return (bestDataPort.getPortUseCnter().testPortUse(time)-1) + bestDataPort.read_latency;
    }
    
  }

  public int addLoadTestCnt(int time) {
  
    Port bestAddyPort = findBestAddyLoadPort(time);
    Port bestDataPort = findBestDataLoadPort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      return bestDataPort.getPortUseCnter().testPortUse(time);
    }
    
  }

  public int getLoadDataPortUseCnt(int time) {
  
    Port bestDataPort = findBestDataLoadPort(time);
    if(bestDataPort == null) {
      return 9999;
    }
    else {
      return bestDataPort.getPortUseCnter().getLoad(time);
    }
    
  }

  public int addLoads(int time, int cnt) {
  
    Port bestDataPort = findBestDataLoadPort(time);
    int totalCost = 0;
      System.out.println("time " + time);
    for(int i=0; i<cnt; i++) {
      totalCost += addLoad(time);
    }
    //we need only add the read_latency once:
    if(bestDataPort == null) {
      return 9999;
    }
    else {
      return totalCost - (cnt-1)*bestDataPort.read_latency;
    }
  }

  public int addLoadsCnt(int time, int cnt) {
  
    int totalCost = 0;
    for(int i=0; i<cnt; i++) {
      totalCost += addLoadCnt(time);
    }
    //we need only add the read_latency once:
    return totalCost;
  }

  public Port findBestAddyStorePort(int time) {
    return findStoreAddyPort(time, true);
  }
  
  public Port findBestDataStorePort(int time) {
    return findStoreDataPort(time, true);
  }
  
  public Port findWorstAddyStorePort(int time) {
    return findStoreAddyPort(time, false);
  }
  
  public Port findWorstDataStorePort(int time) {
    return findStoreDataPort(time, false);
  }
  
  public Port findStoreAddyPort(int time, boolean findBest) {
    Port bestPort = null;
    Port port = null;
    for (Iterator portIt = _port.iterator(); 
         portIt.hasNext(); ) {
      port = (Port)portIt.next();
      if(bestPort == null) bestPort = port;
      int cost = port.getPortUseCnter().getLoad(time);
      if((port.typeCode == Port.ADDRESS_WRITE_TYPE)||
    	 (port.typeCode == Port.ADDRESS_RW_TYPE)) { 
        int portCost = port.getPortUseCnter().testPortUse(time);
        if((((cost==0)||(cost > portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_WRITE_TYPE)))
	           && findBest) ||  //find best port
	   (((cost==0)||(cost < portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_RW_TYPE)))
	           && !findBest)){   //find worst port

    	  cost = portCost;
          bestPort = port;

        }
      }
    }
    return bestPort;
  }
  
  public Port findStoreDataPort(int time, boolean findBest) {
    Port bestPort = null;
    Port port = null;
    for (Iterator portIt = _port.iterator(); 
         portIt.hasNext(); ) {
      port = (Port)portIt.next();
      if(bestPort == null) bestPort = port;
      int cost = port.getPortUseCnter().getLoad(time);
      if((port.typeCode == Port.ADDRESS_WRITE_TYPE)||
    	 (port.typeCode == Port.ADDRESS_RW_TYPE)) { 
        int portCost = port.getPortUseCnter().testPortUse(time);
        if((((cost==0)||(cost > portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_WRITE_TYPE)))
	           && findBest) ||  //find best port
	   (((cost==0)||(cost < portCost) || 
    	    ((cost == portCost)&&(port.typeCode == Port.ADDRESS_RW_TYPE)))
	           && !findBest)){   //find worst port

    	  cost = portCost;
          bestPort = port;

        }
      }
    }
    return bestPort;
  }
  
  public int addStore(int time) {
    Port bestAddyPort = findBestAddyStorePort(time);
    Port bestDataPort = findBestDataStorePort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      bestAddyPort.getPortUseCnter().addUse(time);
      bestDataPort.getPortUseCnter().addUse(time);
      return (bestDataPort.getPortUseCnter().getLoad(time)-1) + bestDataPort.write_latency;
    }
  }
  
  public int addStoreCnt(int time) {
    Port bestAddyPort = findBestAddyStorePort(time);
    Port bestDataPort = findBestDataStorePort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      bestAddyPort.getPortUseCnter().addUse(time);
      bestDataPort.getPortUseCnter().addUse(time);
      return bestDataPort.getPortUseCnter().getLoad(time);
    }
  }
  
  public int subStore(int time) {
    Port worstAddyPort = findWorstAddyStorePort(time);
    Port worstDataPort = findWorstDataStorePort(time);
    if((worstAddyPort == null)||(worstDataPort == null)) {
      return 0;
    }
    else {
      worstAddyPort.getPortUseCnter().subUse(time);
      worstDataPort.getPortUseCnter().subUse(time);
      return (worstDataPort.getPortUseCnter().getLoad(time)-1) + worstDataPort.write_latency;
    }
  }
  
  public int subStoreCnt(int time) {
    Port worstAddyPort = findWorstAddyStorePort(time);
    Port worstDataPort = findWorstDataStorePort(time);
    if((worstAddyPort == null)||(worstDataPort == null)) {
      return 0;
    }
    else {
      worstAddyPort.getPortUseCnter().subUse(time);
      worstDataPort.getPortUseCnter().subUse(time);
      return worstDataPort.getPortUseCnter().getLoad(time);
    }
  }
  
  public int addStoreTest(int time) {
    Port bestAddyPort = findBestAddyStorePort(time);
    Port bestDataPort = findBestDataStorePort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      return (bestDataPort.getPortUseCnter().testPortUse(time)-1) + bestDataPort.write_latency;
    }
  }
  
  public int addStoreTestCnt(int time) {
    Port bestAddyPort = findBestAddyStorePort(time);
    Port bestDataPort = findBestDataStorePort(time);
    if((bestAddyPort == null)||(bestDataPort == null)) {
      return 9999;
    }
    else {
      return bestDataPort.getPortUseCnter().testPortUse(time);
    }
  }
  
  public int addStores(int time, int cnt) {
  
    Port bestDataPort = findBestDataStorePort(time);
    int totalCost = 0;
    for(int i=0; i<cnt; i++) {
      totalCost += addStore(time);
    }
    if(bestDataPort == null) {
      return 9999;
    }
    else {
      return totalCost - (cnt-1)*bestDataPort.write_latency;
    }
  }
  
  public int addStoresCnt(int time, int cnt) {
  
    int totalCost = 0;
    for(int i=0; i<cnt; i++) {
      totalCost += addStoreCnt(time);
    }
    return totalCost;
  }
  
  public int getStoreDataPortUseCnt(int time) {
  
    Port bestDataPort = findBestDataStorePort(time);
    if(bestDataPort == null) {
      return 9999;
    }
    else {
      return bestDataPort.getPortUseCnter().getLoad(time);
    }
    
  }
  

}

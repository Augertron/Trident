/*
 *
 @LICENSE@
 */


package fp.hwdesc;
import java.util.*;

public class PortUseCnt extends HashMap {
  /*private class Time {
    private int _time;
    public Time(int time) {_time=time;}
    public int get() {return _time;}
  }
  private class Count {
    private int _cnt;
    public Count(int cnt) {_cnt=cnt;}
    public int get() {return _cnt;}
  }*/
  
  
  //key=time
  //value=count
  
  public PortUseCnt(){super();}
  
  public void put(int time, int cnt) {
    Integer tm = new Integer(time);
    Integer cn = new Integer(cnt);
    super.put(tm, cn);
  }
  
  public int get(int time) {
    Integer tm = new Integer(time);
    if(containsKey(tm))
      return ((Integer)super.get(tm)).intValue();
    else
      return 0;
  }
  
  public int get(Integer time) {
    return ((Integer)super.get(time)).intValue();
  }
  
  public void addUses(int cnt) {
    addUses(0, cnt);
  }
  
  public void addUse(int time) {
    addUses(time, 1);
  }
  
  public void addUses(int time, int cnt) {
    int oldCnt = get(time);
    put(time, oldCnt + cnt);
  }
  
  public void subUses(int cnt) {
    subUses(0, cnt);
  }
  
  public void subUse(int time) {
    subUses(time, 1);
  }
  
  public void subUses(int time, int cnt) {
    int oldCnt = get(time);
    put(time, oldCnt - cnt);
  }
  
  public int testPortUseCntGen(int cnt) {
    return testPortUseCnt(0, cnt);
  }
  
  public int testPortUse(int time) {
    return testPortUseCnt(time, 1);
  }

  public int testPortUseCnt(int time, int cnt) {
    addUses(time, cnt);
    int cost = getLoad(time);
    subUses(time, cnt);
    return cost;
  }

  public int getLoad(int time) {
    return get(time);
  }

  public int getMaxPortLoad() {
    int fullCost = 0;
    for (Iterator costIt = keySet().iterator(); 
    	 costIt.hasNext(); ) {
      Integer time = (Integer)costIt.next();
      fullCost = Math.max(get(time), fullCost);
    }
    return fullCost;
  }

}

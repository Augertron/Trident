/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;
import fp.util.*;
import java.io.*;

import fp.hardware.*;

/** This class stores the windows used by the force directed scheduler as well
 *  as methods for editing them.
 * 
 * @author Kris Peterson
 */
public class FDWindows extends HashMap
{
  
  private float _absMax;
  
  public FDWindows() {
    super();
  }

  private class Window {
  
    public Window() {
    
    }
    
    public float max;
    public float min;
  
  }  
  
  public float getWinSize(Object key) {
  
    Window win = (Window)this.get(key);
                 //it shouldn't be necessary if the max and min really are max
		 //and min to take the absolute value
    //float size = Math.abs(win.max - win.min);
    float size = win.max - win.min;
    return size;
  }
  public float getMax(Object key) {
  
    Window win = (Window)this.get(key);
    float max = win.max;
    return max;
  }
  public float getMin(Object key) {
  
    Window win = (Window)this.get(key);
    float min = win.min;
    return min;
  }
  public void putMax(Object key, float max) {
  
    Window win;
    if(this.containsKey(key)) 
      win = (Window)this.get(key);
    else
      win = new Window();
    max = ((float)((int)((max+0.0005)*1000)))/1000; 
    win.max = max;
    this.put(key, win);
  }
  public void putMin(Object key, float min) {
  
    Window win;
    if(this.containsKey(key)) 
      win = (Window)this.get(key);
    else
      win = new Window();
    min = ((float)((int)((min+0.0005)*1000)))/1000; 
    win.min = min;
    this.put(key, win);
  }
  public void putWin(Object key, float max, float min) {
  
    Window win;
    if(this.containsKey(key)) 
      win = (Window)this.get(key);
    else
      win = new Window();
    max = ((float)((int)((max+0.0005)*1000)))/1000; 
    win.max = max;
    min = ((float)((int)((min+0.0005)*1000)))/1000; 
    win.min = min;
    this.put(key, win);
  }
  public FDWindows copy() {
  
    FDWindows clone = new FDWindows();
    for (Iterator it = this.keySet().iterator(); it.hasNext(); ) {
      Instruction win = (Instruction)it.next();
      float max = this.getMax(win);
      float min = this.getMin(win);
      clone.putWin(win, max, min);
    }
    clone._absMax = _absMax;
    return clone;
  
  }
  public FDWindows clone() {
  
    FDWindows clone = new FDWindows();
    for (Iterator it = this.keySet().iterator(); it.hasNext(); ) {
      Instruction inst = (Instruction)it.next();
      Window winOld = (Window)this.get(inst);
      Window winNew = new Window();
      winNew.max = winOld.max;
      winNew.min = winOld.min;
      //float max = this.getMax(win);
      //float min = this.getMin(win);
      clone.put(inst, winNew);
    }
    clone._absMax = _absMax;
    return clone;
  
  }
  
  public void loadWinsFromALAPnASAPScheds(Instruction instr, 
                                           ArrayList aSAPlist,
					   ArrayList aLAPlist) {
    float absMaxTime = -9999;
    //foreach instruction
    float minTime, maxTime;
    //find the ASAP and ALAP execution times
    //int ASAPi = aSAPlist.indexOf(instr);
    int ASAPi = -1;
    for(int i = 0; i < aSAPlist.size();i++) {
      Instruction asapCopyOfInst = ((Instruction)(aSAPlist.get(i)));
      String asapCopyAsString = asapCopyOfInst.toString();
      String originalAsString = instr.toString();
      //I have to use string compare, because the copy created two truly different
      //objects for each instruction
      if(asapCopyAsString.compareTo(originalAsString) == 0) {
    	//save location of this instruction in asap list:
        ASAPi = i;
    	continue;
      }
       
    }
    //int ALAPi = aLAPlist.indexOf(instr);
    int ALAPi = -1;
    for(int i = 0; i < aLAPlist.size();i++) {
      Instruction alapCopyOfInst = ((Instruction)(aLAPlist.get(i)));
      String alapCopyAsString = alapCopyOfInst.toString();
      String originalAsString = instr.toString();
      if(alapCopyAsString.compareTo(originalAsString) == 0) {
    	ALAPi = i;
    	continue;
      }
       
    }
    Instruction asapCopy = ((Instruction)(aSAPlist.get(ASAPi)));
    Instruction alapCopy = ((Instruction)(aLAPlist.get(ALAPi)));
    float ASAPexectime = asapCopy.getExecTime();
    float ALAPexectime = alapCopy.getExecTime();
    minTime = Math.min(ASAPexectime, ALAPexectime);
    maxTime = Math.max(ASAPexectime, ALAPexectime);
    
    _absMax = Math.max(_absMax, maxTime);
     
    putWin(instr, maxTime, minTime);
  }
  
  public int getabsMax() {return ((int)(_absMax+0.999))+1;}
  
  public Instruction findInstWithMinWin(ArrayList instrList) { 
  
    Instruction instr = null;	 
    float minWin = 999;
    //find the instruction with the smallest window, and find a time slot
    //to schedule this one in first
    for (Iterator itsTmp = instrList.iterator(); 
        itsTmp.hasNext(); ) {
      Instruction instTmp2 = (Instruction)itsTmp.next();
      if(getWinSize(instTmp2) < minWin) {
    	minWin = getWinSize(instTmp2);
    	instr = instTmp2;
      }

    }
    
    return instr;
  
  }
}

/*
 *
 @LICENSE@
 */


package fp.flowgraph;

import java.util.*;
import fp.util.*;
import java.io.*;

//this class is used to store the database used in Schedule.java.  Schedule.java
//has a private ArrayList, which stores elements from this class.  Each of these elements
//contains a list of instructions, and an integer for the clock tick, in which these
//instructions should be executed


public class ClockInstrucList 
{

  private int ExecClkCnt;
  private ArrayList _InstList;

  public ClockInstrucList()
  {
    ExecClkCnt = 0;
    _InstList = new ArrayList();
  }

  public ClockInstrucList(int clk)
  {
    ExecClkCnt = clk;
    _InstList = new ArrayList();
  }
  
  public ClockInstrucList(int clk, Instruction inst)
  {
    ExecClkCnt = clk;
    _InstList = new ArrayList();
    _InstList.add(inst);
  }

  public ClockInstrucList(int clk, ArrayList instList)
  {
    ExecClkCnt = clk;
    _InstList = new ArrayList(instList);
  }

  public void addInst(Instruction inst)
  {
    _InstList.add(inst);
  }  

  public ArrayList getInstList()
  {
    return (_InstList);
  }  

  public void removeInst(Instruction inst)
  {
    _InstList.remove(inst);
  }  

  public int getClk()
  {
    return ExecClkCnt;
  }
  
  public void setClk(int clk)
  {
    ExecClkCnt = clk;
  }
  

}

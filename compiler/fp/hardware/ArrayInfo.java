/*
 *
 @LICENSE@
 */


package fp.hardware;

import java.util.*;

import fp.flowgraph.*;

/** This class saves information about arrays from the c program.
 * 
 * @author Kris Peterson
 */
public class ArrayInfo
{
  private String _name;
  private Operand _var;
  private float _startAddress;
  private float _stopAddress;
  private int _wordSize;
  private float _arraySize;
  
  public ArrayInfo(Operand var, float arraySize, int wordSize) {
  
    _var = var;
    _name = var.toString();
    _wordSize = wordSize;
    _arraySize = arraySize;
  
  }
  
  public Operand getVar() {return _var;}
  public int getWordSize() {return _wordSize;}
  public float getArraySize() {return _arraySize;}
  public float getStart() {return _startAddress;}
  public void setStart(float stAddy) {
  
    _startAddress = stAddy;
    _stopAddress = stAddy + _arraySize;
  
  }
  public float getStop() {return _stopAddress;}

}

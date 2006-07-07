/*
 *
 @LICENSE@
 */


package fp.circuit.vhdl;

import java.util.*;

public class Library {
  String name;
  String libname;
  String include;
  
  private HashMap _lib_objects;
  //private HashMap _name_objects;

  public Library(String s) {
    name = s;
    _lib_objects = new HashMap();
  }
  
  void addLibObject(String lib, LibObject lo) {
    _lib_objects.put(lib, lo);
  }

  /*
  void addNameObjects(String name, LibObject lo) {
    _name_objects.put(name, lo);
  }
  */

  public LibObject getLibObject(String lib) {
    return (LibObject)_lib_objects.get(lib);
  }

  /*
  LibObject getNameObject(String name) {
    return (LibObject)_name_objects.get(name);
    } */
  
  

}

/*
 *
 @LICENSE@ 
 */ 
package fp.util;

/**
 * This is a convenience class for naming things.  It is not too
 * fancy, but many structures in circuit synthesis are easier to deal
 * with if they have names.
 * 
 * @author Justin L. Tripp
 * @version $version$
 */
public class Nameable {
  /**
   * The name.
   */
  String _name;
  
  String _default_name;
  String _instance_name;

  public Nameable(String name, String instance) {
    _default_name = name;
    _instance_name = instance;
    _name = name;
  }


  /**
   * The constructor to extend, so that you have naming capability.
   * 
   * @param name The name
   */
  public Nameable(String name) {
    this(name, null);
  }

  /**
   * simple accessor.
   * 
   * @return Get the name.
   */
  public String getName() { return _name; }
  public String getUniqueName() { return _instance_name; }
  public String getDefaultName() { return _default_name; }

  protected void setName(String n) { _name = n; }
  protected void setUniqueName(String n) { _instance_name = n; }
  protected void setDefaultName(String n) { _default_name = n; }

  /**
   * Print the name.
   * 
   * @return String with output
   */
  public String toString() {
    return _name;
  }
}

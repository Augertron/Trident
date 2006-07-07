/*
 *
 @LICENSE@
*/

package fp.util.vhdl.generator;

import java.util.HashSet;
import java.util.Iterator;


public class LibraryUnit extends VHDLBase implements VHDLout {

  // according to my revised parse stuff, this is goofy.
    
  // Entity
  // Architectures
  // Package
  // Configuration

  private Entity _entity;
  private Architecture _arch;

  private SimpleName _simple_name;

  public LibraryUnit(String name) {
    super(name);
    _simple_name = new SimpleName(name);
    _entity = new Entity(_simple_name);
    _arch = new Architecture(name);
  }
  
  public Entity getEntity() { return _entity; }
  void setEntity(Entity e) { _entity = e; }

  public Architecture getArchitecture() { return _arch; }
  void setArchitecture(Architecture a) { _arch = a; }


  protected void appendBody(StringBuffer s, String pre) {
    _entity.toVHDL(s,pre);
    _arch.toVHDL(s, pre);
  }

  
}

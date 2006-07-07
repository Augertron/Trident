/*
 *
 @LICENSE@
 */


package fp.parser;

class Varid {
  private String _basename;
  private int _version = 0;
  private boolean _isVersioned;
  public Varid(String basename, int version) {
    _basename = basename;
    _version = version;
    _isVersioned =  true;
  }
  public Varid(String basename) {
    _basename = basename;
    _isVersioned = false;
  }
  public boolean isVersioned() {return _isVersioned;}
  public int getVersion() {return _version;}
  public String getBasename() {return _basename;}
}


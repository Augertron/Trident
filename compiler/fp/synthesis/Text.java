/*
 *
 @LICENSE@
 */


package fp.synthesis;

public interface Text {
  public static final String START = "(";
  public static final String END = ")";
  public static final String LINE = "\n";
  public static final String TAB = "\t";
  public static final String SPACE = " ";

  public String toText(String prefix);

}

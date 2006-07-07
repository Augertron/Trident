/*
 *
 @LICENSE@
 */


package fp.graph;

/**
 * Abstract superclass for Nodes and Edges
 *
 * @author Nathan Kitchen
 */
public abstract class AbstractGraphComponent extends DotAttributes {
  private Object _tag;

  /**
   * Constructs a new AbstractGraphComponent with a null tag
   */
  public AbstractGraphComponent() {
    this(null);
  }
  
  /**
   * Constructs a new AbstractGraphComponent with the given tag
   */
  public AbstractGraphComponent(Object tag) {
    setTag(tag);
  }
  
  /**
   * Returns the tag object
   */
  public Object getTag() {
    return _tag;
  }

  /**
   * Sets the value of the dot attribute "label" to the return value of
   * <code>toString</code>
   */
  public void setDotLabelFromToString() {
    setDotAttribute("label", toString());
  }
  
  /**
   * Replaces the tag object
   */
  public void setTag(Object tag) {
    _tag = tag;
  }

  /**
   * Returns a String representation of this component, which is derived from
   the dot attribute &quot;label&quot;, if possible, or the tag
   */
  public String toString() {
    String label = getDotAttribute("label");
    if (label == null) {
      Object tag = getTag();
      if (tag == null) {
	return super.toString();
      } // end of if ()
      else {
	return tag.toString();
      } // end of else
    }
    else {
      return label;
    } // end of else
  }
}

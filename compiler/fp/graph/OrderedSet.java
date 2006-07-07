/*
 *
 @LICENSE@
 */


package fp.graph;

import java.util.*;

/**
 * Utility class that obeys the constraints of the Set interface but also
 * preserves the order in which the elements are added
 */
public class OrderedSet extends HashSet {
  List _list;

  public OrderedSet() {
    _list = new ArrayList();
  }

  public boolean add(Object o) {
    boolean success = super.add(o);
    if (success) {
      _list.add(o);
    } // end of if ()
    return success;
  }

  public void clear() {
    super.clear();
    _list.clear();
  }

  public Iterator iterator() {
    return new Iterator() { 
	Object _lastReturned = null;
	Iterator _listIter = _list.iterator();

	public Object next() {
	  _lastReturned = _listIter.next();
	  return _lastReturned;
	}

	public boolean hasNext() {
	  return _listIter.hasNext();
	}

	public void remove() {
	  if (_lastReturned == null) {
	    throw new IllegalStateException();
	  } // end of if ()
	  superRemove(_lastReturned);
	  _listIter.remove();
	}
      };
  }

  public boolean remove(Object o) {
    boolean success = superRemove(o);
    if (success) {
      _list.remove(o);
    } // end of if ()
    return success;
  }

  private boolean superRemove(Object o) {
    return super.remove(o);
  }

  public Object[] toArray() {
    return _list.toArray();
  }
}


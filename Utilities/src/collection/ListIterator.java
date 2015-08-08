/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Iterator;

/**
 * Adds forward and backward traversal into iterator operations. Includes
 * relative add, remove, and set modifications. Labels an iterator specialized
 * for lists with indexes.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface ListIterator<E> extends Iterator<E> {

	/**
	 * Inserts parameter object before next() or after previous(). A call to
	 * next is unaffected, and a call to previous returns added object.
	 * Increases the return value of nextIndex() or previousIndex() by one. Only
	 * valid if called after next() or previous() and before remove() or set(E
	 * e).
	 * 
	 * @param e
	 *            object being added by iterator
	 */
	void add(E e);

	/**
	 * Checks if an iterator has more elements to call next() upon.
	 * 
	 * @return true if the iterator has more elements, false otherwise
	 */
	boolean hasNext();

	/**
	 * Checks if an iterator has more elements to call previous() upon.
	 * 
	 * @return true if the iterator has more elements, false otherwise
	 */
	boolean hasPrevious();

	/**
	 * Returns the next element in iterator.
	 * 
	 * @return the next element in iterator
	 */
	E next();

	/**
	 * Returns position of element in following call to next(), size if at end.
	 * 
	 * @return position of element in following call to next(), size if at end
	 */
	int nextIndex();

	/**
	 * Returns the previous element in iterator.
	 * 
	 * @return the previous element in iterator
	 */
	E previous();

	/**
	 * Returns position of element in following call to previous, -1 if at
	 * start.
	 * 
	 * @return position of element in following call to previous, -1 if at start
	 */
	int previousIndex();

	/**
	 * Removes the last element returned by next() or previous(). Decreases the
	 * return value of nextIndex() or previousIndex() by one. Only valid if
	 * called after next() or previous() and before set(E e) or add(E e).
	 */
	void remove();

	/**
	 * Sets the last element returned by next() or previous() with parameter
	 * object. Only valid if called after next() or previous() and before
	 * remove() or add().
	 * 
	 * @param e
	 *            object being set by iterator
	 */
	void set(E e);

}
package sets;

import java.util.Iterator;

/**
 * Extends the definition of sorted set whose iterator ascends the set in
 * ascending order. Adds additional useful behavior and should be used in place
 * of SortedSet<E>.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface NavigableSet<E> extends SortedSet<E> {

	/**
	 * Returns least element in set greater than or equal to e.
	 * 
	 * @param e
	 *            boundary element
	 * @return least element in set greater than or equal to e, or null if
	 *         element does not exist.
	 */
	public E ceiling(E e);

	/**
	 * Returns reverse order iterator.
	 * 
	 * @return reverse order iterator
	 */
	public Iterator<E> descendingIterator();

	/**
	 * Returns reverse order view of elements in set.
	 * 
	 * @return reverse order view of element in set
	 */
	public NavigableSet<E> descendingSet();

	/**
	 * Returns greatest element in set less than or equal to e.
	 * 
	 * @param e
	 *            boundary element
	 * @return greatest element in set less than or equal to e, or null if
	 *         element does not exist.
	 */
	public E floor(E e);

	/**
	 * Improves SortedSet implementation by allowing client to define
	 * inclusiveness. Returns all elements less than toElement.
	 * 
	 * @param toElement
	 *            maximum element
	 * @param inclusive
	 *            whether or not to include toElement
	 * @return all elements less than toElement
	 */
	public NavigableSet<E> headSet(E toElement, boolean inclusive);

	/**
	 * Returns least element in set strictly greater than e.
	 * 
	 * @param e
	 *            boundary element
	 * @return least element in set strictly greater than e, or null if element
	 *         does not exist
	 */
	public E higher(E e);

	/**
	 * Returns greatest element in set strictly less than e.
	 * 
	 * @param e
	 *            boundary element
	 * @return greatest element in set strictly less than e, or null if element
	 *         does not exist
	 */
	public E lower(E e);

	/**
	 * Returns first element in set.
	 * 
	 * @return first element in set, or null if set is empty.
	 */
	public E pollFirst();

	/**
	 * Returns last element in set.
	 * 
	 * @return last element in set, or null if set is empty.
	 */
	public E pollLast();

	/**
	 * Improves SortedSet implementation by allowing client to define
	 * inclusiveness. Returns all elements greater than fromElement and less
	 * than toElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @param fromInclusive
	 *            whether or not to include fromElement
	 * @param toElement
	 *            maximum element
	 * @param toInclusive
	 *            whether or not to include toElement
	 * @return all elements greater than fromElement and less than toElement
	 */
	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive,
			E toElement, boolean toInclusive);

	/**
	 * Improves SortedSet implementation by allowing client to define
	 * inclusiveness. Returns all elements greater than fromElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @param inclusive
	 *            whether or not to include fromElement
	 * @return all elements greater than fromElement
	 */
	public NavigableSet<E> tailSet(E fromElement, boolean inclusive);

}

package collection;

import java.util.Comparator;

/**
 * Applies to data structures holding navigable elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public interface Navigable<E> {

	/**
	 * Returns greatest element greater than or equal to e.
	 * 
	 * @param e
	 *            boundary object
	 * @return greatest element greater than or equal to e, null otherwise
	 */
	E ceiling(E e);

	/**
	 * Returns a sorted collections comparator. If natural ordering is used,
	 * null is returned.
	 * 
	 * @return set's comparator. null if naturally ordered.
	 */
	Comparator<E> comparator();

	/**
	 * Returns greatest element less than or equal to e.
	 * 
	 * @param e
	 *            boundary object
	 * @return greatest element less than or equal to e, null otherwise
	 */
	E floor(E e);

	/**
	 * Returns least element strictly greater than e.
	 * 
	 * @param e
	 *            boundary object
	 * @return least element strictly greater than e, null otherwise
	 */
	E higher(E e);

	/**
	 * Returns greatest element strictly less than e.
	 * 
	 * @param e
	 *            boundary object
	 * @return greatest element strictly less than e, null otherwise
	 */
	E lower(E e);

	/**
	 * Returns last element in a collection.
	 * 
	 * @return last element in a collection, null otherwise
	 */
	E pollLast();

	/**
	 * Returns first element in a collection.
	 * 
	 * @return first element in a collection, null otherwise
	 */
	E pollFirst();

}

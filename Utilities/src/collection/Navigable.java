package collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Defines behaviors for collections with sorted elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public interface Navigable<E extends Comparable<? super E>> {

	/**
	 * Returns the least element that is greater than or equal to the given
	 * parameter.
	 * 
	 * @param e
	 *            element for comparison
	 * @return least (or equal) element or null
	 */
	E ceiling(E e);

	/**
	 * Compares two objects for ordering.
	 * 
	 * This implementation defaults null values to be less than non-null values.
	 * Additionally, if a null comparator is provided, objects (cast to
	 * {@code Comparable<Object>}) are directly compared. ClassCastException may
	 * be thrown when using direct comparison of two non-null objects.
	 * 
	 * @param comparator
	 *            used to impose ordering
	 * @param o1
	 *            first object
	 * @param o2
	 *            second object
	 * @return negative, zero, or positive integer if the first object is less
	 *         than, equal to, or greater than the second object
	 */
	default int compare(Comparator<? super E> comparator, E o1, E o2) {
		return comparator == null ? ((o1 == null || o2 == null) ? (o1 == o2 ? 0
				: (o1 == null) ? -1 : 1) : o1.compareTo(o2)) : comparator
				.compare(o1, o2);
	}

	/**
	 * Returns an iterator traversing elements in the opposite direction of a
	 * collection's standard iterator.
	 * 
	 * @return iterator traversing elements in the opposite direction of a
	 *         collection's standard iterator
	 */
	Iterator<E> descendingIterator();

	/**
	 * Returns the greatest element that is less than or equal to the given
	 * parameter.
	 * 
	 * @param e
	 *            element for comparison
	 * @return greatest (or equal) element or null
	 */
	E floor(E e);

	/**
	 * Returns the least element that is strictly greater than the given
	 * parameter.
	 * 
	 * @param e
	 *            element for comparison
	 * @return least element or null
	 */
	E higher(E e);

	/**
	 * Returns the greatest element that is strictly less than the given
	 * parameter.
	 * 
	 * @param e
	 *            element for comparison
	 * @return greatest element or null
	 */
	E lower(E e);

	/**
	 * Returns the greatest element.
	 * 
	 * @return greatest element or null
	 */
	E maximum();

	/**
	 * Returns the least element.
	 * 
	 * @return least element or null
	 */
	E minimum();

}

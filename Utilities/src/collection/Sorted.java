/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Comparator;

/**
 * Applies to data structures holding sorted elements.
 * 
 * @param <E>
 *            The type of the elements stored in a data structure.
 * 
 * @author Jacob Malter
 */
public interface Sorted<E> {

	/**
	 * Returns a sorted collections comparator. If natural ordering is used,
	 * null is returned.
	 * 
	 * @return set's comparator. null if naturally ordered.
	 */
	Comparator<E> comparator();

	/**
	 * Returns the maximum object that is greater than the object parameter.
	 * 
	 * @param e
	 *            boundary object
	 * @return maximum object
	 */
	E greatestLowerBound(E e);

	/**
	 * Returns the maximum object that is greater than the object parameters.
	 * Performs the operation over all objects in the array.
	 * 
	 * @param e
	 *            boundary object(s)
	 * @return maximum object
	 */
	E greatestLowerBound(E[] e);

	/**
	 * Returns the minimum object that is greater than the object parameter.
	 * 
	 * @param e
	 *            boundary object
	 * @return minimum object
	 */
	E leastUpperBound(E e);

	/**
	 * Returns the minimum object that is greater than the object parameters.
	 * Performs the operation over all objects in the array.
	 * 
	 * @param e
	 *            boundary object(s)
	 * @return minimum object
	 */
	E leastUpperBound(E[] e);

	/**
	 * Gets greatest in a collection.
	 * 
	 * @return greatest element in a collection
	 */
	E maximum();

	/**
	 * Gets least element in a collection.
	 * 
	 * @return least element in a collection
	 */
	E minimum();
}

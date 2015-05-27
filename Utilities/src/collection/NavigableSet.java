/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Further removed from the mathematical definition of a set. Order does matter,
 * and replacement is not allowed. Includes subset operations.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface NavigableSet<E> extends Set<E>, Navigable<E> {

	/**
	 * Returns all elements less than toElement.
	 * 
	 * @param toElement
	 *            maximum element
	 * @param inclusive
	 *            toElement is included in return
	 * @return all elements less than toElement
	 */
	NavigableSet<E> headSet(E toElement, boolean inclusive);

	/**
	 * Returns all elements greater than fromElement and less than toElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @param fromInclusive
	 *            fromElement is included in return
	 * @param toElement
	 *            maximum element
	 * @param toInclusive
	 *            toElement is included in return
	 * @return all elements greater than fromElement and less than toElement
	 */
	NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement,
			boolean toInclusive);

	/**
	 * Returns all elements greater than fromElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @param inclusive
	 *            fromElement is included in return
	 * @return all elements greater than fromElement
	 */
	NavigableSet<E> tailSet(E fromElement, boolean inclusive);

}

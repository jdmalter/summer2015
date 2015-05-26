package sets;

import java.util.Comparator;

/**
 * Extends the definition of set whose iterator ascends the set in ascending
 * order.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface SortedSet<E> extends Set<E> {

	/**
	 * Returns set's comparator if given one at construction time.
	 * 
	 * @return set's comparator if given one at construction time
	 */
	public Comparator<? super E> comparator();

	/**
	 * Returns first element in set.
	 * 
	 * @return first element in set
	 */
	public E first();

	/**
	 * Returns all elements less than toElement.
	 * 
	 * @param toElement
	 *            maximum element
	 * @return all elements less than toElement
	 */
	public SortedSet<E> headSet(E toElement);

	/**
	 * Returns last element in set.
	 * 
	 * @return last element in set
	 */
	public E last();

	/**
	 * Returns all elements greater than fromElement and less than toElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @param toElement
	 *            maximum element
	 * @return all elements greater than fromElement and less than toElement
	 */
	public SortedSet<E> subSet(E fromElement, E toElement);

	/**
	 * Returns all elements greater than fromElement.
	 * 
	 * @param fromElement
	 *            minimum element
	 * @return all elements greater than fromElement
	 */
	public SortedSet<E> tailSet(E fromElement);

}

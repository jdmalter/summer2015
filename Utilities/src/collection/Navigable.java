package collection;

/**
 * Applies to data structures holding navigable elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public interface Navigable<E> extends Sorted<E> {
	/**
	 * Returns ceiling on parameter element.
	 * 
	 * @param e
	 *            object to match
	 * @return least element greater or equal to e, null otherwise
	 */
	E ceiling(E e);

	/**
	 * Returns floor on parameter element.
	 * 
	 * @param e
	 *            object to match
	 * @return greatest element less or equal to e, null otherwise
	 */
	E floor(E e);

	/**
	 * Returns higher on parameter element.
	 * 
	 * @param e
	 *            object to match
	 * @return least element higher (but not equal) to e, null otherwise
	 */
	E higher(E e);

	/**
	 * Returns lower on parameter element.
	 * 
	 * @param e
	 *            object to match
	 * @return greatest element lower (but not equal) to e, null otherwise
	 */
	E lower(E e);

}

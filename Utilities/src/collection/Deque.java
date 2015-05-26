/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Adds behavior to add and remove from both sides. Imitates a double sided
 * queue or stack.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Deque<E> extends Queue<E> {

	/**
	 * Adds object parameter to front of deque.
	 * 
	 * @param obj
	 *            object added into queue
	 * @return true if object was added, false otherwise
	 */
	boolean addFront(E obj);

	/**
	 * Returns last element without removing it.
	 * 
	 * @return last element of deque
	 */
	E peekLast();

	/**
	 * Remove object from front of deque.
	 * 
	 * @return last element of deque
	 */
	E removeLast();

}

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior for a first in, first out (FIFO) collection.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Queue<E> extends Collection<E> {

	/**
	 * Adds object parameter to back of queue.
	 * 
	 * @param obj
	 *            object added into queue
	 * @return true if object was added, false otherwise
	 */
	boolean add(E obj);

	/**
	 * Offers object parameter to back of queue. Adds object if size
	 * restrictions are not violated.
	 * 
	 * @param obj
	 *            object offered into queue
	 */
	void offer(E obj);

	/**
	 * Returns first element without removing it.
	 * 
	 * @return first element of queue
	 */
	E peek();

	/**
	 * Remove object from front of queue.
	 * 
	 * @return first element of queue
	 */
	E remove();

}

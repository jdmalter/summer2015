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
	 * Returns first element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return first element
	 */
	E element();

	/**
	 * Offers object parameter to back of queue. Adds object if size
	 * restrictions are not violated.
	 * 
	 * @param obj
	 *            object offered into queue
	 * @return true if successful
	 */
	boolean offer(E obj);

	/**
	 * Returns first element but does not remove.
	 * 
	 * @return first element, null if empty
	 */
	E peek();

	/**
	 * Returns first element and removes.
	 * 
	 * @return first element, null if empty
	 */
	E poll();

	/**
	 * Returns first element and removes. Throws exception if queue is empty.
	 * 
	 * @return first element
	 */
	E remove();

}

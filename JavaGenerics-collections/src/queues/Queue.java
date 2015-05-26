package queues;

import java.util.Collection;

/**
 * A collection with first-in-first-out (FIFO) operations.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface Queue<E> extends Collection<E> {

	/**
	 * Inserts element if possible.
	 * 
	 * @param e
	 *            element inserted
	 * @return true if successful, false otherwise
	 */
	public boolean offer(E e);

	/**
	 * Returns first element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return first element
	 */
	public E element();

	/**
	 * Returns first element and removes. Throws exception if queue is empty.
	 * 
	 * @return first element
	 */
	public E remove();

	/**
	 * Returns first element but does not remove.
	 * 
	 * @return first element, null if empty
	 */
	public E peek();

	/**
	 * Returns first element and removes.
	 * 
	 * @return first element, null if empty
	 */
	public E poll();

}

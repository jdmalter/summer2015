package queues;

import java.util.Iterator;

/**
 * A double ended queue.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface Deque<E> extends Queue<E> {

	/**
	 * Insert e at head.
	 * 
	 * Similar to {@code add(E e)} from collection but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 */
	public void addFirst(E e);

	/**
	 * Insert e at tail.
	 * 
	 * Similar to {@code add(E e)} from collection but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 */
	public void addLast(E e);

	/**
	 * Returns reverse order iterator over elements.
	 * 
	 * @return reverse order iterator
	 */
	public Iterator<E> descendingIterator();

	/**
	 * Returns head element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return head element
	 */
	public E getFirst();

	/**
	 * Returns tail element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return tail element
	 */
	public E getLast();

	/**
	 * Inserts element at head if possible.
	 * 
	 * Similar to {@code offer(E e)} but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 * @return true if successful, false otherwise
	 */
	public boolean offerFirst(E e);

	/**
	 * Inserts element at tail if possible.
	 * 
	 * Similar to {@code offer(E e)} but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 * @return true if successful, false otherwise
	 */
	public boolean offerLast(E e);

	/**
	 * Returns head element but does not remove.
	 * 
	 * @return head element, null if empty
	 */
	public E peekFirst();

	/**
	 * Returns tail element but does not remove.
	 * 
	 * @return tail element, null if empty
	 */
	public E peekLast();

	/**
	 * Returns head element and removes.
	 * 
	 * @return head element, null if empty
	 */
	public E pollFirst();

	/**
	 * Returns tail element and removes.
	 * 
	 * @return tail element, null if empty
	 */
	public E pollLast();

	/**
	 * Returns head element and removes. Throws exception if queue is empty.
	 * 
	 * @return head element
	 */
	public E pop();

	/**
	 * Insert e at head.
	 * 
	 * Similar to {@code add(E e)} from collection but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 */
	public void push(E e);

	/**
	 * Returns head element and removes. Throws exception if queue is empty.
	 * 
	 * @return head element
	 */
	public E removeFirst();

	/**
	 * Removes first object from collection.
	 * 
	 * Similar to {@code remove(Object o} from collection but specifies
	 * behavior.
	 * 
	 * @param o
	 *            object being removed
	 * @return true if element removed
	 */
	public boolean removeFirstOccurence(Object o);

	/**
	 * Returns tail element and removes. Throws exception if queue is empty.
	 * 
	 * @return tail element
	 */
	public E removeLast();

	/**
	 * Removes last object from collection.
	 * 
	 * Similar to {@code remove(Object o} from collection but specifies
	 * behavior.
	 * 
	 * @param o
	 *            object being removed
	 * @return true if element removed
	 */
	public boolean removeLastOccurence(Object o);

}

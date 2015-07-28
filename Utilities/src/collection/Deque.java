/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Iterator;

/**
 * Adds behavior to add and remove from both sides. Imitates a double sided
 * queue or stack.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Deque<E> extends Queue<E>, Stack<E> {

	/**
	 * Insert e at head.
	 * 
	 * Similar to {@code add(E e)} from collection but implicitly returns true
	 * or throws an exception.
	 * 
	 * @param e
	 *            element inserted
	 */
	void addFirst(E e);

	/**
	 * Insert e at tail.
	 * 
	 * Similar to {@code add(E e)} from collection but implicitly returns true
	 * or throws an exception.
	 * 
	 * @param e
	 *            element inserted
	 */
	void addLast(E e);

	/**
	 * Returns reverse order iterator over elements.
	 * 
	 * @return reverse order iterator
	 */
	Iterator<E> descendingIterator();

	/**
	 * Returns head element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return head element
	 */
	E getFirst();

	/**
	 * Returns tail element but does not remove. Throws exception if queue is
	 * empty.
	 * 
	 * @return tail element
	 */
	E getLast();

	/**
	 * Inserts element at head if possible.
	 * 
	 * Similar to {@code offer(E e)} but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 * @return true if successful, false otherwise
	 */
	boolean offerFirst(E e);

	/**
	 * Inserts element at tail if possible.
	 * 
	 * Similar to {@code offer(E e)} but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 * @return true if successful, false otherwise
	 */
	boolean offerLast(E e);

	/**
	 * Returns head element but does not remove.
	 * 
	 * @return head element, null if empty
	 */
	E peekFirst();

	/**
	 * Returns tail element but does not remove.
	 * 
	 * @return tail element, null if empty
	 */
	E peekLast();

	/**
	 * Returns head element and removes.
	 * 
	 * @return head element, null if empty
	 */
	E pollFirst();

	/**
	 * Returns tail element and removes.
	 * 
	 * @return tail element, null if empty
	 */
	E pollLast();

	/**
	 * Returns head element and removes. Throws exception if queue is empty.
	 * 
	 * @return head element
	 */
	E pop();

	/**
	 * Insert e at head.
	 * 
	 * Similar to {@code add(E e)} from collection but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 * @return
	 */
	void push(E e);

	/**
	 * Returns head element and removes. Throws exception if queue is empty.
	 * 
	 * @return head element
	 */
	E removeFirst();

	/**
	 * Removes first object from collection.
	 * 
	 * Similar to {@code remove(Object o)} from collection but specifies
	 * behavior.
	 * 
	 * @param o
	 *            object being removed
	 * @return true if element removed
	 */
	boolean removeFirstOccurence(Object o);

	/**
	 * Returns tail element and removes. Throws exception if queue is empty.
	 * 
	 * @return tail element
	 */
	E removeLast();

	/**
	 * Removes last object from collection.
	 * 
	 * Similar to {@code remove(Object o)} from collection but specifies
	 * behavior.
	 * 
	 * @param o
	 *            object being removed
	 * @return true if element removed
	 */
	boolean removeLastOccurence(Object o);

}

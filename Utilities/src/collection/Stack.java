/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior for a last in, first out (LIFO) collection.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Stack<E> extends Collection<E> {

	/**
	 * Offers object parameter to back of stack. Pushes object if size
	 * restrictions are not violated.
	 * 
	 * @param obj
	 *            object offered into stack
	 * @return true if successful
	 */
	boolean offer(E obj);

	/**
	 * Returns top element without removing it.
	 * 
	 * @return first element of stack
	 */
	E peek();

	/**
	 * Returns top element and removes. Throws exception if stack is empty.
	 * 
	 * @return top element
	 */
	E pop();

	/**
	 * Insert e at top.
	 * 
	 * Similar to {@code add(E e)} from collection but specifies behavior.
	 * 
	 * @param e
	 *            element inserted
	 */
	void push(E e);

}

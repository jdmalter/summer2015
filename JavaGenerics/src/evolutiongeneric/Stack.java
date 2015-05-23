package evolutiongeneric;

/**
 * Legacy library stack.
 * 
 * @author Jacob Malter
 *
 */
public interface Stack<E> {

	/**
	 * Returns true if empty, false otherwise.
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty();

	/**
	 * Adds element onto top of stack.
	 * 
	 * @param elt
	 *            element added onto top of stack.
	 * @throws NullPointerException
	 *             null passed as parameter
	 */
	public void push(E elt);

	/**
	 * Removes element from top of stack.
	 * 
	 * @return element removed from top of stack.
	 */
	public E pop();

	/**
	 * Returns element from top of stack without removing.
	 * 
	 * @return element from top of stack.
	 */
	public E peek();

	/**
	 * Returns the size.
	 * 
	 * @return the size
	 */
	public int size();

}

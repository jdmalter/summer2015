/**
 * Illustrates concepts from item 6/11: eliminate obsolete object 
 * references/override clone judiciously.
 */
package memoryleakingstack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Non-generic stack with a fixed memory leak problem.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Stack {

	private Object elements[];
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * Creates a blank stack object.
	 */
	public Stack() {
		elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * Adds element to top of stack.
	 * 
	 * @param e
	 *            element being added
	 */
	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	/**
	 * Removes top element from stack.
	 * 
	 * @return top element being removed
	 */
	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}

	/**
	 * Ensure space for at least one more element, roughly doubling the capacity
	 * each time the array needs to grow.
	 */
	private void ensureCapacity() {
		if (elements.length == size)
			elements = Arrays.copyOf(elements, 2 * size + 1);
	}

	/**
	 * Creates a clone with super class clone method.
	 * 
	 * @return Stack clone of object
	 */
	public Stack clone() {
		try {
			Stack result = (Stack) super.clone();
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); // Can't happen
		}
	}

}

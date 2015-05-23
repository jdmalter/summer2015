/**
 * Illustrates concepts from item 6/11/26/28: eliminate obsolete object 
 * references/override clone judiciously/favor generic types/use bounded 
 * wildcards to increase API flexibility.
 */
package memoryleakingstack;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * Non-generic stack with a fixed memory leak problem. Formerly an object based
 * collection subjected to generification.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 * @param <E>
 *            type of object stored in stack
 */
public class Stack<E> {

	private E elements[];
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	/**
	 * Creates a blank stack object.
	 */
	@SuppressWarnings("unchecked")
	public Stack() {
		// This implementation's elements array will only contain E instance
		// from push(E). This is sufficient to ensure type safety, but the
		// runtime type of the array won't be E[]; it will always be Object[]!
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * Adds element to top of stack.
	 * 
	 * @param e
	 *            element being added
	 */
	public void push(E e) {
		ensureCapacity();
		elements[size++] = e;
	}

	/**
	 * Adds every element in the parameter iterable into the invoking stack
	 * instance.
	 * 
	 * @param src
	 *            iterable producing elements
	 */
	public void pushAll(Iterable<? extends E> src) {
		for (E e : src) {
			push(e);
		}
	}

	/**
	 * Removes top element from stack.
	 * 
	 * @return top element being removed
	 */
	public E pop() {
		if (size == 0)
			throw new EmptyStackException();
		E result = elements[--size];
		elements[size] = null; // Eliminate obsolete reference
		return result;
	}

	/**
	 * Returns top element from stack without removing it.
	 * 
	 * @return top element being peeked
	 */
	public E peek() {
		if (size == 0)
			throw new EmptyStackException();
		return elements[size - 1];
	}

	/**
	 * Removes every element in the invoking staack instance and places into the
	 * parameter collection.
	 * 
	 * @param dst
	 *            collection consuming elements
	 */
	public void popAll(Collection<? super E> dst) {
		while (!isEmpty())
			dst.add(pop());
	}

	/**
	 * Tests whether or not a stack is empty.
	 * 
	 * @return true if stack contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
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
	public Stack<E> clone() {
		try {
			// Type cast on object returned from object's clone method. Should
			// safely return type object to be cast into a Stack<E>.
			@SuppressWarnings("unchecked")
			Stack<E> result = (Stack<E>) super.clone();
			result.elements = elements.clone();
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); // Can't happen
		}
	}

}

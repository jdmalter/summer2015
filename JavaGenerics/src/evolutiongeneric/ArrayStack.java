package evolutiongeneric;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Legacy library stack implementation.
 * 
 * @author Jacob Malter
 * 
 * @param <E>
 *            object type of element data
 *
 */
public class ArrayStack<E> implements Stack<E> {
	private E[] elementData;
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 16;

	/**
	 * Constructs an empty stack.
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		// This implementation's elements array will only contain E instance
		// from push(E). This is sufficient to ensure type safety, but the
		// runtime type of the array won't be E[]; it will always be Object[]!
		elementData = (E[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Constructs an empty stack with size.
	 * 
	 * @param initialCapacity
	 *            initial size allocated for object elements
	 * @throws IllegalArgumentException
	 *             if {@code initialCapacity < 0} returns true
	 */
	@SuppressWarnings("unchecked")
	public ArrayStack(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException(initialCapacity
					+ " is less than 0.");

		// This implementation's elements array will only contain E instance
		// from push(E). This is sufficient to ensure type safety, but the
		// runtime type of the array won't be E[]; it will always be Object[]!
		elementData = (E[]) new Object[initialCapacity];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void push(E elt) {
		if (elt == null)
			throw new NullPointerException("Parameter is null.");

		ensureCapacity();
		elementData[size++] = elt;
	}

	@Override
	public E pop() {
		if (size == 0)
			throw new NoSuchElementException();

		E elt = elementData[--size];
		elementData[size] = null;
		return elt;
	}

	@Override
	public E peek() {
		if (isEmpty())
			throw new NoSuchElementException();

		return elementData[size - 1];
	}

	@Override
	public int size() {
		return size;
	}

	private void ensureCapacity() {
		if (elementData.length == size)
			elementData = Arrays.copyOf(elementData, 2 * size + 1);
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();

		s.append("stack[");
		for (E elt : elementData)
			if (elt != null)
				s.append(" " + elt);
		s.append(" ]");

		return s.toString();
	}

}

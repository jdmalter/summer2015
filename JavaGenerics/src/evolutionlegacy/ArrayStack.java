package evolutionlegacy;

import java.util.Arrays;

/**
 * Legacy library stack implementation.
 * 
 * @author Jacob Malter
 *
 */
public class ArrayStack implements Stack {
	private Object[] elementData;
	private int size;

	private static final int DEFAULT_CAPACITY = 16;
	private static final Object[] EMPTY_DATA = {};

	/**
	 * Constructs an empty stack.
	 */
	public ArrayStack() {
		elementData = EMPTY_DATA;
	}

	/**
	 * Constructs an empty stack with size.
	 * 
	 * @param initialCapacity
	 *            initial size allocated for object elements
	 * @throws IllegalArgumentException
	 *             if {@code initialCapacity < 0} returns true
	 */
	public ArrayStack(int initialCapacity) {
		if (initialCapacity < 0)
			throw new IllegalArgumentException(initialCapacity
					+ " is less than 0.");

		elementData = new Object[initialCapacity];
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public void push(Object elt) {
		if (elt == null)
			throw new NullPointerException("Parameter is null.");

		ensureCapacity(size + 1);
		elementData[size] = elt;
		size++;
	}

	@Override
	public Object pop() {
		Object elt = elementData[--size];
		elementData[size] = null;
		return elt;
	}

	@Override
	public Object peek() {
		return elementData[size - 1];
	}

	@Override
	public int size() {
		return size;
	}

	private void ensureCapacity(int capacity) {
		if (elementData == EMPTY_DATA)
			capacity = Math.max(DEFAULT_CAPACITY, capacity);

		if (capacity - elementData.length > 0)
			grow(capacity);
	}

	private void grow(int capacity) {
		int oldSize = elementData.length;
		int newSize = oldSize * 2;

		if (newSize - capacity < 0)
			newSize = capacity;
		elementData = Arrays.copyOf(elementData, newSize);
	}

	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();

		s.append("stack[");
		for (Object o : elementData)
			if (o != null)
				s.append(" " + o);
		s.append(" ]");

		return s.toString();
	}

}

package reification;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/**
 * A generic list implemented on top of an array.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            object type element data
 */
public class ArrayList<E> extends AbstractList<E> implements RandomAccess {
	private E[] arr;
	private int size = 0;
	private static final int RESCALE_FACTOR = 2;
	private static final int DEFAULT_CAPACITY = 16;

	/**
	 * Creates a new ArrayList with the default capacity.
	 * 
	 * This implementation uses an array of default capacity of 16.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		arr = (E[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Creates a new ArrayList with the parameter capacity.
	 * 
	 * This implementation uses an array with the parameter capacity.
	 * 
	 * @param cap
	 *            size capacity of array
	 * @throws IllegalArgumentException
	 *             if {@code cap < 0}
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int cap) {
		if (cap < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + cap);
		arr = (E[]) new Object[cap];
	}

	/**
	 * Creates a new ArrayList with the element belonging to the parameter
	 * collection.
	 * 
	 * This implementation creates an array with the size of the parameter
	 * collection.
	 * 
	 * @param coll
	 *            producer of elements
	 */
	public ArrayList(Collection<? extends E> coll) {
		this(coll.size());
		addAll(coll);
	}

	@Override
	public void add(int index, E elt) {
		checkBounds(index, size + 1);
		ensureCapacity(size + 1);

		// Shift elements to right
		for (int i = size; i > index;)
			arr[i] = arr[--i];
		arr[index] = elt;
		size++;
	}

	@Override
	public E get(int index) {
		checkBounds(index, size);
		return arr[index];
	}

	@Override
	public E remove(int index) {
		checkBounds(index, size);
		E old = arr[index];

		// Shift elements to left
		for (int i = index; i < size - 1;)
			arr[i] = arr[++i];
		arr[size--] = null;
		return old;
	}

	@Override
	public E set(int index, E elt) {
		checkBounds(index, size);

		E old = arr[index];
		arr[index] = elt;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		return toArray(new Object[0]);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		if (a.length < size)
			return (T[]) Arrays.copyOf(arr, size, a.getClass());

		// Good for a.length == size
		System.arraycopy(arr, 0, a, 0, size);

		if (a.length > size)
			a[size] = null;
		return a;
	}

	/**
	 * Checks for valid index.
	 * 
	 * @param index
	 *            index of interest
	 * @param size
	 *            number of elements added
	 * @throws IndexOutOfBoundsException
	 *             if {@code i < 0 || i >= size}
	 */
	private void checkBounds(int index, int size) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Extends array capacity to accept additional elements.
	 * 
	 * @param mincap
	 *            old size capacity
	 */
	// Type cast of (E[]) is safe since objects added into this array are
	// guaranteed to be of type E from the original array.
	@SuppressWarnings("unchecked")
	public void ensureCapacity(int mincap) {
		int oldcap = arr.length;

		if (mincap > oldcap) {
			E[] oldarr = arr;
			arr = (E[]) new Object[Math
					.max(mincap, oldcap * RESCALE_FACTOR + 1)];

			// Add elements into new array
			for (int i = 0; i < size; i++)
				arr[i] = oldarr[i];
			for (int j = size; j < arr.length; j++)
				arr[j] = null;
		}
	}

}

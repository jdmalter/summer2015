package collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Container satisfying list and dequeue interfaces on top of a normal array.
 * 
 * @author Jacob Malter
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class ArrayList<E> extends AbstractDequeList<E> {

	/**
	 * List iterator.
	 * 
	 * @author Jacob Malter
	 */
	private class LstIterator implements ListIterator<E> {

		/** Reference to elements in collection */
		private E[] elements;
		/** Reference to next element, reference to last element returned */
		private int pointer, lastReturned;

		/**
		 * Constructs a new LstIterator.
		 */
		private LstIterator() {
			this(0, true);
		}

		/**
		 * Constructs a new LstIterator given direction.
		 * 
		 * @param forward
		 *            true if iterator elements from 0 to size(), otherwise
		 *            iterator elements reversed
		 */
		private LstIterator(boolean forward) {
			this(0, forward);
		}

		/**
		 * Constructs a new LstIterator given index.
		 * 
		 * @param index
		 *            starting position within list
		 */
		private LstIterator(int index) {
			this(index, true);
		}

		/**
		 * Constructs a new LstIterator given index and direction.
		 * 
		 * @param index
		 *            starting position within list
		 * @param forward
		 *            true if iterator elements from 0 to size(), otherwise
		 *            iterator elements reversed
		 */
		private LstIterator(int index, boolean forward) {
			try {
				rangeCheck(index);
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Iterator index invalid.");
			}

			this.lastReturned = -1;
			this.pointer = index;
			this.elements = (E[]) Arrays.copyOf(data, data.length);
			if (!forward)
				collection.Arrays.reverse(elements);
			// suppressed warning safe since data is type E
		}

		@Override
		public void add(E e) {
			if (lastReturned < 0)
				throw new IllegalStateException("next nor previous called");

			elements = Arrays.copyOf(elements, elements.length + 1);
			for (int i = elements.length; i >= pointer;)
				elements[i] = elements[--i];
			elements[pointer++ - 1] = e;
			lastReturned = -1;
		}

		@Override
		public boolean hasNext() {
			return pointer < data.length;
		}

		@Override
		public boolean hasPrevious() {
			return 0 < pointer;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException(
						"No more elements remaining in iterator");

			lastReturned = pointer;
			return elements[pointer++];
		}

		@Override
		public int nextIndex() {
			return pointer;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException(
						"No more elements remaining in iterator");

			lastReturned = --pointer;
			return elements[pointer];
		}

		@Override
		public int previousIndex() {
			return pointer - 1;
		}

		@Override
		public void remove() {
			if (lastReturned < 0)
				throw new IllegalStateException("next nor previous called");

			System.arraycopy(elements, lastReturned + 1, elements,
					lastReturned, elements.length - lastReturned + 1);
			pointer--;
			lastReturned = -1;
		}

		@Override
		public void set(E e) {
			if (lastReturned < 0)
				throw new IllegalStateException("next nor previous called");

			elements[lastReturned] = e;
			lastReturned = -1;
		}

	}

	/** memory for data */
	private E[] data;

	/**
	 * number of elements, index of last element
	 */
	private int size;

	/**
	 * Constructs a circular array with default capacity.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		data = (E[]) collection.Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
	}

	@Override
	public void add(int index, E obj) {
		rangeCheck(index);
		ensureCapacity(size() + 1);

		rotateRightAfterIndex(index);
		data[index] = obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		data = (E[]) collection.Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
		size = 0;
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new LstIterator(false);
	}

	/**
	 * Creates room for new elements.
	 */
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = data.length;
		if (minCapacity >= oldCapacity) {
			int newCapacity = oldCapacity * 2 + 1;
			data = Arrays.copyOf(data, newCapacity);
		}
	}

	@Override
	public E get(int index) {
		rangeCheck(index);
		return data[index];
	}

	@Override
	public int indexOf(Object obj) {
		for (int i = 0; i < data.length; i++)
			if (data[i] == null ? obj == null : data[i].equals(obj))
				return i;
		return -1;
	}

	@Override
	public Iterator<E> iterator() {
		return new LstIterator();
	}

	@Override
	public int lastIndexOf(Object obj) {
		for (int i = data.length - 1; i > -1; i--)
			if (data[i] == null ? obj == null : data[i].equals(obj))
				return i;
		return -1;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new LstIterator(index);
	}

	/**
	 * Validates index.
	 * 
	 * @param index
	 *            position on list
	 * @throws ArrayIndexOutOfBoundsException
	 *             if index is less than 0 or greater than capacity
	 */
	private void rangeCheck(int index) {
		if (0 > index || index >= data.length)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		E result = data[index];
		rotateLeftAfterIndex(index);
		return result;
	}

	/**
	 * Shifts a range left by one after the given index. Moves the tail of the
	 * range lower in index. Replaces item at given index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateLeftAfterIndex(int fromIndex) {
		rangeCheck(fromIndex);

		for (int i = fromIndex; i < size; i++)
			data[i] = data[i + 1];
		data[size--] = null;
	}

	/**
	 * Shifts a range right by one after the given index. Moves the tail of the
	 * range higher in index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateRightAfterIndex(int fromIndex) {
		rangeCheck(fromIndex);
		ensureCapacity(size() + 1);

		for (int i = size++; i > fromIndex; i--)
			data[i] = data[i - 1];
	}

	@Override
	public E set(int index, E obj) {
		rangeCheck(index);

		E result = data[index];
		data[index] = obj;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

}

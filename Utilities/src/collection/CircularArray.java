package collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Container satisfying list and dequeue interfaces on top of a circular array.
 * 
 * @author Jacob Malter
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class CircularArray<E> extends AbstractDequeList<E> implements List<E>,
		Deque<E> {

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
	 * index of first element, number of elements, index of last element
	 */
	private int head, size, tail;

	/**
	 * Constructs a circular array with default capacity.
	 */
	@SuppressWarnings("unchecked")
	public CircularArray() {
		data = (E[]) collection.Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
	}

	@Override
	public void add(int index, E obj) {
		rangeCheck(index);
		ensureCapacity(size() + 1);

		int newIndex = translate(index);
		if (newIndex < tail)
			rotateRangeAfterIndex(newIndex);
		set(newIndex, obj);
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		data = (E[]) collection.Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
		head = 0;
		size = 0;
		tail = 0;
	}

	/**
	 * This implementation ignores changes to underlying CircularArray.
	 */
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
		return data[translate(index)];
	}

	@Override
	public int indexOf(Object obj) {
		for (int i = head; i < tail; i++)
			if (data[i] == null ? obj == null : data[i].equals(obj))
				return i;
		return -1;
	}

	/**
	 * This implementation ignores changes to underlying CircularArray.
	 */
	@Override
	public Iterator<E> iterator() {
		return new LstIterator();
	}

	@Override
	public int lastIndexOf(Object obj) {
		for (int i = tail; i < head; i--)
			if (data[i] == null ? obj == null : data[i].equals(obj))
				return i;
		return -1;
	}

	/**
	 * This implementation ignores changes to underlying CircularArray.
	 */
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
		if (0 > index || index > data.length)
			throw new ArrayIndexOutOfBoundsException();
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		int newIndex = translate(index);
		E result = data[newIndex];
		data[newIndex] = null;
		rotateRangeBeforeIndex(newIndex);
		size--;
		return result;
	}

	/**
	 * Shifts a range clockwise by one after but not including given index.
	 * Moves the tail of the range higher in index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateRangeAfterIndex(int fromIndex) {
		rangeCheck(fromIndex);
		ensureCapacity(size() + 1);

		if (tail >= fromIndex && tail != data.length - 1) {
			for (int i = tail; i >= fromIndex; i--)
				set(i + 1, get(i));
		} else if (tail >= fromIndex && tail == data.length - 1) {
			set(0, get(data.length - 1));
			for (int i = data.length - 2; i >= fromIndex; i--)
				set(i + 1, get(i));
		} else if (tail < fromIndex) {
			for (int i = tail; i > 0; i--)
				set(i + 1, get(i));
			set(0, get(data.length - 1));
			for (int i = data.length - 2; i > fromIndex; i--)
				set(i + 1, get(i));
		}
		tail++;
	}

	/**
	 * Shifts a range clockwise by one before but not including given index.
	 * Moves the head of the range higher in index. Replaces item at given
	 * index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateRangeBeforeIndex(int fromIndex) {
		rangeCheck(fromIndex);

		if (head < fromIndex) {
			for (int i = fromIndex; i > head; i--)
				set(i, get(i - 1));
		} else if (head >= fromIndex) {
			for (int i = fromIndex; i > 0; i--)
				set(i, get(i - 1));
			set(0, get(data.length - 1));
			for (int i = data.length - 2; i >= fromIndex; i--)
				set(i, get(i - 1));
		}
		head++;
	}

	@Override
	public E set(int index, E obj) {
		rangeCheck(index);

		int newIndex = translate(index);
		E result = data[newIndex];
		data[newIndex] = obj;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		// copyOf prevents implicitly sending a reference to a mutable field
		// outside of this class
		return Arrays.copyOf(data, size);
	}

	/**
	 * Takes a regular index and spits out a circular index.
	 * 
	 * Note: Call this method as late as possible.
	 * 
	 * @param index
	 *            linear index
	 * @return circular index
	 */
	private int translate(int index) {
		return (head + index) % data.length;
	}

}

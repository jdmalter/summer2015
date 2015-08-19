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
public class CircularArrayList<E> extends AbstractDequeList<E> {

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
	public CircularArrayList() {
		data = (E[]) collection.Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
	}

	@Override
	public void add(int index, E obj) {
		rangeCheck(index);
		ensureCapacity(size() + 1);

		if (index == 0) {
			// First element
			head = head <= 0 ? data.length - 1 : head - 1;
			data[head] = obj;
			size++;
		} else if (index >= size()) {
			// Last element
			data[tail] = obj;
			tail = tail >= data.length - 1 ? 0 : tail + 1;
			size++;
		} else {
			// Add in between elements
			int newIndex = translate(index);
			if (newIndex > (head + tail) / 2)
				rotateRightAfterIndex(newIndex);
			else
				rotateLeftBeforeIndex(newIndex);
			data[newIndex] = obj;
		}
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
		return data[index];
	}

	@Override
	public int indexOf(Object obj) {
		if (head < tail) {
			for (int i = head; i < tail; i++)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
		} else if (head > tail) {
			for (int i = head; i < data.length; i++)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
			for (int i = 0; i < tail; i++)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
		}
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
		if (tail > head) {
			for (int i = tail; i > head - 1; i--)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
		} else if (tail < head) {
			for (int i = tail; i > -1; i--)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
			for (int i = data.length - 1; i > head - 1; i--)
				if (data[i] == null ? obj == null : data[i].equals(obj))
					return i;
		}
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
		if (0 > index || index >= data.length)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		int newIndex = translate(index);
		E result = data[newIndex];
		if (index == 0) {
			// First element
			data[head] = null;
			head = head >= data.length - 1 ? 0 : head + 1;
			size--;
		} else if (index >= size()) {
			// Last element
			data[tail - 1] = null;
			tail = tail <= 0 ? data.length - 1 : tail - 1;
			size--;
		} else {
			if (newIndex > (head + tail) / 2)
				rotateLeftAfterIndex(newIndex);
			else
				rotateRightBeforeIndex(newIndex);
		}
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

		for (int i = fromIndex; i < tail; i++)
			data[i] = data[i + 1];
		data[tail] = null;

		tail--;
		size--;
	}

	/**
	 * Shifts a range left by one before the given index. Moves the head of the
	 * range lower in index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateLeftBeforeIndex(int fromIndex) {
		rangeCheck(fromIndex);
		ensureCapacity(size() + 1);

		for (int i = head; i < fromIndex; i++)
			data[i] = data[i + 1];

		head--;
		size++;
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

		for (int i = tail++; i > fromIndex; i--)
			data[i] = data[i - 1];

		tail++;
		size++;
	}

	/**
	 * Shifts a range right by one before the given index. Moves the head of the
	 * range higher in index. Replaces item at given index.
	 * 
	 * Precondition: Index already translated.
	 * 
	 * @param fromIndex
	 *            starting position
	 */
	private void rotateRightBeforeIndex(int fromIndex) {
		rangeCheck(fromIndex);

		for (int i = fromIndex; i > head - 1; i--)
			data[i] = data[i - 1];
		data[head] = null;

		head++;
		size--;
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

	/**
	 * Translate user index into index useful for circular array.
	 * 
	 * @param index
	 *            position in zero-based list
	 * @return position in circular array
	 */
	private int translate(int index) {
		return (index + head) % data.length;
	}

}

package collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A doubly linked container with indices. Operations optimized near the start
 * and end of the list.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class LinkedList<E> extends AbstractDequeList<E> implements List<E>,
		Deque<E> {

	/**
	 * List iterator.
	 * 
	 * @author Jacob Malter
	 */
	protected class LstIterator implements ListIterator<E> {

		/** Reference to current node, reference to last returned node */
		protected Node<E> pointer, lastReturned;
		/** Location of pointer in list */
		protected int pointerIndex;

		/**
		 * Constructs a new LstIterator.
		 */
		protected LstIterator() {
			this(0);
		}

		/**
		 * Constructs a new LstIterator given index.
		 * 
		 * @param index
		 *            starting position within list
		 */
		protected LstIterator(int index) {
			try {
				rangeCheck(index);
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("Iterator index invalid.");
			}

			pointer = head;
			for (int i = 0; i < index; i++) {
				next();
				lastReturned = null;
			}
		}

		@Override
		public void add(E e) {
			if (lastReturned == null)
				throw new IllegalStateException("next nor previous called");

			Node<E> insert = new Node<E>(e, lastReturned.next, lastReturned);
			lastReturned.next = insert;
			pointerIndex++;
			lastReturned = null;
		}

		@Override
		public boolean hasNext() {
			return pointer != null;
		}

		@Override
		public boolean hasPrevious() {
			return pointer.prev != null;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException(
						"No more elements remaining in iterator");

			E result = pointer.data;
			lastReturned = pointer;
			pointer = pointer.next;
			pointerIndex++;
			return result;
		}

		@Override
		public int nextIndex() {
			return pointerIndex;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException(
						"No more elements remaining in iterator");

			lastReturned = pointer;
			pointer = pointer.prev;
			pointerIndex--;
			return pointer.data;
		}

		@Override
		public int previousIndex() {
			return pointerIndex - 1;
		}

		@Override
		public void remove() {
			if (lastReturned == null)
				throw new IllegalStateException("next nor previous called");

			lastReturned.prev.next = lastReturned.next;
			pointerIndex--;
			lastReturned = null;
		}

		@Override
		public void set(E e) {
			if (lastReturned == null)
				throw new IllegalStateException("next nor previous called");

			lastReturned.data = e;
			lastReturned = null;
		}

	}

	/**
	 * One node containing data and links.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            the type of elements stored in this collection.
	 */
	private static class Node<E> {

		/** Memory holding */
		private E data;
		/** Reference link to next/previous node */
		private Node<E> next, prev;

		/**
		 * Constructs a node given data and adjacent nodes.
		 * 
		 * @param data
		 *            memory
		 * @param next
		 *            node in front
		 * @param prev
		 *            node behind
		 */
		private Node(E data, Node<E> next, Node<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

	}

	/** First/Last node */
	private Node<E> head, tail;
	/** Number of elements held */
	private int size;

	/**
	 * Constructs a LinkedList object.
	 */
	public LinkedList() {
		// Does nothing.
	}

	@Override
	public void add(int index, E obj) {
		rangeCheck(index);

		if (index == 0)
			linkHead(obj);
		else if (index == size)
			linkTail(obj);
		else {
			Node<E> current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			current.prev.next = new Node<E>(obj, current, current.prev);
			size++;
		}
	}

	/**
	 * Removes first node
	 * 
	 * @return data from delinked node
	 */
	private E delinkHead() {
		if (head == null)
			return null;
		E result = head.data;
		Node<E> first = head.next;

		head = null; // clean it up
		head = first;
		if (head != null)
			head.prev = null;

		size--;
		return result;
	}

	/**
	 * Removes last node
	 * 
	 * @return data from delinked node
	 */
	private E delinkTail() {
		if (tail == null)
			return null;
		E result = tail.data;
		Node<E> last = tail.prev;

		tail = null; // clean it up
		tail = last;
		if (tail != null)
			tail.next = null;

		size--;
		return result;
	}

	@Override
	public Iterator<E> descendingIterator() {
		return new LstIterator(size()) {

			@Override
			public boolean hasNext() {
				return pointer != null;
			}

			@Override
			public E next() {
				if (!hasNext())
					throw new NoSuchElementException(
							"No more elements remaining in iterator");

				E result = pointer.data;
				pointer = pointer.prev;
				return result;
			}

		};

	}

	@Override
	public E get(int index) {
		rangeCheck(index);

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		E result = current.data;
		return result;
	}

	@Override
	public int indexOf(Object obj) {
		int result = -1;

		Node<E> current = head;
		int index = 0;
		while (current != null) {
			if (current.data == null ? obj == null : current.data.equals(obj))
				result = index;
			current = current.next;
			index++;
		}

		return result;
	}

	@Override
	public Iterator<E> iterator() {
		return new LstIterator();
	}

	@Override
	public int lastIndexOf(Object obj) {
		int result = -1;

		Node<E> current = tail;
		int index = size();
		while (current != null) {
			if (current.data == null ? obj == null : current.data.equals(obj))
				result = index;
			current = current.prev;
			index++;
		}

		return result;
	}

	/**
	 * Links node in front
	 * 
	 * @param e
	 *            data being added
	 */
	private void linkHead(E e) {
		Node<E> first = new Node<E>(e, head, null);
		if (head != null)
			head.prev = first;
		head = first;
		size++;
	}

	/**
	 * Links node in rear
	 * 
	 * @param e
	 *            data being added
	 */
	private void linkTail(E e) {
		Node<E> last = new Node<E>(e, null, tail);
		if (tail != null)
			tail.next = last;
		tail = last;
		size++;
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
	 * @throws IndexOutOfBoundsException
	 *             if index is less than 0 or greater than capacity
	 */
	private void rangeCheck(int index) {
		if (0 > index || index > size())
			throw new IndexOutOfBoundsException();
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		if (index == 0)
			return delinkHead();
		else if (index == size)
			return delinkTail();
		else {
			Node<E> current = head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}

			E result = current.data;
			current.prev.next = current.next;

			size--;
			return result;
		}
	}

	@Override
	public E set(int index, E obj) {
		rangeCheck(index);

		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}

		E result = current.data;
		current.data = obj;

		return result;
	}

	@Override
	public int size() {
		return size;
	}

}

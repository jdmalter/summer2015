package collection;

import java.util.Iterator;

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
		return new Iterator<E>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return null;
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
		return new Iterator<E>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return null;
			}

		};
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
		try {
			rangeCheck(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Iterator index invalid.");
		}

		return new ListIterator<E>(index) {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean hasPrevious() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public E previous() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void add(E e) {
				// TODO Auto-generated method stub

			}

			@Override
			public int nextIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public int previousIndex() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub

			}

			@Override
			public void set(E e) {
				// TODO Auto-generated method stub

			}

		};
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

	@Override
	public List<E> subList(int start, boolean startInclusive, int end,
			boolean endInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

}

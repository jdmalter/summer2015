package declarations;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A collection implemented using a singly linked list.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            object type contained in collection
 */
public class LinkedCollection<E> extends AbstractCollection<E> {

	/**
	 * Singly linked node in collection.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <T>
	 *            object type of element data
	 */
	private static class Node<T> {
		private T element;
		private Node<T> next = null;

		private Node(T elt) {
			element = elt;
		}
	}

	private Node<E> first = new Node<E>(null);
	private Node<E> last = first;

	private int size = 0;

	/**
	 * Constructs an empty linked collection.
	 */
	public LinkedCollection() {
	}

	/**
	 * Constructs a collection with every elements in parameter collection.
	 * 
	 * @param c
	 *            collection whose elements are added into invoking instance.
	 */
	public LinkedCollection(Collection<? extends E> c) {
		addAll(c);
	}

	/**
	 * Number of elements added to collection.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Adds element to end of the list.
	 */
	@Override
	public boolean add(E elt) {
		last.next = new Node<E>(elt);
		last = last.next;
		size++;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator<E>(first);
	}

	/**
	 * An iterator over this class's linked collection.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <T>
	 *            object type of element data
	 */
	private static class LinkedIterator<T> implements Iterator<T> {
		private Node<T> current;

		/**
		 * Constructs a LinkedIterator given a first node.
		 * 
		 * @param first
		 *            first node over which to iterate
		 */
		public LinkedIterator(Node<T> first) {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current.next != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				current = current.next;
				return current.element;
			} else
				throw new NoSuchElementException();
		}

	}

}

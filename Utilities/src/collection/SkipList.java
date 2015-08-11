package collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Skip list implementing using doubly-linked linked lists.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class SkipList<E extends Comparable<? super E>> extends
		AbstractDequeList<E> implements Navigable<E> {

	/**
	 * Individual linked list within skiplist.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            The type of the elements stored in this collection.
	 */
	private static class SkipLinkedList<E extends Comparable<? super E>>
			extends AbstractDequeList<E> implements Navigable<E> {

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
			/** Reference link to child/next/parent/previous node */
			private Node<E> child, next, parent, prev;

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

		/** Used to compare elements */
		private final Comparator<? super E> comparator;
		/** Number of elements */
		private int size;

		/**
		 * Constructs an empty skiplist.
		 */
		public SkipLinkedList() {
			this(null);
		}

		public SkipLinkedList(Comparator<? super E> comp) {
			comparator = comp;
		}

		@Override
		public Iterator<E> descendingIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean add(E obj) {
			// TODO
			return false;
		}

		@Override
		public void add(int index, E obj) {
			add(obj);
		}

		@Override
		public void clear() {
			size = 0;
		}

		@Override
		public E get(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int indexOf(Object obj) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int lastIndexOf(Object obj) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public ListIterator<E> listIterator(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E remove(int index) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E set(int index, E obj) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public E ceiling(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Comparator<? super E> comparator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E floor(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E higher(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E lower(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E maximum() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public E minimum() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	/** Used to compare elements */
	private final Comparator<? super E> comparator;
	/** List of lists */
	private List<SkipLinkedList<E>> lists;
	/** Number of elements */
	private int size;

	/**
	 * Constructs an empty skiplist.
	 */
	public SkipList() {
		this(null);
	}

	public SkipList(Comparator<? super E> comp) {
		comparator = comp;
	}

	@Override
	public boolean add(E obj) {
		// TODO
		return false;
	}

	@Override
	public void add(int index, E obj) {
		add(obj);
	}

	@Override
	public E ceiling(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E floor(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E higher(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int lastIndexOf(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E lower(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E maximum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E minimum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E set(int index, E obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return size;
	}

}

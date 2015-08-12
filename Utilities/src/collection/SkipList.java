package collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

/**
 * Skip list implementing using doubly-linked linked lists.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class SkipList<E extends Comparable<? super E>> extends
		AbstractCollection<E> implements Navigable<E> {

	/**
	 * Individual linked list within skiplist. ORDERED!
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            The type of the elements stored in this collection.
	 */
	private static class SkipLinkedList<E extends Comparable<? super E>>
			extends AbstractCollection<E> implements Navigable<E> {

		/**
		 * One node containing data and links.
		 * 
		 * @author Jacob Malter
		 *
		 * @param <E>
		 *            the type of elements stored in this collection.
		 */
		private static class Node<E> {

			private static final Comparable<?> LEAST = (Comparable<?>) new Object();
			private static final Comparable<?> GREATEST = (Comparable<?>) new Object();

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
		private Node<E> head, tail;

		/**
		 * Constructs an empty skiplist.
		 */
		public SkipLinkedList() {
			this(null);
		}

		/**
		 * Constructs an empty skiplist given a comparator.
		 * 
		 * @param comp
		 *            ordering being used
		 */
		public SkipLinkedList(Comparator<? super E> comp) {
			comparator = comp;
			constructHeadTail();
		}

		@Override
		public boolean add(E obj) {
			if (size() == 0 || compare(obj, head.next.data) < 0)
				return linkHead(obj);
			else if (compare(obj, tail.prev.data) > 0)
				return linkTail(obj);

			Node<E> current = head.next;
			while (current != null && compare(obj, current.data) < 0) {
				current = current.next;
			}
			Node<E> insert = new Node<E>(obj, current, current.next);
			if (current.next != null)
				current.next.prev = insert;
			current.next = insert;
			size++;
			return true;
		}

		@Override
		public E ceiling(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clear() {
			size = 0;
			constructHeadTail();
		}

		@Override
		public Comparator<? super E> comparator() {
			return comparator;
		}

		private int compare(E o1, E o2) {
			if ((o1 == Node.GREATEST && o2 != Node.GREATEST)
					|| (o2 == Node.LEAST && o1 != Node.LEAST))
				return Integer.MAX_VALUE;
			else if ((o2 == Node.GREATEST && o1 != Node.GREATEST)
					|| (o1 == Node.LEAST && o2 != Node.LEAST))
				return Integer.MIN_VALUE;
			return compare(comparator(), o1, o2);
		}

		@SuppressWarnings("unchecked")
		private void constructHeadTail() {
			// suppression safe since head and tail will not be modified
			head = (Node<E>) new Node<Object>(Node.LEAST, null, null);
			;
			tail = (Node<E>) new Node<Object>(Node.GREATEST, null, null);
			;
			head.next = tail;
			tail.prev = head;
		}

		@Override
		public boolean contains(Object obj) {
			Node<E> current = head.next;
			while (current != null && current.next != null) {
				if (current.data.equals(obj))
					return true;
				current = current.next;
			}
			return false;
		}

		/**
		 * Removes first node (not HEAD)
		 * 
		 * @return data from delinked node
		 */
		private E delinkHead() {
			E result = head.next.data;
			Node<E> first = head.next.next;

			head.next = null; // clean it up
			head = first;
			if (head.next != null)
				head.next.prev = null;

			size--;
			return result;
		}

		/**
		 * Removes last node (not TAIL)
		 * 
		 * @return data from delinked node
		 */
		private E delinkTail() {
			E result = tail.prev.data;
			Node<E> last = tail.prev.prev;

			tail.prev = null; // clean it up
			tail = last;
			if (tail.prev != null)
				tail.prev.next = null;

			size--;
			return result;
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
		public E higher(E e) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator<E> iterator() {
			// TODO Auto-generated method stub
			return null;
		}

		/**
		 * Links node in front (not HEAD)
		 * 
		 * @param e
		 *            data being added
		 * @return true if successful, false otherwise
		 */
		private boolean linkHead(E e) {
			Node<E> first = new Node<E>(e, head.next, head);
			if (head.next != null)
				head.next.prev = first;
			head.next = first;
			size++;
			return true;
		}

		/**
		 * Links node in rear (not TAIL)
		 * 
		 * @param e
		 *            data being added
		 * @return true if successful, false otherwise
		 */
		private boolean linkTail(E e) {
			Node<E> last = new Node<E>(e, tail, tail.prev);
			if (tail.prev != null)
				tail.prev.next = last;
			tail.prev = last;
			size++;
			return true;
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
		public boolean remove(Object obj) {
			if (size() > 0)
				if (head.next.equals(obj))
					return delinkHead() != null;
				else if (tail.prev.equals(obj))
					return delinkTail() != null;
				else {
					Node<E> current = head.next;
					while (current != null && current.next != null) {
						if (current.data.equals(obj)) {
							current.prev.next = current.next;
							current = null;
							size--;
							return true;
						}
						current = current.next;
					}
				}
			return false;
		}

		@Override
		public int size() {
			return size;
		}

	}

	/** Used to compare elements */
	private final Comparator<? super E> comparator;
	/** List of lists */
	private List<SkipLinkedList<E>> lists;
	/** Random for coin tosses */
	private Random random;
	/** Number of elements */
	private int size;

	/**
	 * Constructs an empty skiplist.
	 */
	public SkipList() {
		this(null);
	}

	/**
	 * Constructs an empty skiplist given a comparator.
	 * 
	 * @param comp
	 *            ordering being used
	 */
	public SkipList(Comparator<? super E> comp) {
		comparator = comp;
		random = new Random();
		lists = new LinkedList<SkipLinkedList<E>>();
		lists.add(lists.size(), new SkipLinkedList<E>());
	}

	@Override
	public boolean add(E obj) {
		SkipLinkedList.Node<E> lower = lowerNode(obj);
		SkipLinkedList.Node<E> insert = new SkipLinkedList.Node<E>(obj, lower,
				lower.next);
		lower.next.prev = insert;
		lower.next = insert;
		size++;
		promote(0, insert); // may or may not actually promote node
		return true;
	}

	@Override
	public E ceiling(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		size = 0;
		lists.clear();
		lists.add(lists.size(), new SkipLinkedList<E>());
	}

	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}

	private int compare(E o1, E o2) {
		if ((o1 == SkipLinkedList.Node.GREATEST && o2 != SkipLinkedList.Node.GREATEST)
				|| (o2 == SkipLinkedList.Node.LEAST && o1 != SkipLinkedList.Node.LEAST))
			return Integer.MAX_VALUE;
		else if ((o2 == SkipLinkedList.Node.GREATEST && o1 != SkipLinkedList.Node.GREATEST)
				|| (o1 == SkipLinkedList.Node.LEAST && o2 != SkipLinkedList.Node.LEAST))
			return Integer.MIN_VALUE;
		return compare(comparator(), o1, o2);
	}

	@Override
	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		return false;
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
	public E higher(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E lower(E e) {
		SkipLinkedList.Node<E> result = lowerNode(e);
		return result == null ? null : result.data;
	}

	private SkipLinkedList.Node<E> lowerNode(E e) {
		if (compare(e, lists.get(0).tail.prev.data) >= 0)
			return lists.get(0).tail;
		int level = lists.size() - 1;
		SkipLinkedList.Node<E> current = lists.get(level).head;
		while (current != null) {
			while (compare(e, current.data) < 0) {
				current = current.next;
			}
			current = current.prev;
			if (level > 0) {
				current = current.child;
				level--;
			} else
				return current;
		}
		return lists.get(0).tail;
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

	private void promote(int level, SkipLinkedList.Node<E> node) {
		if (random.nextBoolean()) {
			SkipLinkedList.Node<E> lower = node.prev;
			while (lower.prev != null && lower.prev.parent == null) {
				lower = lower.prev;
			}
			if (level + 2 > lists.size()) {
				lists.add(lists.size(), new SkipLinkedList<E>());
				lists.get(lists.size() - 1).head.child = lists
						.get(lists.size() - 2).head;
				lists.get(lists.size() - 2).head.parent = lists.get(lists
						.size() - 1).head;
				lists.get(lists.size() - 1).tail.child = lists
						.get(lists.size() - 2).tail;
				lists.get(lists.size() - 2).tail.parent = lists.get(lists
						.size() - 1).tail;
			}
			lower = lower.parent;
			SkipLinkedList.Node<E> promotee = new SkipLinkedList.Node<E>(
					node.data, lower.next, lower);
			promotee.child = node;
			node.parent = promotee;
			lower.next = promotee;
			promote(level + 1, promotee);
		}
	}

	@Override
	public boolean remove(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}

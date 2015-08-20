package collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
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
	private static class OrderedLinkedList<E extends Comparable<? super E>>
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

		/**
		 * Iterator for individual linked list.
		 * 
		 * @author Jacob Malter
		 *
		 * @param <E>
		 *            the type of elements stored in this collection.
		 */
		private class SkipIterator implements Iterator<E> {

			private OrderedLinkedList.Node<E> cursor;
			private boolean direction;

			private SkipIterator() {
				this(true);
			}

			private SkipIterator(boolean forward) {
				direction = forward;
				cursor = direction ? head.next : tail.prev;

			}

			@Override
			public boolean hasNext() {
				return direction ? cursor.next.next != null
						: cursor.prev.prev != null;
			}

			@Override
			public E next() {
				if (!hasNext())
					throw new NoSuchElementException();
				E result = cursor.data;
				cursor = direction ? cursor.next : cursor.prev;
				return result;
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
		public OrderedLinkedList() {
			this(null);
		}

		/**
		 * Constructs an empty skiplist given a comparator.
		 * 
		 * @param comp
		 *            ordering being used
		 */
		public OrderedLinkedList(Comparator<? super E> comp) {
			comparator = comp;
			constructHeadTail();
		}

		@Override
		public boolean add(E obj) {
			if (size() == 0 || compare(obj, head.next.data) < 0)
				return linkHead(obj);
			else if (compare(obj, tail.prev.data) > 0)
				return linkTail(obj);

			Node<E> current = head;
			while (current.next != null && compare(obj, current.next.data) < 0) {
				current = current.next;
			}
			// current never will be null since compare(obj, Node.GREATEST)
			// always returns Integer.MIN_VALUE and will break at the tail
			Node<E> insert = new Node<E>(obj, current, current.next);
			if (current.next != null)
				current.next.prev = insert;
			current.next = insert;
			size++;
			return true;
		}

		@Override
		public E ceiling(E e) {
			Node<E> result = null;
			if (size() > 0) {
				result = tail.prev;
				while (result != null && result.prev != null
						&& compare(e, result.data) >= 0) {
					result = result.prev;
				}
				result = result.next;
			}
			return result == null ? null : result.data;
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
			tail = (Node<E>) new Node<Object>(Node.GREATEST, null, null);
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
			return new SkipIterator(false);
		}

		@Override
		public E floor(E e) {
			Node<E> result = null;
			if (size() > 0) {
				result = head.next;
				while (result != null && result.next != null
						&& compare(e, result.data) <= 0) {
					result = result.next;
				}
				result = result.prev;
			}
			return result == null ? null : result.data;
		}

		@Override
		public E higher(E e) {
			Node<E> result = null;
			if (size() > 0) {
				result = tail.prev;
				while (result != null && result.prev != null
						&& compare(e, result.data) > 0) {
					result = result.prev;
				}
				result = result.next;
			}
			return result == null ? null : result.data;
		}

		@Override
		public Iterator<E> iterator() {
			return new SkipIterator();
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
			Node<E> result = null;
			if (size() > 0) {
				result = head.next;
				while (result != null && result.next != null
						&& compare(e, result.data) < 0) {
					result = result.next;
				}
				result = result.prev;
			}
			return result == null ? null : result.data;
		}

		@Override
		public E maximum() {
			return size() > 0 ? tail.prev.data : null;
		}

		@Override
		public E minimum() {
			return size() > 0 ? head.next.data : null;
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
					while (current.next != null) {
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
	private List<OrderedLinkedList<E>> lists;
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
		lists = new CircularArrayList<OrderedLinkedList<E>>();
		lists.add(lists.size(), new OrderedLinkedList<E>());
	}

	@Override
	public boolean add(E obj) {
		OrderedLinkedList.Node<E> lower = lowerNode(obj);
		OrderedLinkedList.Node<E> insert = new OrderedLinkedList.Node<E>(obj,
				lower, lower.next);
		lower.next.prev = insert;
		lower.next = insert;
		size++;
		promote(0, insert); // may or may not actually promote node
		return true;
	}

	@Override
	public E ceiling(E e) {
		OrderedLinkedList.Node<E> result = higherNode(e);
		while (result != null && result.prev != null
				&& compare(e, result.data) >= 0) {
			result = result.prev;
		}
		result = result.next;
		return result == null ? null : result.data;
	}

	@Override
	public void clear() {
		size = 0;
		lists.clear();
		lists.add(lists.size(), new OrderedLinkedList<E>());
	}

	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}

	private int compare(E o1, E o2) {
		if ((o1 == OrderedLinkedList.Node.GREATEST && o2 != OrderedLinkedList.Node.GREATEST)
				|| (o2 == OrderedLinkedList.Node.LEAST && o1 != OrderedLinkedList.Node.LEAST))
			return Integer.MAX_VALUE;
		else if ((o2 == OrderedLinkedList.Node.GREATEST && o1 != OrderedLinkedList.Node.GREATEST)
				|| (o1 == OrderedLinkedList.Node.LEAST && o2 != OrderedLinkedList.Node.LEAST))
			return Integer.MIN_VALUE;
		return compare(comparator(), o1, o2);
	}

	@Override
	public boolean contains(Object obj) {
		try {
			@SuppressWarnings("unchecked")
			E other = (E) obj;
			OrderedLinkedList.Node<E> current = lists.get(size() - 1).head.next;
			while (current != null) {
				while (current.next != null && compare(other, current.data) < 0) {
					current = current.next;
				}
				current = current.prev;
				if (current.child != null)
					current = current.child;
				else if (compare(other, current.data) == 0)
					return true;
			}
		} catch (ClassCastException e) {
			return false;
		}
		return false;
	}

	@Override
	public Iterator<E> descendingIterator() {
		return lists.get(0).descendingIterator();
	}

	@Override
	public E floor(E e) {
		OrderedLinkedList.Node<E> result = lowerNode(e);
		while (result != null && result.next != null
				&& compare(e, result.data) <= 0) {
			result = result.next;
		}
		result = result.prev;
		return result == null ? null : result.data;
	}

	@Override
	public E higher(E e) {
		OrderedLinkedList.Node<E> result = higherNode(e);
		return result == null ? null : result.data;
	}

	private OrderedLinkedList.Node<E> higherNode(E e) {
		if (compare(e, lists.get(0).head.next.data) <= 0)
			return lists.get(0).head;
		OrderedLinkedList.Node<E> current = lists.get(lists.size() - 1).tail;
		while (current != null) {
			while (current.prev != null && compare(e, current.data) > 0) {
				current = current.prev;
			}
			current = current.next;
			if (current.child != null)
				current = current.child;
			else
				return current;
		}
		return lists.get(0).tail;
	}

	@Override
	public Iterator<E> iterator() {
		return lists.get(0).iterator();
	}

	@Override
	public E lower(E e) {
		OrderedLinkedList.Node<E> result = lowerNode(e);
		return result == null ? null : result.data;
	}

	private OrderedLinkedList.Node<E> lowerNode(E e) {
		if (compare(e, lists.get(0).tail.prev.data) >= 0)
			return lists.get(0).tail;
		int level = lists.size() - 1;
		OrderedLinkedList.Node<E> current = lists.get(level).head;
		while (current != null) {
			while (current.next != null && compare(e, current.data) < 0) {
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
		return lists.get(0).maximum();
	}

	@Override
	public E minimum() {
		return lists.get(0).minimum();
	}

	private void promote(int level, OrderedLinkedList.Node<E> node) {
		if (random.nextBoolean()) {
			OrderedLinkedList.Node<E> lower = node.prev;
			while (lower.prev != null && lower.prev.parent == null) {
				lower = lower.prev;
			}
			if (level + 2 > lists.size()) {
				lists.add(lists.size(), new OrderedLinkedList<E>());
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
			OrderedLinkedList.Node<E> promotee = new OrderedLinkedList.Node<E>(
					node.data, lower.next, lower);
			promotee.child = node;
			node.parent = promotee;
			lower.next = promotee;
			promote(level + 1, promotee);
		}
	}

	private void demote(int level, OrderedLinkedList.Node<E> node) {
		if (level < lists.size() && node != null) {
			demote(level + 1, node.parent);
			node.prev.next = node.next;
			node = null;
		}
	}

	@Override
	public boolean remove(Object obj) {
		try {
			@SuppressWarnings("unchecked")
			E other = (E) obj;
			OrderedLinkedList.Node<E> current = lists.get(size() - 1).head.next;
			while (current != null) {
				while (current.next != null && compare(other, current.data) < 0) {
					current = current.next;
				}
				current = current.prev;
				if (current.child != null)
					current = current.child;
				else if (compare(other, current.data) == 0) {
					demote(0, current);
					size--;
					return true;
				}
			}
		} catch (ClassCastException e) {
			return false; // obj is not correct type
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}

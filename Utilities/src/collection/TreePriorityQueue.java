package collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Heap based priority queue implemented by tree.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class TreePriorityQueue<E extends Comparable<? super E>> extends
		AbstractQueue<E> implements Navigable<E> {

	/**
	 * Node with data and family.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            The type of the elements stored in this collection.
	 */
	private static class Node<E> {

		private E data;
		private Node<E> left, right, parent;

		private Node(E data, Node<E> left, Node<E> parent, Node<E> right) {
			this.data = data;
			this.left = left;
			this.parent = parent;
			this.right = right;
		}

	}

	/** Used to compare elements */
	private final Comparator<? super E> comparator;
	/** Reference to beginning of tree */
	private Node<E> root;
	/** Number of elements */
	private int size;

	/**
	 * Constructs an empty PriorityQueue using natural ordering comparator.
	 */
	public TreePriorityQueue() {
		this(null);
	}

	/**
	 * Constructs an empty PriorityQueue using given comparator.
	 * 
	 * @param comparator
	 *            ordering being used
	 */
	public TreePriorityQueue(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	@Override
	public boolean add(E obj) {
		if (obj == null)
			throw new IllegalArgumentException("Cannot add null object.");
		return priorityAdd(new Node<E>(obj, null, null, null));
	}

	/**
	 * Returns comparator used for ordering.
	 * 
	 * @return comparator used for ordering
	 */
	public Comparator<? super E> comparator() {
		return comparator;
	}

	@Override
	public E element() {
		if (isEmpty())
			throw new NoSuchElementException();
		return root.data;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean priorityAdd(Node<E> node) {
		// Root case
		if (root == null) {
			root = node;
			size++;
			return true;
		}

		// Find correct spot
		Node<E> parent = leftMost(root, 0);

		// Add node
		if (parent != null) {
			node.parent = parent;
			if (parent.left == null)
				parent.left = node;
			else if (parent.right == null)
				parent.right = node;
			else
				throw new AssertionError("priorityAdd: Normally unreachable.");
			moveUp(node);
			size++;
			return true;
		}

		// Failed
		return false;
	}

	private void priorityRemove() {
		// Root case
		if (root.left == null && root.right == null) {
			root = null;
			size--;
		}

		// Find replacement
		Node<E> replacement = rightMost(root, 0);

		// Replacement should not be root since root has children
		if (replacement != null) {
			root.data = replacement.data;
			if (replacement == replacement.parent.right)
				replacement.parent.right = null;
			else
				replacement.parent.left = null;
			replacement.parent = null;
			moveDown(root);
			size--;
		}
	}

	/**
	 * Swaps node's data with child who has largest data.
	 * 
	 * @param node
	 *            contains data
	 */
	private void moveDown(Node<E> node) {
		if (node != null && (node.left != null || node.right != null)) {
			E temp = node.data;
			if (node.left == null && node.data.compareTo(node.right.data) < 0) {
				node.data = node.right.data;
				node.right.data = temp;
				moveDown(node.right);
			} else if (node.right == null
					&& node.data.compareTo(node.left.data) < 0) {
				node.data = node.left.data;
				node.left.data = temp;
				moveDown(node.left);
			} else {
				if (node.left.data.compareTo(node.right.data) < 0
						&& node.data.compareTo(node.right.data) < 0) {
					node.data = node.right.data;
					node.right.data = temp;
					moveDown(node.right);
				} else if (node.data.compareTo(node.left.data) < 0) {
					node.data = node.left.data;
					node.left.data = temp;
					moveDown(node.left);
				}
			}
		}
	}

	/**
	 * Swaps node's data with parent's data if node's data is greater.
	 * 
	 * @param node
	 *            contains data
	 */
	private void moveUp(Node<E> node) {
		if (node != null && node.parent != null
				&& node.data.compareTo(node.parent.data) > 0) {
			E temp = node.data;
			node.data = node.parent.data;
			node.parent.data = temp;
			moveUp(node.parent);
		}
	}

	/**
	 * Finds parent of position on lowest level farthest to the right.
	 * 
	 * @param node
	 *            target of operation
	 * @param height
	 *            depth in tree
	 * @return parent on position
	 */
	private Node<E> rightMost(Node<E> node, int height) {
		// Null case
		if (node == null)
			return node;

		// Successful case
		int treeHeight = Arrays.decompse(size(), 2).length - 1;
		if (height == treeHeight && (node.left == null || node.right == null))
			return node;

		// Recursive case
		Node<E> result = null;
		// Check nodes above lowest level
		if (height < treeHeight) {
			Node<E> right = rightMost(node.right, height + 1);
			result = right == null ? rightMost(node.left, height + 1) : right;
		}

		if (height == 0 && result == null) {
			// Entire (treeHeight - 1) row is filled
			result = root;
			for (int i = 0; i < treeHeight; i++) {
				result = result.right;
			}
		}

		return result;
	}

	/**
	 * Finds parent of position on second lowest level farthest to the left.
	 * 
	 * @param node
	 *            target of operation
	 * @param height
	 *            depth in tree
	 * @return parent on position
	 */
	private Node<E> leftMost(Node<E> node, int height) {
		// Null case
		if (node == null)
			return node;

		// Successful case
		int treeHeight = Arrays.decompse(size(), 2).length - 1;
		if (height == treeHeight - 1
				&& (node.left == null || node.right == null))
			return node;

		// Recursive case
		Node<E> result = null;
		// Check nodes above lowest level
		if (height < treeHeight) {
			Node<E> left = leftMost(node.left, height + 1);
			result = left == null ? leftMost(node.right, height + 1) : left;
		}

		if (height == 0 && result == null) {
			// Entire (treeHeight - 1) row is filled
			result = root;
			for (int i = 0; i < treeHeight; i++) {
				result = result.left;
			}
		}

		return result;
	}

	@Override
	public E remove() {
		if (isEmpty())
			throw new NoSuchElementException();
		E result = root.data;
		priorityRemove();
		return result;
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
	public E lower(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E maximum() {
		return isEmpty() ? null : root.data;
	}

	@Override
	public E minimum() {
		return null;
	}

}

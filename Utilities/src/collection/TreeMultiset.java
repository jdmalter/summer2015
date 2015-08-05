package collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Ordered multiset backed by a red-black tree. Allows multiple, equal entries.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class TreeMultiset<E extends Comparable<? super E>> extends
		AbstractMultiset<E> implements Multiset<E> {

	/**
	 * Tree in order iterator.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private class TreeIterator implements Iterator<E> {

		Node<E> pointer;

		private TreeIterator() {
			pointer = getLeast();
		}

		/**
		 * Finds node with no left children and therefore lowest value.
		 * 
		 * @return node to complete left
		 */
		private Node<E> getLeast() {
			Node<E> current = root;

			if (current == null)
				return null;

			while (current.left != null)
				current = current.left;

			return current;
		}

		@Override
		public boolean hasNext() {
			return pointer != null;
		}

		@Override
		public E next() {
			E result = pointer.data;
			if (pointer.left != null)
				pointer = pointer.left;
			else if (pointer.parent != null)
				pointer = pointer.parent;
			else if (pointer.right != null)
				pointer = pointer.left;
			else
				pointer = null;
			return result;
		}

	}

	/**
	 * Node with children and data.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            The type of the elements stored in this collection.
	 */
	private static class Node<E> {

		private E data;
		private boolean isBlack;
		private Node<E> left, parent, right;

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
	 * Constructs an empty TreeMultiset using natural ordering comparator.
	 */
	public TreeMultiset() {
		this(null);
	}

	/**
	 * Constructs an empty TreeMultiset using given comparator.
	 * 
	 * @param comparator
	 *            ordering being used
	 */
	public TreeMultiset(Comparator<? super E> comparator) {
		this.comparator = comparator;
	}

	@Override
	public boolean add(E obj) {
		Node<E> insert = new Node<E>(obj, null, null, null);
		boolean flag = rbAdd(insert);
		recolorAdd(insert);
		return flag;
	}

	/**
	 * Mirrors blackRightSibling. Rotates sibling's children to maintain
	 * red-black properties.
	 * 
	 * PRECONDITION: sibling is non-null left child of parent
	 * 
	 * @param sibling
	 *            sibling of removed node
	 * @return true if operation run, false otherwise
	 */
	private boolean blackLeftSibling(Node<E> sibling) {
		if (sibling != null && sibling.isBlack) {
			if (sibling.left != null && !sibling.left.isBlack) {
				rotateRight(sibling.parent);
				return true;
			} else if (sibling.right == null || !sibling.right.isBlack) {
				rotateRight(sibling);
				rotateRight(sibling.parent.parent);
				return true;
			}
		}
		return false;
	}

	/**
	 * Mirrors blackLeftSibling. Rotates sibling's children to maintain
	 * red-black properties.
	 * 
	 * PRECONDITION: sibling is non-null right child of parent
	 * 
	 * @param sibling
	 *            sibling of removed node
	 * @return true if operation run, false otherwise
	 */
	private boolean blackRightSibling(Node<E> sibling) {
		if (sibling != null && sibling.isBlack) {
			if (sibling.right != null && !sibling.right.isBlack) {
				rotateLeft(sibling.parent);
				return true;
			} else if (sibling.left == null || !sibling.left.isBlack) {
				rotateLeft(sibling);
				rotateLeft(sibling.parent.parent);
				return true;
			}
		}
		return false;
	}

	/**
	 * Colors child's parent in black, and colors sibling in red.
	 * 
	 * PRECONDITION: sibling is non-null with two black children
	 * 
	 * @param sibling
	 *            sibling of removed node
	 * @return true if operation run, false otherwise
	 */
	private boolean blackSiblingBlackChildren(Node<E> sibling) {
		if (sibling != null && sibling.isBlack) {
			if ((sibling.left == null || sibling.left.isBlack)
					&& (sibling.right == null || sibling.right.isBlack)) {
				// Two black children
				boolean flag = sibling.isBlack;
				sibling.parent.isBlack = true;
				sibling.isBlack = false;
				if (flag)
					recolorRemove(sibling.parent, sibling.parent.parent);
				return true;
			}
		}
		return false;
	}

	/**
	 * Finds least node greater than target.
	 * 
	 * @param target
	 *            starting point
	 * @return least node
	 */
	private Node<E> ceiling(Node<E> target) {
		Node<E> ceilingCurrent = target.right;
		Node<E> ceilingPrev = target;
		do {
			ceilingPrev = ceilingCurrent;
			ceilingCurrent = ceilingCurrent.left;
		} while (ceilingCurrent != null);
		return ceilingPrev;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Returns comparator used for ordering.
	 * 
	 * @return comparator used for ordering
	 */
	public Comparator<? super E> comparator() {
		return comparator;
	}

	/**
	 * Compares two objects for ordering.
	 * 
	 * @param e1
	 *            first object
	 * @param e2
	 *            second object
	 * @return negative, zero, or positive integer if the first object is less
	 *         than, equal to, or greater than the second object
	 */
	private int compare(E e1, E e2) {
		return comparator == null ? ((e1 == null || e2 == null) ? (e1 == e2 ? 0
				: (e1 == null) ? -1 : 1) : e1.compareTo(e2)) : comparator
				.compare(e1, e2);
	}

	@Override
	public boolean contains(Object obj) {
		return getNode(obj) != null;
	}

	/**
	 * Finds greatest node less than target.
	 * 
	 * @param target
	 *            starting point
	 * @return greatest node
	 */
	private Node<E> floor(Node<E> target) {
		Node<E> floorCurrent = target.left;
		Node<E> floorPrev = target;
		do {
			floorPrev = floorCurrent;
			floorCurrent = floorCurrent.right;
		} while (floorCurrent != null);
		return floorPrev;
	}

	private Node<E> getNode(Object obj) {
		try {
			@SuppressWarnings("unchecked")
			E target = (E) obj;
			// suppressed warning safe within try/catch block

			// Root case
			if (root == null)
				return null;

			// Find correct spot
			Node<E> current = root;
			do {
				int comparison = compare(target, current.data);
				if (comparison < 0)
					current = current.left;
				else if (comparison > 0)
					current = current.right;
				else
					return current;
			} while (current != null);

		} catch (ClassCastException e) {
			return null;
		}

		// Tried and failed
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		return new TreeIterator();
	}

	private boolean rbAdd(Node<E> node) {

		// Root case
		if (root == null) {
			root = node;
			size++;
			return true;
		}

		// Find correct spot
		Node<E> current = root;
		Node<E> prev = root.parent;
		do {
			int comparison = compare(node.data, current.data);
			prev = current.parent;
			if (comparison < 0)
				current = current.left;
			else if (comparison > 0)
				current = current.right;
		} while (current != null);

		// Insert node
		node.parent = prev;
		int comparison = compare(node.data, prev.data);
		if (comparison < 0) {
			prev.left = node;
		} else if (comparison > 0) {
			prev.right = node;
		} else {
			if (prev.right == null)
				prev.right = node;
			else if (prev.left == null)
				prev.left = node;
			else
				throw new AssertionError("rbAdd: Logically unreachable.");
		}

		size++;
		return true;
	}

	private boolean rbRemove(Node<E> node) {
		if (node == null)
			return false;

		Node<E> current = node;

		if (current.left == null && current.right != null) {
			// Right child
			current.right.parent = current.parent;
			current = current.right;
			current.isBlack = true;
			size--;
			return true;

		} else if (current.left != null && current.right == null) {
			// Left child
			current.left.parent = current.parent;
			current = current.left;
			current.isBlack = true;
			size--;
			return true;

		} else if ((current.left != null || current.right == null)
				&& (current.left == null || current.right != null)) {
			// XOR logic
			// No children and both children

			Node<E> floor = floor(current);
			Node<E> ceiling = ceiling(current);

			// Find which node is closer to current
			Node<E> currentCandidate = compare(floor.data, current.data) < compare(
					current.data, ceiling.data) ? floor : ceiling;

			// Replace current node with candidate
			if (currentCandidate == current) {
				// Current has no children
				if (current.parent != null) {
					if (current == current.parent.left)
						current.parent.left = null;
					else if (current == current.parent.right)
						current.parent.right = null;
					else
						throw new AssertionError(
								"rbRemove(1): Logically unreachable.");
				}

				// Clip leaf
				recolorRemove(current, current.parent);
				current = null;
				size--;
				return true;

			} else if (currentCandidate == floor) {
				// Lesser node is closer
				current.data = floor.data;

				// Found more children?
				if (floor.left != null)
					floor.left.parent = floor.parent;

				// Link over floor
				floor.parent.right = floor.left;
				recolorRemove(floor, floor.parent);
				floor = null;
				size--;
				return true;

			} else if (currentCandidate == ceiling) {
				// Greater node is closer
				current.data = ceiling.data;

				// Found more children?
				if (ceiling.right != null)
					ceiling.right.parent = ceiling.parent;

				// Link over ceiling
				ceiling.parent.left = ceiling.right;
				recolorRemove(ceiling, ceiling.parent);
				ceiling = null;
				size--;
				return true;

			}
		}
		return false;
	}

	/**
	 * Reassigns colors to nodes in accordance with red-black tree principles.
	 * Intended to be called after rbAdd.
	 * 
	 * @param child
	 *            added node
	 */
	private void recolorAdd(Node<E> child) {
		while (child != root || !child.isBlack) {
			if (child.parent != null && child.parent.parent != null) {
				if (child.parent == child.parent.parent.left) {
					// Parent is left child
					if (!redUncle(child, child.parent.parent.right)) {
						if (child == child.parent.right)
							rotateLeft(child.parent);

						// child is right right grandchild
						child.parent.isBlack = true;
						child.parent.parent.isBlack = false;
						rotateRight(child.parent.parent);
					}

				} else if (child.parent == child.parent.parent.right) {
					// Parent is right child
					if (!redUncle(child, child.parent.parent.left)) {
						if (child == child.parent.left)
							rotateRight(child.parent);

						// child is left left grandchild
						child.parent.isBlack = true;
						child.parent.parent.isBlack = false;
						rotateLeft(child.parent.parent);
					}

				} else
					throw new AssertionError(
							"recolorAdd: Logically unreachable.");

			}
		}
		root.isBlack = true;
	}

	/**
	 * Reassigns colors to nodes in accordance with red-black tree principles.
	 * Intended to be called after rbRemove.
	 * 
	 * @param child
	 *            removed node
	 * @param parent
	 *            removed node parent
	 * @return true if operation was run, false otherwise
	 */
	private boolean recolorRemove(Node<E> child, Node<E> parent) {
		if (child == null || child.isBlack)
			while (child != root) {
				if (parent != null) {
					if (child == parent.left) {
						// child is left child
						if (redSibling(parent.right))
							rotateLeft(parent);

						// child and sibling are black
						if (!blackSiblingBlackChildren(parent.right)) {
							// sibling has red children
							blackRightSibling(parent.right);
						}
					} else if (child == parent.right) {
						// child is right child
						if (redSibling(parent.left))
							rotateRight(parent);

						// child and sibling are black
						if (!blackSiblingBlackChildren(parent.left)) {
							// sibling has red children
							blackLeftSibling(parent.left);
						}
					} else
						throw new AssertionError(
								"recolorRemove: Logically unreachable.");
				}
				return true;
			}
		return false;
	}

	/**
	 * Colors child's parent in red, and colors sibling in black.
	 * 
	 * @param sibling
	 *            sibling of removed node
	 * @return true if operation run, false otherwise
	 */
	private boolean redSibling(Node<E> sibling) {
		if (sibling != null && !sibling.isBlack) {
			sibling.parent.isBlack = false;
			sibling.isBlack = true;
			return true;
		}
		return false;
	}

	/**
	 * Colors child's parent and uncle in black, and colors child's grandparent
	 * in red. Recursively recolors grandparent.
	 * 
	 * PRECONDITION: if uncle is red, child is not null
	 * 
	 * @param child
	 *            newly added node
	 * @param uncle
	 *            uncle of newly added node
	 * @return true if operation run, false otherwise
	 */
	private boolean redUncle(Node<E> child, Node<E> uncle) {
		if (uncle != null && !uncle.isBlack) {
			child.parent.isBlack = true;
			child.parent.parent.isBlack = false;
			uncle.isBlack = true;
			recolorAdd(child.parent.parent);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		return rbRemove(getNode(obj));
	}

	/**
	 * Preserves in-order nodes while moving nodes.
	 * 
	 * @param vertex
	 *            Node around which rotation occurs
	 */
	private void rotateLeft(Node<E> vertex) {
		Node<E> temp = vertex.right;
		vertex.right = temp.left;

		if (temp.left != null)
			temp.left.parent = vertex;
		temp.parent = vertex.parent;

		if (vertex.parent == null)
			root = temp;
		else if (vertex == vertex.parent.left)
			vertex.parent.left = temp;
		else if (vertex == vertex.parent.right)
			vertex.parent.right = temp;
		else
			throw new AssertionError("rotateLeft: Logically unreachable.");

		temp.left = vertex;
		vertex.parent = temp;
	}

	/**
	 * Preserves in-order nodes while moving nodes.
	 * 
	 * @param vertex
	 *            Node around which rotation occurs
	 */
	private void rotateRight(Node<E> vertex) {
		Node<E> temp = vertex.left;
		vertex.left = temp.right;

		if (temp.right != null)
			temp.right.parent = vertex;
		temp.parent = vertex.parent;

		if (vertex.parent == null)
			root = temp;
		else if (vertex == vertex.parent.left)
			vertex.parent.left = temp;
		else if (vertex == vertex.parent.right)
			vertex.parent.right = temp;
		else
			throw new AssertionError("rotateRight: Logically unreachable.");

		temp.right = vertex;
		vertex.parent = temp;
	}

	@Override
	public int size() {
		return size;
	}

}

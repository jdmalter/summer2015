package visitor;

/**
 * A sample tree (with client) using visitors.
 * 
 * This implementation uses the visitor design pattern.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public abstract class Tree<E> {

	/**
	 * A visitor class that performs operations on subtrees.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            element type
	 * @param <R>
	 *            a subtree typed to Tree
	 */
	public interface Visitor<E, R> {
		/**
		 * Accepts a value of type E and returns a value of type R.
		 * 
		 * @param elt
		 *            object data contained in node
		 * @return node returned
		 */
		public R leaf(E elt);

		/**
		 * Accepts two values to type R and returns a value of type R.
		 * 
		 * @param left
		 *            left node
		 * @param right
		 *            right node
		 * @return branch with left and right nodes
		 */
		public R branch(R left, R right);
	}

	/**
	 * Defines a visitor method for all methods. Takes visitor and returns node.
	 * 
	 * @param v
	 *            visitor object used
	 * @param <R>
	 *            node type
	 * @return node
	 */
	public abstract <R> R visit(Visitor<E, R> v);

	/**
	 * Creates a new node leaf given an object.
	 * 
	 * @param e
	 *            object belonging to new node
	 * @param <T>
	 *            object type stored in tree
	 * @return new node
	 */
	public static <T> Tree<T> leaf(final T e) {
		return new Tree<T>() {
			public <R> R visit(Visitor<T, R> v) {
				return v.leaf(e);
			}
		};
	}

	/**
	 * Creates a new node branch given two sub-nodes.
	 * 
	 * @param l
	 *            left node
	 * @param r
	 *            right node
	 * @param <T>
	 *            object type stored in tree
	 * @return new branch
	 */
	public static <T> Tree<T> branch(final Tree<T> l, final Tree<T> r) {
		return new Tree<T>() {
			public <R> R visit(Visitor<T, R> v) {
				return v.branch(l.visit(v), r.visit(v));
			}
		};
	}

}

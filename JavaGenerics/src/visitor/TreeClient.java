package visitor;

/**
 * A sample client (with tree).
 * 
 * @author Jacob Malter
 *
 */
public class TreeClient {

	/**
	 * Creates a string representation of this tree.
	 * 
	 * Implements visitor from tree.
	 * 
	 * @param t
	 *            tree
	 * @param <T>
	 *            object type
	 * @return string representation
	 */
	public static <T> String toString(Tree<T> t) {
		return t.visit(new Tree.Visitor<T, String>() {

			@Override
			public String leaf(T elt) {
				return elt.toString();
			}

			@Override
			public String branch(String left, String right) {
				return "(" + left + "^" + right + ")";
			}
		});
	}

	/**
	 * Returns the sum of elements in a tree containing numbers.
	 * 
	 * Implements visitor from tree.
	 * 
	 * @param t
	 *            tree
	 * @param <N>
	 *            a subclass of Number
	 * @return double sum
	 */
	public static <N extends Number> double sum(Tree<N> t) {
		return t.visit(new Tree.Visitor<N, Double>() {

			@Override
			public Double leaf(N elt) {
				return elt.doubleValue();
			}

			@Override
			public Double branch(Double left, Double right) {
				return left + right;
			}
		});
	}

	/**
	 * Runs test on Tree class.
	 * 
	 * @param args
	 *            Command line arguments are not used in this program.
	 */
	public static void main(String[] args) {
		Tree<Integer> t = Tree.branch(Tree.branch(Tree.leaf(1), Tree.leaf(2)),
				Tree.leaf(3));
		if (!toString(t).equals("((1^2)^3)"))
			throw new AssertionError();
		if (sum(t) != 6)
			throw new AssertionError();
	}

}

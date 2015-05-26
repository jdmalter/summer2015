package interpreter;

/**
 * A pair of two independent objects grouped together. Used by Exp class as an
 * example of interpreter design pattern.
 * 
 * @author Jacob Malter
 *
 * @param <A>
 *            first object
 * @param <B>
 *            second object
 */
public final class Pair<A, B> {
	private final A left;
	private final B right;

	/**
	 * Constructs a pair with two objects.
	 * 
	 * @param left
	 *            first object
	 * @param right
	 *            second object
	 */
	public Pair(A left, B right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns the left object.
	 * 
	 * @return the left object
	 */
	public A left() {
		return left;
	}

	/**
	 * Returns the right object.
	 * 
	 * @return the right object
	 */
	public B right() {
		return right;
	}

}

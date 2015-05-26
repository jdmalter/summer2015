package interpreter;

/**
 * A class representing an expression.
 * 
 * This implementation uses the interpreter design pattern.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *            type of expression evaluated
 */
public abstract class Exp<T> {

	/**
	 * Evaluates an expression and returns the result.
	 * 
	 * @return result
	 */
	public abstract T eval();

	/**
	 * Literal expression of type
	 * 
	 * @param i
	 *            int value converted to Integer
	 * @return literal Integer
	 */
	static Exp<Integer> lit(final int i) {
		return new Exp<Integer>() {
			public Integer eval() {
				return i;
			}
		};
	}

	/**
	 * Sums two expressions.
	 * 
	 * @param e1
	 *            first subexpression
	 * @param e2
	 *            second subexpression
	 * @return sum of expressions
	 */
	static Exp<Integer> plus(final Exp<Integer> e1, final Exp<Integer> e2) {
		return new Exp<Integer>() {
			public Integer eval() {
				return e1.eval() + e2.eval();
			}
		};
	}

	/**
	 * Constructs a pair using two expressions.
	 * 
	 * @param e1
	 *            first subexpression
	 * @param e2
	 *            second subexpression
	 * @param <A>
	 *            first object in pair
	 * @param <B>
	 *            second object in pair
	 * @return a pair expression
	 */
	static <A, B> Exp<Pair<A, B>> pair(final Exp<A> e1, final Exp<B> e2) {
		return new Exp<Pair<A, B>>() {
			public Pair<A, B> eval() {
				return new Pair<A, B>(e1.eval(), e2.eval());
			}
		};
	}

	/**
	 * An expression from a pair expression.
	 * 
	 * @param e
	 *            pair expression
	 * @param <A>
	 *            first object in pair
	 * @param <B>
	 *            second object in pair
	 * @return left returned as expression
	 */
	static <A, B> Exp<A> left(final Exp<Pair<A, B>> e) {
		return new Exp<A>() {
			public A eval() {
				return e.eval().left();
			}
		};
	}

	/**
	 * An expression from a pair expression.
	 * 
	 * @param e
	 *            pair expression
	 * @param <A>
	 *            first object in pair
	 * @param <B>
	 *            second object in pair
	 * @return right returned as expression
	 */
	static <A, B> Exp<B> right(final Exp<Pair<A, B>> e) {
		return new Exp<B>() {
			public B eval() {
				return e.eval().right();
			}
		};
	}

	/**
	 * Runs test on Exp class.
	 * 
	 * @param args
	 *            Command line arguments not used in this program.
	 */
	public static void main(String[] args) {
		Exp<Integer> e = left(pair(plus(lit(3), lit(4)), lit(5)));
		if (e.eval() != 7)
			throw new AssertionError();
	}

}

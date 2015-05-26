package function;

/**
 * Declares a function which takes one argument A and returns an argument B
 * which throws exception X.
 * 
 * This implementation relies on the function design pattern.
 * 
 * @author Jacob Malter
 *
 * @param <A>
 *            Argument of a function
 * @param <B>
 *            Result of a function
 * @param <X>
 *            checked exception thrown by function
 */
public interface Function<A, B, X extends Throwable> {

	/**
	 * Applies a function to an argument.
	 * 
	 * @param a
	 *            argument
	 * @return result of function on argument
	 * @throws X
	 *             checked exception
	 */
	public B apply(A a) throws X;

}

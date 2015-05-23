package evolutiongeneric;

/**
 * Legacy library static stack utility method.
 * 
 * @author Jacob Malter
 *
 */
public class Stacks {

	/**
	 * Reverses a stack parameter and returns the result.
	 * 
	 * This implementation empties the stack being passed in as a parameter.
	 * 
	 * @param <E>
	 * 
	 * @param in
	 *            stack being reversed
	 * @return reversed stack from input
	 * @throws NullPointerException
	 *             null is passed as argument
	 */
	public static <E> Stack<E> reverse(Stack<E> in) {
		if (in == null)
			throw new NullPointerException();

		Stack<E> out = new ArrayStack<E>();
		while (!in.isEmpty()) {
			E elt = in.pop();
			out.push(elt);
		}
		return out;
	}

}

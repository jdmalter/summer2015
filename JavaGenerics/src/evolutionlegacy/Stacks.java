package evolutionlegacy;

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
	 * @param in
	 *            stack being reversed
	 * @return reversed stack from input
	 * @throws NullPointerException
	 *             null is passed as argument
	 */
	public static Stack reverse(Stack in) {
		if (in == null)
			throw new NullPointerException();

		Stack out = new ArrayStack();
		while (!in.isEmpty()) {
			Object elt = in.pop();
			out.push(elt);
		}
		return out;
	}

}

package evolutionlegacy;

/**
 * A test class for legacy code.
 * 
 * @author Jacob Malter
 *
 */
public class Client {

	/**
	 * Runs tests.
	 * 
	 * @param args
	 *            Command line arguments not used in this program.
	 */
	public static void main(String[] args) {
		Stack stack = new ArrayStack();
		for (int i = 0; i < 4; i++)
			stack.push(new Integer(i));
		System.out.println(stack.toString());
		if (!stack.toString().equals("stack[ 0 1 2 3 ]"))
			throw new AssertionError();
		int top = ((Integer) stack.pop()).intValue();
		if (top != 3 && stack.toString().equals("stack[ 0 1 2 ]"))
			throw new AssertionError();
		Stack reverse = Stacks.reverse(stack);
		if (!stack.isEmpty())
			throw new AssertionError();
		System.out.println(reverse.toString());
		if (!reverse.toString().equals("stack[ 2 1 0 ]"))
			throw new AssertionError();
	}
}

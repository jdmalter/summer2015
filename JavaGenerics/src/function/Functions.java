package function;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class containing static methods using Function interface. This class tests
 * Function and its function design pattern.
 * 
 * @author Jacob Malter
 *
 */
public class Functions {

	/**
	 * Applies a function to a list of arguments and returns a list of results.
	 * 
	 * @param f
	 *            function applied
	 * @param list
	 *            list of arguments
	 * @param <A>
	 *            Argument of a function
	 * @param <B>
	 *            Result of a function
	 * @param <X>
	 *            checked exception thrown by function
	 * @return list of results
	 * @throws X
	 *             checked exception throw by functions
	 */
	public static <A, B, X extends Throwable> List<B> applyAll(
			Function<A, B, X> f, List<A> list) throws X {
		List<B> result = new ArrayList<B>(list.size());
		for (A x : list)
			result.add(f.apply(x));
		return result;
	}

	/**
	 * Tests Functions method and Function interface.
	 * 
	 * @param args
	 *            Command line arguments not used in this program.
	 */
	public static void main(String[] args) {

		// Define functions to apply
		Function<String, Integer, Error> length = new Function<String, Integer, Error>() {
			@Override
			public Integer apply(String a) throws Error {
				return a.length();
			}
		};
		Function<String, Class<?>, ClassNotFoundException> forName = new Function<String, Class<?>, ClassNotFoundException>() {
			@Override
			public Class<?> apply(String a) throws ClassNotFoundException {
				return Class.forName(a);
			}

		};
		Function<String, Method, Exception> getRunMethod = new Function<String, Method, Exception>() {
			@Override
			public Method apply(String a) throws ClassNotFoundException,
					NoSuchMethodException {
				return Class.forName(a).getMethod("run");
			}
		};

		// Gather classes
		List<String> strings = Arrays.asList("function.Function",
				"function.Functions");

		// Tests length function
		System.out.println(applyAll(length, strings));

		// Tests forName function
		try {
			System.out.println(applyAll(forName, strings));
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}

		// Tests getRunMethod
		try {
			System.out.println(applyAll(getRunMethod, strings));
		} catch (ClassNotFoundException | NoSuchMethodException e) {
			System.out.println(e);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new AssertionError();
		}
	}

}

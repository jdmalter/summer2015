package declarations;

/**
 * A pair of objects.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *            one type
 * @param <U>
 *            another type
 */
public final class Pair<T, U> {
	private final T first;
	private final U second;

	/**
	 * Constructs an object containing two other object.
	 * 
	 * @param first
	 *            one type
	 * @param second
	 *            another type
	 */
	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns the first object.
	 * 
	 * @return the first object
	 */
	public T first() {
		return first;
	}

	/**
	 * Returns the second object.
	 * 
	 * @return the second object
	 */
	public U second() {
		return second;
	}

}

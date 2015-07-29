package collection;

/**
 * A set extended from HashBag. Add method is refined to not accept duplicates
 * which brakes complete substitutability.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class HashSet<E> extends HashBag<E> implements Set<E> {

	/**
	 * Constructs HashSet with default capacity and default load.
	 */
	public HashSet() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs HashSet with parameter capacity and parameter load.
	 * 
	 * Note: capacity subject to grow.
	 * 
	 * @param capacity
	 *            beginning capacity
	 * @throws IllegalArgumentException
	 *             when {@code initCapacity < 1} or
	 */
	public HashSet(int initCapacity) {
		super(initCapacity);
	}

	@Override
	public boolean add(E obj) {
		return !contains(obj) ? super.add(obj) : false;
	}

}

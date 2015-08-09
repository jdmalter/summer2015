package collection;

/**
 * A set extended from HashMultiset. Add method is refined to not accept
 * duplicates which brakes complete substitutability.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class HashSet<E> extends HashMultiset<E> implements Set<E> {

	/**
	 * Constructs HashSet with default capacity and default load.
	 */
	public HashSet() {
		super();
	}

	@Override
	public boolean add(E obj) {
		return !contains(obj) ? super.add(obj) : false;
	}

}

package collection;

/**
 * A set extended from a bag Add method is refined to not accept duplicates
 * which brakes complete substitutability.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class HashSet<E> extends HashBag<E> implements Set<E> {

	@Override
	public boolean add(E obj) {
		return !contains(obj) ? super.add(obj) : false;
	}

}

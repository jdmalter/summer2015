package collection;

import java.util.Comparator;

/**
 * Ordered set backed by a red-black tree. Add method is refined to not accept
 * duplicates which brakes complete substitutability.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class TreeSet<E extends Comparable<? super E>> extends TreeMultiset<E>
		implements Set<E> {

	/**
	 * Constructs an empty TreeSet using natural ordering comparator.
	 */
	public TreeSet() {
		this(null);
	}

	/**
	 * Constructs an empty TreeSet using given comparator.
	 * 
	 * @param comparator
	 *            ordering being used
	 */
	public TreeSet(Comparator<? super E> comparator) {
		super(comparator);
	}

	@Override
	public boolean add(E obj) {
		return !contains(obj) ? super.add(obj) : false;
	}

}

package collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * A linked set extended from TreeSet. Iterator guarantees in-order element
 * returns.
 * 
 * This implementation currently fails to guarantee in-order elements returns
 * and rather returns elements in the other that they were stored.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class LinkedTreeSet<E extends Comparable<? super E>> extends TreeSet<E> {

	/** Maintains in-order entries */
	private Collection<E> linkedEntries;

	/**
	 * Constructs an empty TreeSet using natural ordering comparator.
	 */
	public LinkedTreeSet() {
		this(null);
	}

	/**
	 * Constructs an empty TreeSet using given comparator.
	 * 
	 * @param comparator
	 *            ordering being used
	 */
	@SuppressWarnings("unchecked")
	public LinkedTreeSet(Comparator<? super E> comparator) {
		super(comparator);
		linkedEntries = (Collection<E>) Collections.EMPTY_COMPARABLE_COLLECTION;
	}

	@Override
	public boolean add(E obj) {
		if (contains(obj))
			return false;
		linkedEntries.add(obj);
		return super.add(obj);
	}

	@Override
	public void clear() {
		linkedEntries.clear();
		super.clear();
	}

	/**
	 * This implementation returns a linked iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return linkedEntries.iterator();
	}

	@Override
	public boolean remove(Object obj) {
		linkedEntries.remove(obj);
		return super.remove(obj);
	}

}

package collection;

import java.util.Iterator;

/**
 * A linked set extended from Hashset. Iterator guarantees elements returned in
 * order of adding.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class LinkedHashSet<E> extends HashSet<E> {

	public static final Collection<Object> linkedCollection = new LinkedList<Object>();

	/** Maintains entries in the order they were stored */
	private Collection<E> linkedEntries;

	/**
	 * Constructs LinkedHashSet with default capacity and default load.
	 */
	public LinkedHashSet() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Constructs LinkedHashSet with parameter capacity and parameter load.
	 * 
	 * Note: capacity subject to grow.
	 * 
	 * @param capacity
	 *            beginning capacity
	 * @throws IllegalArgumentException
	 *             when {@code initCapacity < 1} or
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashSet(int initCapacity) {
		super(initCapacity);
		linkedEntries = (Collection<E>) linkedCollection;
		// suppressed warning safe since only elements of type E will be
		// inserted
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
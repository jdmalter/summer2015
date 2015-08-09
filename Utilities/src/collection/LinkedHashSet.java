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

	/** Maintains entries in the order they were stored */
	private Collection<E> linkedEntries;

	/**
	 * Constructs LinkedHashSet with default capacity and default load.
	 */
	@SuppressWarnings("unchecked")
	public LinkedHashSet() {
		super();
		linkedEntries = (Collection<E>) Collections.ITERABLE_AND_REMOVE_COLLECTION;
		// suppression safe since only elements of type E will be inserted
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

package collection;

import java.util.Iterator;

/**
 * A skeletal implementation of the multiset (and set) interface.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractMultiset<E> extends AbstractCollection<E> implements
		Multiset<E> {

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (null == obj)
			return false;
		if (!(obj instanceof AbstractMultiset<?>))
			return false;
		Collection<?> other = (Collection<?>) obj;

		if (other.size() != size())
			return false;
		return containsAll(other);
	}

	@Override
	public int hashCode() {
		int result = 0;
		Iterator<E> it = iterator();

		while (it.hasNext()) {
			E next = it.next();
			if (next != null)
				result += next.hashCode();
		}

		return result;
	}

	@Override
	public abstract Iterator<E> iterator();

	@Override
	public abstract int size();

}

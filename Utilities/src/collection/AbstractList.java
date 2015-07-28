package collection;

/**
 * A skeletal implementation of the list interface.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractList<E> extends AbstractCollection<E> implements
		List<E> {

	@Override
	public boolean add(E obj) {
		add(size(), obj);
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean changed = false;
		for (E e : c) {
			add(index++, e);
			changed = true;
		}
		return changed;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) >= 0;
	}

	@Override
	public boolean remove(Object obj) {
		remove(indexOf(obj));
		return true;
	}

}

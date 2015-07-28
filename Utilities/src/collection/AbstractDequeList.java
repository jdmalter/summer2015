package collection;

import java.util.NoSuchElementException;

/**
 * A skeletal implementation of the combined deque and list interfaces.
 * 
 * This implementation copies methods from AbstractList<E> since multiple
 * inheritance is prohibited.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractDequeList<E> extends AbstractDeque<E> implements
		Deque<E>, List<E> {

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
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		add(size(), e);
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) >= 0;
	}

	@Override
	public E getFirst() {
		return get(0);
	}

	@Override
	public E getLast() {
		return get(size());
	}

	@Override
	public E pollFirst() {
		return isEmpty() ? null : remove(0);
	}

	@Override
	public E pollLast() {
		return isEmpty() ? null : remove(size());
	}

	@Override
	public E remove() {
		if (isEmpty())
			throw new NoSuchElementException("No elements in container");

		return remove(0);
	}

	@Override
	public boolean remove(Object obj) {
		remove(indexOf(obj));
		return true;
	}

	@Override
	public E removeFirst() {
		E result = get(0);
		remove(0);
		return result;
	}

	@Override
	public boolean removeFirstOccurence(Object o) {
		remove(indexOf(o));
		return true;
	}

	@Override
	public E removeLast() {
		E result = get(size());
		remove(size());
		return result;
	}

	@Override
	public boolean removeLastOccurence(Object o) {
		remove(lastIndexOf(o));
		return true;
	}

}

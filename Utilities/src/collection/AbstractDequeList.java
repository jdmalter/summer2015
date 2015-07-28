package collection;

import java.util.NoSuchElementException;

/**
 * A skeletal implementation of the combined deque and list interfaces.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractDequeList<E> extends AbstractCollection<E>
		implements Deque<E>, List<E> {

	@Override
	public boolean add(E obj) {
		addLast(obj);
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		int start = index;
		for (E e : c)
			add(start++, e);
		return true;
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
	public E element() {
		return isEmpty() ? null : getFirst();
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
	public boolean offer(E obj) {
		addLast(obj);
		return true;
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public E peekFirst() {
		return isEmpty() ? null : getFirst();
	}

	@Override
	public E peekLast() {
		return isEmpty() ? null : getLast();
	}

	@Override
	public E poll() {
		return pollFirst();
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
	public E pop() {
		if (isEmpty())
			throw new NoSuchElementException("No element in container");

		return remove(0);
	}

	@Override
	public void push(E e) {
		addFirst(e);
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

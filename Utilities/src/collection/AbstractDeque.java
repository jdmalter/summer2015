package collection;

import java.util.NoSuchElementException;

/**
 * A skeletal implementation of the deque interface.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractDeque<E> extends AbstractQueue<E> implements
		Deque<E> {

	@Override
	public boolean add(E obj) {
		addLast(obj);
		return true;
	}

	@Override
	public E element() {
		if (isEmpty())
			throw new NoSuchElementException();
		return getFirst();
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
	public E peekFirst() {
		return isEmpty() ? null : getFirst();
	}

	@Override
	public E peekLast() {
		return isEmpty() ? null : getLast();
	}

	@Override
	public E pop() {
		return remove();
	}

	@Override
	public void push(E e) {
		addFirst(e);
	}

}

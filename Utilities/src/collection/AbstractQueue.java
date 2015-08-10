package collection;

/**
 * A skeletal implementation of the queue interface.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractQueue<E> extends AbstractCollection<E> implements
		Queue<E> {

	@Override
	public boolean offer(E obj) {
		return add(obj);
	}

	@Override
	public E peek() {
		return isEmpty() ? null : element();
	}

	@Override
	public E poll() {
		return isEmpty() ? null : remove();
	}

}

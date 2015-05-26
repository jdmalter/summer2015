package sets;

import java.util.Collection;

/**
 * A collection that cannot contain duplicate elements.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface Set<E> extends Collection<E> {

	/**
	 * Extends add provided from Collection<E> but adding existing elements has
	 * no effect.
	 * 
	 * @return true if collection changed
	 */
	@Override
	public boolean add(E e);

	/**
	 * Extends addAll provided from Collection<E> but adding existing elements
	 * has no effect.
	 * 
	 * @return true if collection changed
	 */
	@Override
	public boolean addAll(Collection<? extends E> c);

}

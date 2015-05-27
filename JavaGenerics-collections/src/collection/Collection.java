package collection;

import java.util.Iterator;

/**
 * A collection containing core functionality. Provides adding, removing,
 * querying contents, and translating operations.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            element type
 */
public interface Collection<E> {

	/**
	 * Adds element e.
	 * 
	 * @param e
	 *            element added into invoking collection
	 * @return boolean result indicates whether or not invoking collection was
	 *         changed by operation. Elements added must be present after
	 *         execution. Throws an exception if a collection refuses an element
	 *         for any reason.
	 */
	public boolean add(E e);

	/**
	 * Adds contents of c.
	 * 
	 * @param c
	 *            collection of elements added into invoking collection
	 * @return boolean result indicates whether or not invoking collection was
	 *         changed by operation. Elements added must be present after
	 *         execution. Throws an exception if a collection refuses an element
	 *         for any reason.
	 */
	public boolean addAll(Collection<? extends E> c);

	/**
	 * Removes all elements.
	 */
	public void clear();

	/**
	 * Returns true if o is present.
	 * 
	 * @param o
	 *            object searched for
	 * @return true if o is present, false otherwise.
	 */
	public boolean contains(Object o);

	/**
	 * Returns true if all elements of c are present.
	 * 
	 * @param c
	 *            collection searched for
	 * @return true if all elements of c are present, false otherwise
	 */
	public boolean containsAll(Collection<?> c);

	/**
	 * Returns Iterator over elements.
	 * 
	 * @return Iterator over elements.
	 */
	public Iterator<E> iterator();

	/**
	 * Returns true if no elements are present.
	 * 
	 * @return true if no elements are present, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Removes element o.
	 * 
	 * Removes null from collection if null is parameter. Otherwise, removes
	 * element which {@code o.equals(e)} returns true.
	 * 
	 * @param o
	 *            element removed
	 * @return true if collection was changed by operation, false otherwise
	 */
	public boolean remove(Object o);

	/**
	 * Removes elements in c.
	 * 
	 * Removes null from collection if null is parameter. Otherwise, removes
	 * element which {@code o.equals(e)} returns true.
	 * 
	 * @param c
	 *            collection removed
	 * @return true if collection was changed by operation, false otherwise
	 */
	public boolean removeAll(Collection<?> c);

	/**
	 * Remove elements not in c.
	 * 
	 * Removes null from collection if null is parameter. Otherwise, removes
	 * element which {@code o.equals(e)} returns true.
	 * 
	 * @param c
	 *            collection retained
	 * @return true if collection was changed by operation, false otherwise
	 */
	public boolean retainAll(Collection<?> c);

	/**
	 * Returns element count (or INTEGER.MAX_VALUE if that is less).
	 * 
	 * @return element count
	 */
	public int size();

	/**
	 * Converts contents of collection into Object[]. Returns new array of
	 * Object.
	 * 
	 * @return new array of Object
	 */
	public Object[] toArray();

	/**
	 * Converts contents of collection into T[]. Returns new array of type T.
	 * Implicitly throws ArrayStoreException if elements type E cannot be cast
	 * to T.
	 * 
	 * @param a
	 *            array with components of type T
	 * @param <T>
	 *            of components
	 * @return new array of type T
	 */
	public <T> T[] toArray(T[] a);

}

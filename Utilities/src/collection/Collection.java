/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Iterator;

/**
 * Root of the collection hierarchy. Defines basic behavior for a group of
 * objects. Uses individual and bulk methods. Includes methods to convert
 * collection into an array.
 * 
 * Every concrete subclass and extending interface must implement this
 * interface.
 * 
 * @author Jacob Malter
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public interface Collection<E> extends Iterable<E> {

	/**
	 * Adds object parameter to collection.
	 * 
	 * @param obj
	 *            object added into collection
	 * @return true if collection was changed. Elements added must be present
	 *         after execution. Throws an exception if a collection refuses an
	 *         element for any reason.
	 */
	boolean add(E obj);

	/**
	 * Adds all elements of collection parameter to collection.
	 * 
	 * @param coll
	 *            collection added into collection
	 * @return true if collection was changed. Elements added must be present
	 *         after execution. Throws an exception if a collection refuses an
	 *         element for any reason.
	 */
	boolean addAll(Collection<? extends E> coll);

	/**
	 * Removes all elements in a collection.
	 */
	void clear();

	/**
	 * Tests an object against elements inside a collection.
	 * 
	 * @param obj
	 *            object compared to collection
	 * @return true if object belongs to collection, false otherwise
	 */
	boolean contains(Object obj);

	/**
	 * Tests all objects against elements inside a collection.
	 * 
	 * @param c
	 *            collection compared to collection
	 * @return true if all objects belong to collection, false otherwise
	 */
	boolean containsAll(Collection<?> c);

	/**
	 * Equivalence relation must be reflexive (a,a), symmetric (a,b)-->(b,a),
	 * and transitive ((a,b)^(b,c))-->(a,c).
	 * 
	 * @param obj
	 *            Another collection compared to collection
	 * @return true if equal, false otherwise
	 */
	boolean equals(Object obj);

	/**
	 * Generates a hashcode for a collection.
	 * 
	 * @return int hashcode
	 */
	int hashCode();

	/**
	 * Returns true if no elements are present.
	 * 
	 * @return true if no elements are present, false otherwise.
	 */
	boolean isEmpty();

	/**
	 * Creates an iterator for a collection.
	 * 
	 * @return iterator over elements
	 */
	Iterator<E> iterator();

	/**
	 * Remove object parameter from collection.
	 * 
	 * @param obj
	 *            object removed from collection
	 * @return true if collection was changed by operation, false otherwise
	 */
	boolean remove(Object obj);

	/**
	 * Removes all elements of collection parameter to collection.
	 * 
	 * @param coll
	 *            collection removed from collection
	 * @return true if collection was changed by operation, false otherwise
	 * @throws NullPointerException
	 *             when parameter coll is null
	 */
	boolean removeAll(Collection<?> coll);

	/**
	 * Retains all elements of collection parameter to collection and removes
	 * other objects.
	 * 
	 * @param coll
	 *            collection retained from collection
	 * @return true if collection was changed by operation, false otherwise
	 * @throws NullPointerException
	 *             when parameter coll is null
	 */
	boolean retainAll(Collection<?> coll);

	/**
	 * Returns number of elements.
	 * 
	 * @return number of elements
	 */
	int size();

	/**
	 * Translates a collection into an array.
	 * 
	 * @return new array of Object
	 */
	Object[] toArray();

	/**
	 * Translates a collection into an array whose element's type matches the
	 * parameter array element's type. Returns new array of type T. Implicitly
	 * throws ArrayStoreException if elements type E cannot be cast to T.
	 * 
	 * @param array
	 *            array from which element types are taken
	 * @param <T>
	 *            of components
	 * @return new array of type T
	 * @throws ArrayStoreException
	 *             when element type E cannot be cast to T
	 */
	<T> T[] toArray(T[] array);

}

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * This interface is the root of the iterator hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Iterator<E> {

    /**
     * Checks if an iterator has more elements to call next() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasNext();

    /**
     * Returns the next element in iterator.
     * 
     * @return the next element in iterator
     */
    E next();

}

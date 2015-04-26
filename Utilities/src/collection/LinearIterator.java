/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Adds forward and backward traversal into iterator operations. Includes
 * relative add, remove, and set modifications.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface LinearIterator<E> extends Iterator<E> {

    /**
     * Inserts parameter object before next() or after previous(). A call to
     * next is unaffected, and a call to previous returns added object. Only
     * valid if called after next() or previous() and before remove() or set(E
     * e).
     * 
     * @param e
     */
    void add(E e);

    /**
     * Checks if an iterator has more elements to call next() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasNext();

    /**
     * Checks if an iterator has more elements to call previous() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasPrevious();

    /**
     * Returns the next element in iterator.
     * 
     * @return the next element in iterator
     */
    E next();

    /**
     * Returns the previous element in iterator.
     * 
     * @return the previous element in iterator
     */
    E previous();

    /**
     * Removes the last element returned by next() or previous(). Only valid if
     * called after next() or previous() and before set(E e) or add(E e).
     */
    void remove();

    /**
     * Sets the last element returned by next() or previous() with parameter
     * object. Only valid if called after next() or previous() and before
     * remove() or add().
     * 
     * @param e
     */
    void set(E e);

}

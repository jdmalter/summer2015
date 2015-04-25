/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Deque<E> extends Queue<E> {

    /**
     * Adds object parameter to back of deque.
     * 
     * @param obj
     *            object added into queue
     * @return true if object was added, false otherwise
     */
    boolean addFirst(E obj);

    /**
     * Returns last element without removing it.
     * 
     * @return first element of deque
     */
    E peekLast();

    /**
     * Remove object from back of deque.
     * 
     * @return true if object removed, false otherwise
     */
    boolean removeLast();

}

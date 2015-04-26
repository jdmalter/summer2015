/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior for a first in, first out (FIFO) collection.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Queue<E> extends Collection<E> {

    /**
     * Adds object parameter to back of queue.
     * 
     * @param obj
     *            object added into queue
     * @return true if object was added, false otherwise
     */
    boolean addLast(E obj);

    /**
     * Returns first element without removing it.
     * 
     * @return first element of queue
     */
    E peekFirst();

    /**
     * Remove object from front of queue.
     * 
     * @return first element of queue
     */
    E removeFirst();
}

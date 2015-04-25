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
public interface Stack<E> extends Collection<E> {

    /**
     * Returns first element without removing it.
     * 
     * @return first element of queue
     */
    E peekFirst();

    /**
     * Remove object from top of stack.
     * 
     * @return true if object removed, false otherwise
     */
    E popFirst();

    /**
     * Adds object parameter to back of stack.
     * 
     * @param obj
     *            object added into stack
     * @return true if object was added, false otherwise
     */
    boolean pushFirst(E obj);

}

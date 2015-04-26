/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior for a last in, first out (LIFO) collection.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Stack<E> extends Collection<E> {

    /**
     * Returns top element without removing it.
     * 
     * @return first element of stack
     */
    E peekFirst();

    /**
     * Remove object from top of stack.
     * 
     * @return top element of stack
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

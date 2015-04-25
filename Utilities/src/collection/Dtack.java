/**
 * 
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
public interface Dtack<E> extends Stack<E> {

    /**
     * Returns bottom element without removing it.
     * 
     * @return top element of dtack
     */
    E peekLast();

    /**
     * Remove object from bottom of dtack.
     * 
     * @return true if object removed, false otherwise
     */
    E popLast();

    /**
     * Adds object parameter to bottom of dtack.
     * 
     * @param obj
     *            object added into dtack
     * @return true if object was added, false otherwise
     */
    boolean pushLast(E obj);

}

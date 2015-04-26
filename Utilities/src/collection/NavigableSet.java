/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Further removed from the mathematical definition of a set. Order does matter,
 * and replacement is not allowed. Adds behavior to get the ceiling, floor,
 * higher, and lower element relative to an element.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface NavigableSet<E> extends SortedSet<E> {

    /**
     * Returns ceiling on parameter element.
     * 
     * @param e
     *            object to match
     * @return least element greater or equal to e, null otherwise
     */
    E ceiling(E e);

    /**
     * Returns floor on parameter element.
     * 
     * @param e
     *            object to match
     * @return greatest element less or equal to e, null otherwise
     */
    E floor(E e);

    /**
     * Returns higher on parameter element.
     * 
     * @param e
     *            object to match
     * @return least element higher (but not equal) to e, null otherwise
     */
    E higher(E e);

    /**
     * Returns lower on parameter element.
     * 
     * @param e
     *            object to match
     * @return greatest element lower (but not equal) to e, null otherwise
     */
    E lower(E e);

}

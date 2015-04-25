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
public interface SortedSet<E> extends Set<E>, Sorted<E> {

    /**
     * Creates a subset from main set using parameter index for end.
     * 
     * @param end
     *            position of set end
     * @return new subset created from set
     */
    Set<E> headSet(E end);

    /**
     * Creates a subset from main set using parameter index for start.
     * 
     * @param start
     *            position of set start
     * @return new subset created from set
     */
    Set<E> tailSet(E start);
}

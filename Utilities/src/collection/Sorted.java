/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * This interface applies to data structures holding sorted elements.
 * 
 * @param <E>
 *            The type of the elements stored in a data structure.
 * 
 * @author Jacob Malter
 */
public interface Sorted<E> {

    /**
     * Returns a sorted collections comparator. If natural ordering is used,
     * null is returned.
     * 
     * @return set's comparator. null if naturally ordered.
     */
    Comparator<E> comparator();

    /**
     * Gets greatest in a collection.
     * 
     * @return greatest element in a collection
     */
    E greatest();

    /**
     * Gets least element in a collection.
     * 
     * @return least element in a collection
     */
    E least();
}

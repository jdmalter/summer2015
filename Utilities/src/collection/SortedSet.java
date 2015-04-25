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
     * Gets first element in set.
     * 
     * @return first element in set
     */
    E first();

    /**
     * Creates a subset from main set using parameter index for end.
     * 
     * @param end
     *            position of set end
     * @return new subset created from set
     */
    SortedSet<E> headSet(E end);

    /**
     * Gets last element in set.
     * 
     * @return last element in set
     */
    E last();

    /**
     * Creates a subset from main set using parameter index for start and end.
     * 
     * @param start
     *            position of set start
     * @param end
     *            position of set end
     * @return new subset created from set
     */
    SortedSet<E> subSet(E start, E end);

    /**
     * Creates a subset from main set using parameter index for start.
     * 
     * @param start
     *            position of set start
     * @return new subset created from set
     */
    SortedSet<E> tailSet(E start);
}

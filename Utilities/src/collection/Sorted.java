package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
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

}

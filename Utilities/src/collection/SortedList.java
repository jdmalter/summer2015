/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Acts like a list with elements in a specific order. Adds behavior to return a
 * headList and tailList plus behavior taken from sorted.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface SortedList<E> extends List<E>, Sorted<E> {

    /**
     * Creates a sublist from main set using parameter index for end.
     * 
     * @param end
     *            position of list end
     * @return new sublist created from set
     */
    SortedList<E> headList(E end);

    /**
     * Creates a sublist from main set using parameter index for start.
     * 
     * @param start
     *            position of list start
     * @return new subset created from list
     */
    SortedList<E> tailList(E start);

}

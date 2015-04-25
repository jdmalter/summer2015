/**
 * 
 */
package collection;

/**
 * Allows an object to have its iterator returned.
 * 
 * @param <T>
 *            The type of the elements iterated over.
 * @author Jacob Malter
 *
 */
public interface Iterable<T> {

    /**
     * Creates iterator over elements.
     * 
     * @return an iterator
     */
    Iterator<T> iterator();

}

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior necessary for sorting.
 * 
 * @param <T>
 *            The type of the objects being compared
 * 
 * @author Jacob Malter
 */
public interface Comparable<T> {

    /**
     * Compares objects to impose sorting.
     * 
     * @param other
     *            object being compared to
     * @return a negative integer, zero, or positive integer if other object is
     *         less than, equal to, or greater than object
     */
    int compareTo(T other);

}

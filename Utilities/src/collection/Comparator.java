/**
 * 
 */
package collection;

/**
 * Defines orders based on comparisons.
 * 
 * @param <T>
 *            The type of the objects being compared
 * 
 * @author Jacob Malter
 */
public interface Comparator<T> {

    /**
     * Compares objects to impose sorting.
     * 
     * @param obj1
     *            one object being compared
     * @param obj2
     *            another object being compared
     * @return a negative integer, zero, or positive integer if second object is
     *         less than, equal to, or greater than first object
     */
    int compare(T obj1, T obj2);

    /**
     * May be used to check if a comparator imposes the same ordering.
     * 
     * @param obj
     *            Another collection compared to collection
     * @return true if equal, false otherwise
     */
    boolean equals(Object obj);

    /**
     * Returns a comparator that uses the reverse ordering.
     * 
     * @return A comparator that uses the reverse ordering.
     */
    Comparator<T> reversed();
}

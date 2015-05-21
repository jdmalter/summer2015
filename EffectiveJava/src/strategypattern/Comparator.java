/**
 * Illustrates the strategy design pattern from item 21: use function objects
 * to represent strategies.
 */
package strategypattern;

/**
 * Strategy interface.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 * @param <T>
 *            type being compared
 */
public interface Comparator<T> {

	/**
	 * Compares two objects for ordering.
	 * 
	 * @param t1
	 *            first object being compared
	 * @param t2
	 *            second object being compared
	 * @return negative, zero, or positive integer if the first object is less
	 *         than, equal to, or greater than the second object
	 */
	public int compare(T t1, T t2);

}

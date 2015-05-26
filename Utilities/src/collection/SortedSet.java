/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Not based on the mathematical definition of a set. Order does matter, and
 * replacement is not allowed. Adds behavior to return a headSet and tailSet
 * plus behavior taken from sorted.
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
	SortedSet<E> headSet(E end);

	@Override
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

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Based on the mathematical definition of a set. Basically acts like a bag, but
 * overrides equals and hashcode methods. Order does not matter, and replacement
 * is not allowed. Adds behavior to return a subSet.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Set<E> extends Bag<E> {

	/**
	 * Adds object parameter to collection if it is unique. Duplicate objects
	 * have no effect.
	 * 
	 * @param obj
	 *            object added into collection
	 * @return true if collection changed
	 */
	@Override
	boolean add(E obj);

	/**
	 * Adds every element from producing collection into invoking collection if
	 * it is unique. Duplicate objects have no effect.
	 * 
	 * @param coll
	 *            object added into collection
	 * @return true if collection changed
	 */
	boolean addAll(Collection<? extends E> coll);

	/**
	 * Equivalence relation must be reflexive (a,a), symmetric (a,b)-->(b,a),
	 * and transitive ((a,b)^(b,c))-->(a,c). Equals acts regardless of element
	 * ordering.
	 * 
	 * @param obj
	 *            Another collection compared to collection
	 * @return true if equal, false otherwise
	 */
	@Override
	boolean equals(Object obj);

	/**
	 * Generates a hashcode for a collection. Hashcode acts regardless of
	 * element ordering.
	 * 
	 * @return int hashcode
	 */
	@Override
	int hashCode();

}

package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Set<E> extends Bag<E> {

    /**
     * Adds object parameter to collection if it is unique. Duplicate objects
     * are not allowed.
     * 
     * @param obj
     *            object added into collection
     * @return true if object was added, false otherwise
     */
    @Override
    boolean add(E obj);

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

package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Bag<E> extends Collection<E> {

    /**
     * Equivalence relation must be reflexive (a,a), symmetric (a,b)-->(b,a),
     * and transitive ((a,b)^(b,c))-->(a,c). Equals acts regardless of element
     * ordering, but takes number of objected added into account.
     * 
     * @param obj
     *            Another collection compared to collection
     * @return true if equal, false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Generates a hashcode for a collection. Hashcode acts regardless of
     * element ordering, but takes number of objects added into account.
     * 
     * @return int hashcode
     */
    @Override
    int hashCode();

}

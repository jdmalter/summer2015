package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Adds object parameter to collection.
     * 
     * @param obj
     *            object added into collection
     * @return true if object was added, false otherwise
     */
    boolean add(E obj);

    /**
     * Adds all elements of collection parameter to collection.
     * 
     * @param c
     *            collection added into collection
     * @return true if c was added, false otherwise
     */
    boolean addAll(Collection<E> c);

    /**
     * Removes all elements in a collection.
     */
    void clear();

    /**
     * Tests an object against elements inside a collection.
     * 
     * @param obj
     *            object compared to collection
     * @return true if object belongs to collection, false otherwise
     */
    boolean contains(E obj);

    /**
     * Tests allmobjects against elements inside a collection.
     * 
     * @param c
     *            collection compared to collection
     * @return true if all objects belong to collection, false otherwise
     */
    boolean containsAll(Collection<E> c);

    /**
     * Equivalence relation must be reflexive (a,a), symmetric (a,b)-->(b,a),
     * and transitive ((a,b)^(b,c))-->(a,c).
     * 
     * @param obj
     *            Another collection compared to collection
     * @return true if equal, false otherwise
     */
    boolean equals(Object obj);

    /**
     * Generates a hashcode for a collection.
     * 
     * @return int hashcode
     */
    int hashCode();

    /**
     * Checks a collection for elements.
     * 
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Creates an iterator for a collection.
     * 
     * @return iterator over elements
     */
    Iterator<E> iterator();

    /**
     * Remove object parameter from collection.
     * 
     * @param obj
     *            object removed from collection
     * @return true if object removed, false otherwise
     */
    boolean remove(E obj);

    /**
     * Removes all elements of collection parameter to collection.
     * 
     * @param c
     *            collection removed from collection
     * @return true if c was removed, false otherwise
     */
    boolean removeAll(Collection<E> c);

    /**
     * Retains all elements of collection parameter to collection and removes
     * other objects.
     * 
     * @param c
     *            collection retained from collection
     * @return true objects removed, false otherwise
     */
    boolean retainAll(Collection<E> c);

    /**
     * Returns the number of elements in a collection.
     * 
     * @return number of elements
     */
    int size();

    /**
     * Translates a collection into an array.
     * 
     * @return an array containing all elements
     */
    E[] toArray();

    /**
     * Translates a collection into an array whose element's type matches the
     * parameter array element's type.
     * 
     * @param array
     *            array from which element types are taken
     * @return an array containing similar typed elements
     */
    E[] toArray(E[] array);

}

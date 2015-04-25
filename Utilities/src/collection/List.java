/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * This interface is the root of the list hierarchy, extending the collections
 * hierarchy. Elements have positions, may be duplicated.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface List<E> extends Collection<E> {

    /**
     * Inserts object into a list at parameter index.
     * 
     * @param index
     *            position of insertion in a list
     * @param obj
     *            object inserted into list
     */
    void add(int index, E obj);

    /**
     * Inserts elements in collection c into a list past parameter index.
     * Elements after the index are shifted.
     * 
     * @param index
     *            position of insertion in a list
     * @param c
     *            collection of elements inserted into list
     * @return true if elements added, false otherwise
     */
    boolean addAll(int index, Collection<E> c);

    /**
     * Returns object at index in list.
     * 
     * @param index
     *            position of getting in list
     * @return object at position in list
     */
    E get(int index);

    /**
     * Returns index of first instance of an object in list.
     * 
     * @param obj
     *            object being searched
     * @return position of index in list
     */
    int indexOf(E obj);

    /**
     * Returns index of last instance of an object in list.
     * 
     * @param obj
     *            object being searched
     * @return position of index in list
     */
    int lastIndexOf(E obj);

    /**
     * Creates iterator starting at the front of list.
     * 
     * @return ListIterator over elements
     */
    ListIterator<E> listIterator();

    /**
     * Creates iterator starting at parameter index.
     * 
     * @param index
     *            position at which iterator starts.
     * @return ListIterator over elements
     */
    ListIterator<E> listIterator(int index);

    /**
     * Removes element at parameter index from list. Shifts other elements to
     * fill spacing.
     * 
     * @param index
     *            position of removed object
     * @return object removed from list
     */
    E remove(int index);

    /**
     * Assigns object to location of parameter index.
     * 
     * @param index
     *            position of set object
     * @param obj
     *            object set into list
     * @return object removed
     */
    E set(int index, E obj);

    /**
     * Creates sublist from main list using parameter indexes for start and end.
     * 
     * @param start
     *            position of list start
     * @param end
     *            position of list end
     * @return new list created from list
     */
    List<E> subList(int start, int end);

}

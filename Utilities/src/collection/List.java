/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Elements have a position. Adds new behavior needed to process elements with
 * positions. Order does matter, and replacement is allowed. Includes a method
 * to sort elements in this list and iterate with listIterator.
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
	 * Similar to {@code add(E e)} from collection but implicitly returns true
	 * or throws an exception.
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
	boolean addAll(int index, Collection<? extends E> c);

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
	int indexOf(Object obj);

	/**
	 * Returns index of last instance of an object in list.
	 * 
	 * @param obj
	 *            object being searched
	 * @return position of index in list
	 */
	int lastIndexOf(Object obj);

	/**
	 * Creates iterator starting at the front of list.
	 * 
	 * @return ListIterator over elements
	 */
	public default ListIterator<E> listIterator() {
		return listIterator(0);
	}

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
	 * @param startInclusive
	 *            start is included in return
	 * @param end
	 *            position of list end
	 * @param endInclusive
	 *            end is included in return
	 * @return new list created from list
	 */
	List<E> subList(int start, boolean startInclusive, int end,
			boolean endInclusive);

}

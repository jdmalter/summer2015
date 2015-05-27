package lists;

import java.util.Collection;
import java.util.ListIterator;

/**
 * A collection which may contain duplicate elements with positions.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public interface List<E> extends Collection<E> {

	/**
	 * Add element e at given index.
	 * 
	 * @param index
	 *            position at which element is added
	 * @param element
	 *            element being added
	 * @return true if element was added
	 */
	public boolean add(int index, E element);

	/**
	 * Adds contents of c at given index.
	 * 
	 * @param index
	 *            position at which element is added
	 * @param c
	 *            contains elements being added
	 * @return true if elements were added
	 */
	public boolean addAll(int index, Collection<? extends E> c);

	/**
	 * Returns element with given index.
	 * 
	 * @param index
	 *            index of element
	 * @return element with given index
	 */
	public E get(int index);

	/**
	 * Returns index of first o.
	 * 
	 * @param o
	 *            object searched for
	 * @return index of o
	 */
	public int indexOf(Object o);

	/**
	 * Returns index of last o.
	 * 
	 * @param o
	 *            object searched for
	 * @return index of o
	 */
	public int lastIndexOf(Object o);

	/**
	 * Returns listIterator for list at index 0.
	 * 
	 * @return listIterator for elements
	 */
	public ListIterator<E> listIterator();

	/**
	 * Returns listIterator for list at given index.
	 * 
	 * @param index
	 *            start position for iterator
	 * @return listIterator for elements
	 */
	public ListIterator<E> listIterator(int index);

	/**
	 * Removes element with given index.
	 * 
	 * @param index
	 *            index of element
	 * @return element with given index
	 */
	public E remove(int index);

	/**
	 * Replaces element at given index with E element.
	 * 
	 * @param index
	 *            index of element
	 * @param element
	 *            element being set
	 * @return element with given index
	 */
	public E set(int index, E element);

	/**
	 * Returns view of portion of list.
	 * 
	 * @param fromIndex
	 *            minimum index
	 * @param toIndex
	 *            maximum index
	 * @return all elements whose index is greater than fromIndex and less than
	 *         toIndex
	 */
	public List<E> subList(int fromIndex, int toIndex);

}
/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Labels an iterator specialized for lists with indexes.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface ListIterator<E> extends LinearIterator<E> {

    /**
     * Inserts parameter object before next() or after previous(). A call to
     * next is unaffected, and a call to previous returns added object.
     * Increases the return value of nextIndex() or previousIndex() by one. Only
     * valid if called after next() or previous() and before remove() or set(E
     * e).
     * 
     * @param e
     *            object being added by iterator
     */
    void add(E e);

    /**
     * Returns position of element in following call to next(), size if at end.
     * 
     * @return position of element in following call to next(), size if at end
     */
    int nextIndex();

    /**
     * Returns position of element in following call to previous, -1 if at
     * start.
     * 
     * @return position of element in following call to previous, -1 if at start
     */
    int previousIndex();

    /**
     * Removes the last element returned by next() or previous(). Decreases the
     * return value of nextIndex() or previousIndex() by one. Only valid if
     * called after next() or previous() and before set(E e) or add(E e).
     */
    void remove();

    /**
     * Sets the last element returned by next() or previous() with parameter
     * object. Only valid if called after next() or previous() and before
     * remove() or add().
     * 
     * @param e
     *            object being set by iterator
     */
    void set(E e);

}
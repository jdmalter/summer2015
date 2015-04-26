/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines a unique iterator interface specialized for binary trees. Returns
 * roots and subtrees.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface BinaryTreeIterator<E> {

    /**
     * Checks if an iterator has more elements to call left() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasLeft();

    /**
     * Checks if an iterator has more elements to call right() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasRight();

    /**
     * Checks if an iterator has more elements to call root() upon.
     * 
     * @return true if the iterator has more elements, false otherwise
     */
    boolean hasRoot();

    /**
     * Returns left subtree in iterator.
     * 
     * @return left subtree in iterator
     */
    E left();

    /**
     * Returns right subtree in iterator.
     * 
     * @return right subtree in iterator
     */
    E right();

    /**
     * Returns root in iterator.
     * 
     * @return root in iterator
     */
    E root();

}

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface SortedBinaryTree<E> extends BinaryTree<E>, Sorted<E> {

    /**
     * Gets left half of a tree.
     * 
     * @return left half of a tree
     */
    SortedBinaryTree<E> leftTree();

    /**
     * Gets right half of a tree.
     * 
     * @return right half of a tree.
     */
    SortedBinaryTree<E> rightTree();

}

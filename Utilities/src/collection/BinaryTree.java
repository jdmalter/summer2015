/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior needed to process elements in a binary tree. Still similar
 * in practice to a generic collection.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface BinaryTree<E> extends Collection<E> {

    /**
     * Creates iterator starting at the root of tree.
     * 
     * @return treeIterator over elements
     */
    BinaryTreeIterator<E> binaryTreeIterator();

}

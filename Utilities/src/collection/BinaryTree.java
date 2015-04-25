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
public interface BinaryTree<E> extends Collection<E> {

    /**
     * Creates iterator starting at the root of tree.
     * 
     * @return treeIterator over elements
     */
    BinaryTreeIterator<E> binaryTreeIterator();

}

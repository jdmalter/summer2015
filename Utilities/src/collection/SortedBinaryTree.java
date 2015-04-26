/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Defines behavior needed to process elements in a binary tree. Still similar
 * in practice to a generic collection. Adds behavior to return a leftTree and
 * rightTree plus behavior taken from sorted.
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

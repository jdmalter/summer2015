/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;

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
     * Creates iterator starting at the root.
     * 
     * @return BinaryTreeIterator over elements
     */
    BinaryTreeIterator<E> binaryTreeIterator();

    /**
     * Creates iterator starting at the left subtree.
     * 
     * @return BinaryTreeInOrderIterator over elements
     */
    BinaryTreeInOrderIterator<E> binaryTreeInOrderIterator();

    /**
     * Creates iterator starting at the root of tree.
     * 
     * @return BinaryTreePreOrderIterator over elements
     */
    BinaryTreePreOrderIterator<E> binaryTreePreOrderIterator();

    /**
     * Creates iterator starting at the left subtree.
     * 
     * @return BinaryTreePostOrderIterator over elements
     */
    BinaryTreePostOrderIterator<E> binaryTreePostOrderIterator();

    /**
     * Sorts elements in a binary tree using a order defined by the comparator
     * parameter.
     * 
     * @param c
     *            comparator used for sorting
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    default void sort(Comparator<? super E> c) {
	Object[] a = toArray();
	Arrays.sort(a, (Comparator) c);
	BinaryTreeInOrderIterator<E> i = binaryTreeInOrderIterator();
	for (Object e : a) {
	    i.next();
	    i.set((E) e);
	}
    }

}

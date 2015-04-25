package collection;

/**
 * This interface is the root of the collection hierarchy.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface BinarySortedTree<E> extends BinaryTree<E>, Sorted<E> {

    /**
     * Gets left half of a tree.
     * 
     * @return left half of a tree
     */
    BinarySortedTree<E> leftTree();

    /**
     * Gets right half of a tree.
     * 
     * @return right half of a tree.
     */
    BinarySortedTree<E> rightTree();

    /**
     * Gets left element in tree.
     * 
     * @return left element in tree
     */
    E left();

    /**
     * Gets right element in tree.
     * 
     * @return right element in tree
     */
    E right();

}

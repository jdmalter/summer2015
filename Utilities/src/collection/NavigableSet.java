/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Further removed from the mathematical definition of a set. Order does matter,
 * and replacement is not allowed. Adds behavior to get the ceiling, floor,
 * higher, and lower element relative to an element.
 * 
 * @param <E>
 *            The type of the elements stored in this collection.
 * 
 * @author Jacob Malter
 */
public interface NavigableSet<E> extends SortedSet<E>, Navigable<E> {

}

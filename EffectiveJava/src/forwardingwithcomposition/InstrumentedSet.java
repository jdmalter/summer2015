/**
 * Illustrates the concept of forwarding from item 16: favor composition over inheritance.
 */
package forwardingwithcomposition;

import java.util.Collection;
import java.util.Set;

/**
 * Wrapper class using composition in place over inheritance.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 * @param <E>
 *            object type of element data
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {

	private int addCount = 0;

	/**
	 * Constructs an instrumented set which counts the number of time that an
	 * object has been added into a set.
	 * 
	 * @param s
	 *            set being forwarded
	 */
	public InstrumentedSet(Set<E> s) {
		super(s);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	/**
	 * Returns the add count.
	 * 
	 * @return the add count
	 */
	public int addCount() {
		return addCount;
	}

}

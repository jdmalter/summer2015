/**
 * Illustrates an example from item 18: prefer interfaces to abstract classes.
 * However, this class is a counter-example which illustrates when to create an abstract class.
 */
package skeletalabstractinheritance;

import java.util.Map;
import java.util.Map.Entry;

/**
 * Skeletal implementation of Entry sub-interface.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 * @param <K>
 *            type of object data used to access entry
 * @param <V>
 *            type of object data stored in entry
 */
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {
	// Primitive operations

	/**
	 * Promise to subclasses to implement this method.
	 * 
	 * @return the key
	 */
	public abstract K getKey();

	/**
	 * Promise to subclasses to implement this method.
	 * 
	 * @return the value
	 */
	public abstract V getValue();

	/**
	 * Entries in modifiable maps must override this method.
	 * 
	 * @param value
	 *            value being set into map entry
	 * @return old value set replaced by operation (never used by abstract
	 *         class)
	 */
	public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Map.Entry))
			return false;
		Map.Entry<?, ?> arg = (Entry<?, ?>) o;
		return equals(getKey(), arg.getKey())
				&& equals(getValue(), arg.getValue());
	}

	/**
	 * Helper method to object overridden equals method. Compares two objects
	 * for equality.
	 * 
	 * @param o1
	 *            first object being compared
	 * @param o2
	 *            second object being compared
	 * @return true if equal, false otherwise
	 */
	private static boolean equals(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	@Override
	public int hashCode() {
		return hashCode(getKey()) ^ hashCode(getValue());
	}

	/**
	 * Helper method to object overridden hashcode method. Generates hashcode
	 * for object.
	 * 
	 * @param obj
	 *            object generated hashcode for
	 * @return int hashcode representation of object
	 */
	private static int hashCode(Object obj) {
		return obj == null ? 0 : obj.hashCode();
	}

}

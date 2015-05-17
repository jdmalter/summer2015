/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * 
 * @author Jacob Malter
 *
 * @param <K>
 *            key value used to access data
 * @param <V>
 *            object data stored in the map
 */
public interface Map<K, V> {

	/**
	 * 
	 */
	void clear();

	/**
	 * 
	 * @param k
	 * @return
	 */
	boolean containsKey(K k);

	/**
	 * 
	 * @param v
	 * @return
	 */
	boolean containsValue(V v);

	/**
	 * 
	 * @param o
	 * @return
	 */
	boolean equals(Object o);

	/**
	 * 
	 * @param k
	 * @return
	 */
	V get(K k);

	/**
	 * 
	 * @return
	 */
	int hashCode();

	/**
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 
	 * @param v
	 * @param k
	 * @return
	 */
	V put(V v, K k);

	/**
	 * 
	 * @param m
	 */
	void putAll(Map<? extends K, ? extends V> m);

	/**
	 * 
	 * @param k
	 * @return
	 */
	V remove(K k);

	/**
	 * 
	 * @return
	 */
	int size();

	/**
	 * 
	 * @return
	 */
	Collection<V> values();

	/**
	 * 
	 * @author Jacob Malter
	 *
	 * @param <K>
	 *            key value used to access map
	 * @param <V>
	 *            object data stored within map
	 */
	interface Entry<K, V> {

		boolean equals(Object o);

		K getKey();

		V getValue();

		int hashcode();
	}
}
package maps;

import java.util.Collection;
import java.util.Set;

/**
 * A map which associates many unique keys to a single values.
 * 
 * @author Jacob Malter
 *
 * @param <K>
 *            key type
 * @param <V>
 *            value type
 */
public interface Map<K, V> {

	/**
	 * A single key value association.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <K>
	 *            key type
	 * @param <V>
	 *            value type
	 */
	public interface Entry<K, V> {

		/**
		 * Sets value in assocation to parameter.
		 * 
		 * @param value
		 *            value being set
		 * @return old value, or null if none
		 */
		public V setValue(V value);

	}

	/**
	 * Removes all associations.
	 */
	public void clear();

	/**
	 * Returns true if key is present.
	 * 
	 * @param key
	 *            key searched for
	 * @return true if key is present
	 */
	public boolean containsKey(Object key);

	/**
	 * Returns true if value is present.
	 * 
	 * @param value
	 *            value searched for
	 * @return true if value is present
	 */
	public boolean containsValue(Object value);

	/**
	 * Returns set view of associations.
	 * 
	 * @return set view of associations
	 */
	public Set<Map.Entry<K, V>> entrySet();

	/**
	 * Returns value corresponding to k.
	 * 
	 * @param key
	 *            association being searched
	 * @return value corresponding to k, or null if none
	 */
	public V get(Object key);

	/**
	 * Returns true if no associations present.
	 * 
	 * @return true if no associations present
	 */
	public boolean isEmpty();

	/**
	 * Returns set view of keys.
	 * 
	 * @return set view of keys
	 */
	public Set<K> keySet();

	/**
	 * Add or replace association.
	 * 
	 * @param key
	 *            association being created
	 * @param value
	 *            value associated to key
	 * @return old value, or null if none
	 */
	public V put(K key, V value);

	/**
	 * Add each key value association.
	 * 
	 * @param m
	 *            contains associations being added
	 */
	public void putAll(Map<? extends K, ? extends V> m);

	/**
	 * Removes association, if any, given key.
	 * 
	 * @param key
	 *            association being removed
	 * @return value associated, or null if none
	 */
	public V remove(Object key);

	/**
	 * Returns number of associations.
	 * 
	 * @return number of associations
	 */
	public int size();

	/**
	 * Returns collection view of values.
	 * 
	 * @return collection view of values
	 */
	public Collection<V> values();

}

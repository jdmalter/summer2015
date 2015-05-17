/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

/**
 * Associates keys to values with entries. Cannot contain duplicate keys. Each
 * key contains one value.
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
	 * Removes all entries from a collection.
	 */
	void clear();

	/**
	 * Tests whether or not the parameter key exists in this map.
	 * 
	 * @param k
	 *            key value used to access data
	 * @return true if key belongs to map, false otherwise
	 */
	boolean containsKey(K k);

	/**
	 * Tests whether or not the parameter value exists in this map.
	 * 
	 * @param v
	 *            object data stored in the map
	 * @return true if value belongs to map, false otherwise
	 */
	boolean containsValue(V v);

	/**
	 * Equivalence relation must be reflexive (a,a), symmetric (a,b)-->(b,a),
	 * and transitive ((a,b)^(b,c))-->(a,c).
	 * 
	 * Tests if keys, values, and mapping is similar.
	 * 
	 * @param obj
	 *            Another map compared to map
	 * @return true if equal, false otherwise
	 */
	boolean equals(Object obj);

	/**
	 * Returns the data stored at the parameter key
	 * 
	 * @param k
	 *            key value used to access data
	 * @return object data stored behind given key
	 */
	V get(K k);

	/**
	 * Generates a hashcode for a map
	 * 
	 * @return int hashcode
	 */
	int hashCode();

	/**
	 * Checks a map for entries
	 * 
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();

	/**
	 * Converts the keys in the map into a set since keys cannot repeat.
	 * 
	 * @return set of keys
	 */
	Set<K> keys();

	/**
	 * Creates an entry in the map with the parameter key and value being
	 * associated. If the key already exists, its value is overridden by the new
	 * value.
	 * 
	 * @param k
	 *            key value used to access data
	 * @param v
	 *            object data stored in the map
	 * @return previous value associated with this key or null if it did not
	 *         exist
	 */
	V put(K k, V v);

	/**
	 * Creates multiple entries in the map using the parameter map. If the key
	 * already exists, its value is overridden by the new value.
	 * 
	 * @param m
	 *            map with keys and values
	 * @return true if all entries were added successfully, false otherwise
	 */
	boolean putAll(Map<? extends K, ? extends V> m);

	/**
	 * Remove key and its associated value from the map.
	 * 
	 * @param k
	 *            key removed from map
	 * @return value associated with removed key
	 */
	V remove(K k);

	/**
	 * Removes all keys of map parameter from the map.
	 * 
	 * @param m
	 *            map of keys removed from map
	 * @return true if successful, false otherwise
	 */
	boolean removeAll(Map<? extends K, ? extends V> m);

	/**
	 * Retains all keys of map parameter to map and removes other keys.
	 * 
	 * @param m
	 *            map of keys retained from map
	 * @return true if successful, false otherwise
	 */
	boolean retainAll(Map<? extends K, ? extends V> m);

	/**
	 * Returns the number of entries in the map.
	 * 
	 * @return number of elements
	 */
	int size();

	/**
	 * Translates a map into an array of entries.
	 * 
	 * @return an array containing all the entries
	 */
	Entry<K, V>[] toEntryArray();

	/**
	 * Converts the values in the map into a collection since values can repeat.
	 * 
	 * @return collection of values
	 */
	Collection<V> values();

	/**
	 * A single association between a key and value.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <K>
	 *            key value used to access map
	 * @param <V>
	 *            object data stored within map
	 */
	interface Entry<K, V> {

		/**
		 * Equivalence relation must be reflexive (a,a), symmetric
		 * (a,b)-->(b,a), and transitive ((a,b)^(b,c))-->(a,c).
		 * 
		 * Tests if entry is similar.
		 * 
		 * @param obj
		 *            Another entry compared to entry
		 * @return true if equal, false otherwise
		 */
		boolean equals(Object obj);

		/**
		 * Returns key of this entry.
		 * 
		 * @return key of this entry
		 */
		K getKey();

		/**
		 * Returns value of this entry.
		 * 
		 * @return value of this entry
		 */
		V getValue();

		/**
		 * Generates a hashcode for an entry.
		 * 
		 * @return int hashcode
		 */
		int hashcode();

		/**
		 * Sets the value of this entry to parameter value.
		 * 
		 * @param v
		 *            value that this entry is accepting
		 * @return previous value of this entry
		 */
		V setValue(V v);

	}

}
/**
 * Illustrates a java library class from item 29: consider type-safe heterogeneous containers.
 */
package heterogeneouscontainers;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores and retrieves a "favorite" instance of any class with the class as the
 * key.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 * 
 */
public class Favorites {
	private Map<Class<?>, Object> favorites = new HashMap<Class<?>, Object>();

	/**
	 * Stores a favorite instance with its class as key.
	 * 
	 * @param type
	 *            key
	 * @param instance
	 *            value
	 * @param <T>
	 *            type of class being stored
	 */
	public <T> void putFavorite(Class<T> type, T instance) {
		if (type == null)
			throw new NullPointerException("Type is null");
		favorites.put(type, type.cast(instance));
	}

	/**
	 * Retrieves instance from map.
	 * 
	 * @param type
	 *            key
	 * @param <T>
	 *            type of class being retrieved
	 * @return value
	 */
	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}

}

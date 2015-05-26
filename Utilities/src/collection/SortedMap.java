package collection;

/**
 * A map whose keys are a sorted set.
 * 
 * @author Jacob Malter
 *
 * @param <K>
 *            key value used to access data
 * @param <V>
 *            object data stored in the map
 */
public interface SortedMap<K, V> extends Map<K, V>, Sorted<K> {

	@Override
	SortedSet<K> keys();

}

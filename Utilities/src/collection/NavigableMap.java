package collection;

/**
 * A map whose keys are a naviable set.
 * 
 * @author Jacob Malter
 *
 * @param <K>
 *            key value used to access data
 * @param <V>
 *            object data stored in the map
 */
public interface NavigableMap<K, V> extends Map<K, V> {

	@Override
	NavigableSet<K> keys();

}

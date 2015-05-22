package declarations;

import java.util.ArrayList;
import java.util.List;

/**
 * A class with an integer identifier and an object identifier.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *            type of value stored in cell
 */
public class Cell<T> {
	private final int id;
	private final T value;

	private static List<Object> values = new ArrayList<Object>();
	private static int count;

	private static synchronized int nextId() {
		return count++;
	}

	private static synchronized boolean addValue(Object value) {
		return values.add(value);
	}

	/**
	 * Constructs a Cell given a value.
	 * 
	 * This implementation generates an id based on {@code count++}.
	 * 
	 * @param value
	 *            object value of object
	 * @throws IllegalArgumentException
	 *             value unable to be added to values list
	 */
	public Cell(T value) {
		this.value = value;
		this.id = nextId();

		if (!addValue(value))
			throw new IllegalArgumentException("Not added to values: " + value);
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value
	 */
	public T value() {
		return value;
	}

	/**
	 * Returns the id.
	 * 
	 * @return the id
	 */
	public int id() {
		return id;
	}

	/**
	 * Returns the count.
	 * 
	 * This implementation is synchronized to return a static field.
	 * 
	 * @return the count.
	 */
	public static synchronized int count() {
		return count;
	}

	/**
	 * Returns the values.
	 * 
	 * This implementation is synchronized to return a static field.
	 * 
	 * @return the values.
	 */
	public static synchronized List<Object> values() {
		return values;
	}

}

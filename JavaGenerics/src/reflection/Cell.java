package reflection;

/**
 * A sample class.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            object type stored in the class
 */
public class Cell<E> {
	private E value;

	/**
	 * Constructs a new cell given a value.
	 * 
	 * @param value
	 *            object stored in cell
	 */
	public Cell(E value) {
		this.value = value;
	}

	/**
	 * Returns the value.
	 * 
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Sets the value to given object.
	 * 
	 * @param value
	 *            object being set.
	 */
	public void setValue(E value) {
		this.value = value;
	}

	/**
	 * Copies given cell and returns it.
	 * 
	 * @param cell
	 *            cell being copied
	 * @param <T>
	 *            object type stored in cell
	 * @return copied cell
	 */
	public static <T> Cell<T> copy(Cell<T> cell) {
		return new Cell<T>(cell.getValue());
	}

}

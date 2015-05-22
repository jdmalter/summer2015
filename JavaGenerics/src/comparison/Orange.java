package comparison;

/**
 * An immutable orange.
 * 
 * @author Jacob Malter
 *
 */
public final class Orange extends Fruit {

	/**
	 * Constructs an orange given size.
	 * 
	 * This implementation provides a string name to its superclass constructor.
	 * 
	 * @param size
	 *            size of orange
	 */
	protected Orange(int size) {
		super("Orange", size);
	}
}

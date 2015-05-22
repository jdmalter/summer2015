package comparison;

/**
 * An immutable apple.
 * 
 * @author Jacob Malter
 *
 */
public final class Apple extends Fruit {

	/**
	 * Constructs an apple given size.
	 * 
	 * This implementation provides a string name to its superclass constructor.
	 * 
	 * @param size
	 *            size of apple
	 */
	protected Apple(int size) {
		super("Apple", size);
	}

}

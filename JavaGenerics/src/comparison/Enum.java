package comparison;

/**
 * A basic enum class.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            type of object in enum
 */
public abstract class Enum<E extends Enum<E>> implements Comparable<E> {
	private final String name;
	private final int ordinal;

	/**
	 * Constructs a enum instance.
	 * 
	 * @param name
	 *            name of instance
	 * @param ordinal
	 *            number of other instances before it plus one
	 */
	protected Enum(String name, int ordinal) {
		this.name = name;
		this.ordinal = ordinal;
	}

	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public final String name() {
		return name;
	}

	/**
	 * Returns the ordinal.
	 * 
	 * @return the ordinal
	 */
	public final int ordinal() {
		return ordinal;
	}

	/**
	 * This implementation matches {@code name()} but can be overridden.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * This implementation compares ordinal fields and returns -1, 0, or 1 only.
	 * Can be overridden to check other fields.
	 */
	@Override
	public final int compareTo(E o) {
		return ordinal < o.ordinal() ? -1 : ordinal == o.ordinal() ? 0 : 1;
	}

}

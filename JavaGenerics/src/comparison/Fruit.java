package comparison;

/**
 * A piece of generic fruit.
 * 
 * This class is intended to be sub-classed by other types of fruit.
 * 
 * @author Jacob Malter
 *
 */
public abstract class Fruit implements Comparable<Fruit> {
	private final String name;
	private final int size;

	/**
	 * Constructs a Fruit object given name and size.
	 * 
	 * @param name
	 *            name of fruit
	 * @param size
	 *            size of fruit
	 */
	protected Fruit(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Fruit))
			return false;
		Fruit other = (Fruit) obj;
		return name.equals(other.name) && size == other.size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name == null ? name.hashCode() : 0;
		result = prime * result + size;
		return result;
	}

	@Override
	public int compareTo(Fruit that) {
		return size < that.size ? -1 : size == that.size ? 0 : 1;
	}

}

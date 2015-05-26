package collectioninterface;

/**
 * An abstract task that can be compared to other tasks.
 * 
 * @author Jacob Malter
 *
 */
public abstract class Task implements Comparable<Task> {

	/**
	 * Protected constructor to prevent instantiation.
	 */
	protected Task() {
	}

	@Override
	public int compareTo(Task t) {
		return toString().compareTo(t.toString());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Task))
			return false;
		return toString().equals(o.toString());
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	/**
	 * Does not override with annotations. Intended to be overridden.
	 * 
	 * @return string representation
	 */
	public abstract String toString();

}

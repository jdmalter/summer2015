package collection;

/**
 * An empty task.
 * 
 * @author Jacob Malter
 *
 */
public final class EmptyTask extends Task {

	/**
	 * Constructs an EmptyTask and nothing more.
	 */
	public EmptyTask() {
	}

	/**
	 * Returns "" every time.
	 * 
	 * @return ""
	 */
	@Override
	public String toString() {
		return "";
	}

}

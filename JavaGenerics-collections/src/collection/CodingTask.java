package collection;

/**
 * A coding task which contains one string specification.
 * 
 * @author Jacob Malter
 *
 */
public final class CodingTask extends Task {
	private final String spec;

	/**
	 * Constructs a CodingTask given a spec.
	 * 
	 * @param spec
	 *            given specification
	 */
	public CodingTask(String spec) {
		this.spec = spec;
	}

	/**
	 * Returns the spec.
	 * 
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * String returned starts with "code " which is followed by a single space.
	 * Immediately after, the spec follows.
	 * 
	 * @return string representation of CodingTask
	 */
	@Override
	public String toString() {
		return "code " + spec;
	}

}

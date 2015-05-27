package collection;

/**
 * A phone task which contains one name and one number.
 * 
 * @author Jacob Malter
 *
 */
public final class PhoneTask extends Task {
	private final String name;
	private final String number;

	/**
	 * Constructs a PhoneTask given name and number.
	 * 
	 * @param name
	 *            name associated with number
	 * @param number
	 *            phone number
	 */
	public PhoneTask(String name, String number) {
		this.name = name;
		this.number = number;
	}

	/**
	 * Returns the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the number.
	 * 
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * String returned starts with "phone " which is followed by a single space.
	 * Immediately after, the name follows.
	 * 
	 * @return string representation of PhoneTask
	 */
	@Override
	public String toString() {
		return "phone " + name;
	}

}

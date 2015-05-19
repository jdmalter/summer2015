/**
 * Illustrates overriding object methods in items 8/9/10/11/12: obey the general 
 * contract when overriding equals/always override hashcode when you override
 * equals/always override toString/override clone judiciously/consider
 * implementing comparable.
 */
package equalshashcode;

/**
 * Phone number with area code, prefix, and line number. Overrides equal,
 * hashcode, and toString methods.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class PhoneNumber implements Comparable<PhoneNumber> {

	private final short areaCode;
	private final short prefix;
	private final short lineNumber;

	private volatile int hashCode;

	/**
	 * Constructs a PhoneNumber object with area code, prefix, and line number
	 * numbers.
	 * 
	 * @param areaCode
	 *            three digit area code (or less)
	 * @param prefix
	 *            three digit prefix (or less)
	 * @param lineNumber
	 *            four digit line number (or less)
	 */
	public PhoneNumber(int areaCode, int prefix, int lineNumber) {
		rangeCheck(areaCode, 999, "area code");
		rangeCheck(prefix, 999, "prefix");
		rangeCheck(lineNumber, 9999, "line number");
		this.areaCode = (short) areaCode;
		this.prefix = (short) prefix;
		this.lineNumber = (short) lineNumber;
	}

	/**
	 * Checks whether or not an object belong within a range between 0 and max
	 * parameter. Throws an exception if not within the range.
	 * 
	 * @param arg
	 *            item being examined
	 * @param max
	 *            maximum number in range
	 * @param name
	 *            not sure why this is here, but it is
	 */
	private static void rangeCheck(int arg, int max, String name) {
		if (arg < 0 || arg > max)
			throw new IllegalArgumentException(name + ": " + arg);
	}

	/**
	 * Returns the areaCode.
	 * 
	 * @return the areaCode
	 */
	public short getAreaCode() {
		return areaCode;
	}

	/**
	 * Returns the prefix.
	 * 
	 * @return the prefix
	 */
	public short getPrefix() {
		return prefix;
	}

	/**
	 * Returns the lineNumber.
	 * 
	 * @return the lineNumber
	 */
	public short getLineNumber() {
		return lineNumber;
	}

	/**
	 * Satisfies equal contract with reflexive, symmetric, transitive, and
	 * "non-nullity".
	 * 
	 * @param obj
	 *            object being compared to
	 * @return true if logically equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PhoneNumber))
			return false;
		PhoneNumber other = (PhoneNumber) obj;
		return other.lineNumber == lineNumber && other.prefix == prefix
				&& other.areaCode == areaCode;
	}

	/**
	 * Creates a hashcode representation of this object. This implementation
	 * only computes the hashcode if it is not already set since this object is
	 * immutable and therefore has constant hashcode returns.
	 * 
	 * @return int hashcode
	 */
	@Override
	public int hashCode() {
		int result = hashCode;
		if (result == 0) {
			final int prime = 31;
			result = 1;
			result = prime * result + areaCode;
			result = prime * result + prefix;
			result = prime * result + lineNumber;
			hashCode = result;
		}
		return result;
	}

	/**
	 * Return a string representation of this phone number. The string consist
	 * of fourteen characters whose format is "(XXX) YYY-ZZZZ", where XXX is the
	 * area code, YYY is the prefix, and ZZZZ is the line number. (Each of the
	 * capital letters represents a single decimal digit.)
	 * 
	 * If any of the three parts of this phone number is too small to fill up
	 * its field, the field is padded with leading zeros. For example, if the
	 * value of the line number is 123, the last four characters of the string
	 * representation will be "0123".
	 * 
	 * Note that there is a single space separating the closing parenthesis
	 * after the area code from the first digit of the prefix.
	 * 
	 * @return string representation of this phone number.
	 */
	@Override
	public String toString() {
		return String.format("(%03d) %03d-%04d", areaCode, prefix, lineNumber);
	}

	/**
	 * Creates a clone with super class clone method.
	 * 
	 * @return PhoneNumber clone of object
	 */
	@Override
	public PhoneNumber clone() {
		try {
			return (PhoneNumber) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); // Can't happen
		}
	}

	/**
	 * Compares two objects for natural ordering. This implementation relies on
	 * non-negative fields with a difference less than Integer.MAX_VALUE (2^31
	 * -1).
	 * 
	 * @param other
	 *            other instance of PhoneNumber being compared to
	 * @return negative, zero, or positive integer if invoking instance is less
	 *         than, equal to, or greater than other instance
	 */
	public int compareTo(PhoneNumber other) {
		// compare area codes
		int areaCodeDiff = areaCode - other.areaCode;
		if (areaCodeDiff != 0)
			return areaCodeDiff;

		// Area codes are equal, compare prefixes
		int prefixDiff = prefix - other.prefix;
		if (prefixDiff != 0)
			return prefixDiff;

		// Area codes and prefixes are equal, compare line numbers
		return lineNumber - other.lineNumber;
	}

}

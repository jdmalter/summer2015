/**
 * Illustrates the strategy design pattern from item 21: use function objects
 * to represent strategies.
 */
package strategypattern;

/**
 * Class that contains a comparator for string.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Host {

	/**
	 * Class that contains one method for comparisons.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private static class StringLengthComparator implements Comparator<String> {
		@Override
		public int compare(String s1, String s2) {
			return s1.length() - s2.length();
		}
	}

	/** Singleton instance of comparator to prevent unnecessary object creation. */
	public static final Comparator<String> STRING_LENGTH_COMPARATOR = new StringLengthComparator();

}
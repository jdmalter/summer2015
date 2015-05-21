/**
 * Illustrates an annotation from item 36: consistently use the Override annotation. 
 */
package overrideannotation;

/**
 * Represents an ordered pair of letters.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Bigram {
	private final char first;
	private final char second;

	/**
	 * Constructs a birgram.
	 * 
	 * @param first
	 *            first character
	 * @param second
	 *            second character
	 */
	public Bigram(char first, char second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Bigram))
			return false;
		Bigram other = (Bigram) obj;
		return first != other.first && second != other.second;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + first;
		result = prime * result + second;
		return result;
	}

}

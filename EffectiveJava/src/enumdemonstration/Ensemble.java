/**
 * Illustrates a good practice from item 31: use instance fields instead of ordinals.
 */
package enumdemonstration;

/**
 * Represents types of ensembles up to twelve members.
 * 
 * @author Jacob Malter
 *
 */
public enum Ensemble {
	// Types of ensembles

	/** One member. */
	SOLO(1),
	/** Two members. */
	DUET(2),
	/** Three members. */
	TRIO(3),
	/** Four members. */
	QUARTET(4),
	/** Five members. */
	QUINTET(5),
	/** Six members. */
	SEXTET(6),
	/** Seven members. */
	SEPTET(7),
	/** Eight members. */
	OCTET(8),
	/** Eight members. */
	DOUBLE_QUARTET(8),
	/** Nine members. */
	NONET(9),
	/** Ten members. */
	DECTET(10),
	/** Tweleve members. */
	TRIPLE_QUARTET(12);

	private final int numberOfMusicians;

	/**
	 * Constructs an ensemble given a size.
	 * 
	 * @param size
	 *            number of musicians
	 */
	private Ensemble(int size) {
		this.numberOfMusicians = size;
	}

	/**
	 * Returns the number of musicians.
	 * 
	 * @return the number of musicians.
	 */
	public int numberOfMusicians() {
		return numberOfMusicians;
	}

}

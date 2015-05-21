/**
 * Illustrates defensive programming from item 39: make defensive copies when needed.
 */
package defensivecopies;

import java.sql.Date;

/**
 * A period of time with a start and end.
 * 
 * @author Jacob Malter
 *
 */
public final class Period {
	private final Date start;
	private final Date end;

	/**
	 * Constructs a period object.
	 * 
	 * @param start
	 *            the beginning of the period
	 * @param end
	 *            the end of the period; must not precede start
	 * @throws IllegalArgumentException
	 *             if start is after end
	 * @throws NullPointerException
	 *             if start or end is null
	 */
	public Period(Date start, Date end) {
		this.start = new Date(start.getTime());
		this.end = new Date(end.getTime());

		if (this.start.compareTo(this.end) > 0)
			throw new IllegalArgumentException(start + " after " + end);
	}

	/**
	 * Returns the start date.
	 * 
	 * @return the start date
	 */
	public Date start() {
		return new Date(start.getTime());
	}

	/**
	 * Returns the end date.
	 * 
	 * @return the end date
	 */
	public Date end() {
		return new Date(end.getTime());
	}

}

/**
 * Illustrates defensive programming from item 39/76: make defensive copies
 * when needed/write readObject methods defensively.
 */
package defensivecopies;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;

/**
 * A period of time with a start and end.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public final class Period implements Serializable {
	private static final long serialVersionUID = 4025455619631294981L;
	private transient Date start;
	private transient Date end;

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

	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();

		// Defensively copy our mutable components
		start = new Date(start.getTime());
		end = new Date(end.getTime());

		// Check that our invariants are satisfied
		if (start.compareTo(end) > 0)
			throw new InvalidObjectException(start + " after " + end);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();

		// Serialize fields defensively
		s.writeObject(start());
		s.writeObject(end());
	}

}

/**
 * Illustrates good form for serialization from item 75: consider using a custom serialized form.
 */
package serializing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * StringList with a reasonable custom serialized form.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public final class StringList implements Serializable {
	private static final long serialVersionUID = -6505598025020501565L;
	private transient int size = 0;
	private transient Entry head = null;

	/**
	 * Singly linked node. No longer serializable!
	 * 
	 * @author Jacob Malter
	 *
	 */
	private static class Entry {
		String data;
		Entry next;
	}

	/**
	 * Appends the specified string to the list. Throws an exception if an
	 * attempt is made to add a null parameter.
	 * 
	 * This method is an example and placeholder. It does nothing. Deal with it.
	 * 
	 * @param s
	 *            string being appended.
	 * @throws IllegalArgumentException
	 *             if parameter is null
	 */
	public final void add(String s) {
		// Adds objects.
	}

	/**
	 * Serialize this {@code StringList} instance.
	 * 
	 * @param s
	 *            stream being written into
	 * @throws IOException
	 *             if exception occurs on outputstream
	 * 
	 * @serialData This size of this list (the number of strings it contains) is
	 *             emitted ({@code int}), followed by all of its elements (each
	 *             a {@code String}), in the proper sequence.
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		s.writeInt(size);

		// Write out all elements in the proper order.
		for (Entry e = head; e != null; e = e.next)
			s.writeObject(e.data);
	}

	/**
	 * Deserialize this {@code StringList} instance.
	 * 
	 * @param s
	 *            stream being read from
	 * @throws IOException
	 *             if exception occurs on inputstream
	 * @throws ClassNotFoundException
	 *             if class can't be loaded by name
	 */
	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();
		int numElements = s.readInt();

		// Read in all elements and insert them in list
		for (int i = 0; i < numElements; i++)
			add((String) s.readObject());
	}

}

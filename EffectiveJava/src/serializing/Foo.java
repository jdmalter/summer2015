/**
 * Illustrates a serializable hierarchy from item 74: implement serializable judiciously.
 */
package serializing;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Example subclass designed for inheritance and serializiation.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Foo extends AbstractFoo implements Serializable {
	private void readObject(ObjectInputStream s) throws IOException,
			ClassNotFoundException {
		s.defaultReadObject();

		// Manually deserialize and initialize superclass state
		int x = s.readInt();
		int y = s.readInt();
		initialize(x, y);
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();

		// Manually serialize superclass state
		s.writeInt(getX());
		s.writeInt(getY());
	}

	/**
	 * Constructs a Foo object given its two fields.
	 * 
	 * @param x
	 *            first field
	 * @param y
	 *            second field
	 */
	public Foo(int x, int y) {
		super(x, y);
	}

	private static final long serialVersionUID = 221528547649127292L;

}

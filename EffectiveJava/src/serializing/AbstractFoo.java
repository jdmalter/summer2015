/**
 * Illustrates a serializable hierarchy from item 74: implement serializable judiciously.
 */
package serializing;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Example superclass designed for inheritance and serializiation.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public abstract class AbstractFoo {
	private int x, y; // state

	/**
	 * Enum works with field to track initialization.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private enum State {
		NEW, INITIALIZING, INITIALIZED
	};

	private final AtomicReference<State> init = new AtomicReference<State>(
			State.NEW);

	/**
	 * Constructs an AbstractFoo object given its two fields.
	 * 
	 * @param x
	 *            first field
	 * @param y
	 *            second field
	 */
	public AbstractFoo(int x, int y) {
		initialize(x, y);
	}

	/**
	 * This constructor and the following method allow subclass's readObject
	 * method to initialized our state.
	 */
	protected AbstractFoo() {
	}

	/**
	 * Properly creates object with state changes.
	 * 
	 * @param x
	 *            first field
	 * @param y
	 *            second field
	 */
	protected final void initialize(int x, int y) {
		if (!init.compareAndSet(State.NEW, State.INITIALIZING))
			throw new IllegalStateException("Already initialized");
		this.x = x;
		this.y = y;
		init.set(State.INITIALIZED);
	}

	// These methods provide access to internal state so it can be manually
	// serialized by subclass's writeObject method.

	/**
	 * Returns the x field.
	 * 
	 * @return the x field
	 */
	protected final int getX() {
		checkInit();
		return x;
	}

	/**
	 * Returns the y field.
	 * 
	 * @return the y field
	 */
	protected final int getY() {
		checkInit();
		return y;
	}

	/**
	 * Checks if this object is fully initialized.
	 * 
	 * @throws IllegalStateException
	 *             if object is not initialized
	 */
	private void checkInit() {
		if (init.get() != State.INITIALIZED)
			throw new IllegalStateException("Uninitialized");
	}

}

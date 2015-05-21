/**
 * Illustrates a class hierarchy replacement of a tagged class from item 20:
 * prefer class hierarchies to tagged classes.
 */
package inheritanceovertagging;

/**
 * A specific, immutable implementation of the figure contract for a square that
 * extends a rectangle.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public final class Square extends Rectangle {

	/**
	 * Creates a new square with side length.
	 * 
	 * @param side
	 *            horizontal and vertical components
	 */
	public Square(double side) {
		super(side, side);
	}

}

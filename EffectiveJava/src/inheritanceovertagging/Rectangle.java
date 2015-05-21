/**
 * Illustrates a class hierarchy replacement of a tagged class from item 20:
 * prefer class hierarchies to tagged classes.
 */
package inheritanceovertagging;

/**
 * A specific, immutable implementation of the figure contract for a rectangle.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class Rectangle extends Figure {
	final double length;
	final double width;

	private volatile double area;

	/**
	 * Creates a new rectangle figure with length and width.
	 * 
	 * @param length
	 *            horizontal component
	 * @param width
	 *            vertical component
	 */
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	/**
	 * Returns the length.
	 * 
	 * @return the length
	 */
	public double length() {
		return length;
	}

	/**
	 * Returns the width.
	 * 
	 * @return the width
	 */
	public double width() {
		return width;
	}

	@Override
	public double area() {
		if (area == 0)
			area = findArea(length(), width());
		return area;
	}

	/**
	 * Calculates area given a length and width.
	 * 
	 * @param length
	 *            horizontal component
	 * @param width
	 *            vertical component
	 * @return the area
	 */
	private double findArea(double length, double width) {
		return length * width;
	}

}
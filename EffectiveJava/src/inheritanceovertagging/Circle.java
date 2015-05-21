/**
 * Illustrates a class hierarchy replacement of a tagged class from item 20:
 * prefer class hierarchies to tagged classes.
 */
package inheritanceovertagging;

/**
 * A specific, immutable implementation of the figure contract for a circle.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public final class Circle extends Figure {
	final double radius;

	private volatile double area;

	/**
	 * Creates a new circle figure with radius.
	 * 
	 * @param radius
	 *            half of the distance between two parallel sides of the circle
	 */
	public Circle(double radius) {
		this.radius = radius;
	}

	/**
	 * Returns the radius.
	 * 
	 * @return the radius
	 */
	public double radius() {
		return radius;
	}

	@Override
	public double area() {
		if (area == 0)
			area = findArea(radius());
		return area;
	}

	/**
	 * Calculates area given a radius.
	 * 
	 * @param radius
	 *            half of the distance between two parallel sides of the circle
	 * @return the area
	 */
	private double findArea(double radius) {
		return Math.PI * radius * radius;
	}

}
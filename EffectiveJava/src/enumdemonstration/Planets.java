/**
 * Illustrates a new enum class from item 30: use enums instead of int constants.
 */
package enumdemonstration;

/**
 * Enum type with data and behavior representing planets.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public enum Planets {
	// Planets in the solar system.

	/** First planet. */
	MERCURY(3.302e+23, 2.439e6),
	/** Second planet. */
	VENUS(4.869e+24, 6.052e+6),
	/** Third planet and our home. */
	EARTH(5.975e+24, 6.37e+6),
	/** Fourth planet. */
	MARS(6.419e+23, 3.393e+6),
	/** Fifth planet. */
	JUPITER(1.899e+27, 7.149e+7),
	/** Sixth planet. */
	SATURN(5.685e+26, 6.027e+7),
	/** Seventh planet. */
	URANUS(8.683e+25, 2.556e+7),
	/** Eight planet. */
	NEPTUNE(1.024e+26, 2.477e+7);

	private final double mass; // in kg
	private final double radius; // in meters
	private final double surfaceGravity; // in m/s^2

	// Universal gravitational constant in m^3/(kg*s^2)
	private static final double G = 6.67300e-11;

	/**
	 * Constructs a planet and calculates surface gravity.
	 * 
	 * @param mass
	 *            mass in kg
	 * @param radius
	 *            radius in m
	 */
	Planets(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		this.surfaceGravity = G * mass / (radius * radius);
	}

	/**
	 * Returns the mass.
	 * 
	 * @return the mass.
	 */
	public double mass() {
		return mass;
	}

	/**
	 * Returns the radius.
	 * 
	 * @return the radius.
	 */
	public double radius() {
		return radius;
	}

	/**
	 * Returns the surface gravity.
	 * 
	 * @return the surface gravity
	 */
	public double surfaceGravity() {
		return surfaceGravity;
	}

	/**
	 * Calculates surface weight in newtons given mass.
	 * 
	 * @param mass
	 *            mass of object being weighed.
	 * @return weight as force in newtons
	 */
	public double surfaceWeight(double mass) {
		return mass * surfaceGravity(); // F=m*a
	}

}

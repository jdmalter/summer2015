/**
 * Illustrates a good practice from item 19: use interfaces only to define types.
 */
package staticimport;

/**
 * Non-instantiable utility class used to stored constants.
 * 
 * @author Jacob Malter
 *
 */
public class PhysicalConstants {

	/**
	 * Prevents instantiation.
	 */
	private PhysicalConstants() {
	}

	/** A count of atoms in a mol of substance. */
	public static final double AVOGADROS_NUMBER = 6.02214199e23;
	/** Something relating to energy for temperature? */
	public static final double BOLTZMANN_CONSTANT = 1.3806503e-23;
	/** Mass of an electron in kg. */
	public static final double ELECTRON_MASS = 9.10938188e-31;

}

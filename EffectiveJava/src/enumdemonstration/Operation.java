/**
 * Illustrates extendible enums from item 34: emulate extensible enums with interfaces.
 */
package enumdemonstration;

/**
 * Interfaces adds extensibility into implementing enum classes.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 */
public interface Operation {

	/**
	 * Performs an operation given two arguments.
	 * 
	 * @param x
	 *            first argument
	 * @param y
	 *            second argument
	 * @return the result
	 */
	double apply(double x, double y);

}

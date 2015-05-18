/**
 * Illustrates a class from item 4: enforce noninstantiability with a private constructor.
 */
package noninstantiableclass;

/**
 * Class that cannot be instantiated. Provides static methods and static fields.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class UtilityClass {

	/**
	 * Cannot be instantiaed.
	 */
	private UtilityClass() {
		throw new AssertionError();
	}

	// Add any static methods or fields

}

/**
 * Illustrates a good practice from item 19: use interfaces only to define types.
 */
package staticimport;

import static staticimport.PhysicalConstants.*;

/**
 * Uses the utility class.
 * 
 * @author Jacob Malter
 *
 */
public class Test {

	/**
	 * Returns the number of atoms given mols.
	 * 
	 * @param mols
	 *            number of mols of substance
	 * @return number of atoms given mols
	 */
	double atoms(double mols) {
		return AVOGADROS_NUMBER * mols;
	}
}

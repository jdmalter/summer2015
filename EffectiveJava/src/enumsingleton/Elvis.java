/**
 * Illustrates a design pattern from item 3: enforce the singleton property 
 * with a private constructor or an enum type.
 */
package enumsingleton;

import java.util.Arrays;

/**
 * Creates a single instance of Elvis.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public enum Elvis {
	/** Single instance of Elvis running around. */
	INSTANCE;

	private String[] favoriteSongs = { "Hound Dog", "Heartbreak Hotel" };

	/**
	 * Prints out favorite songs of this singleton instance.
	 */
	public void printFavorites() {
		System.out.println(Arrays.toString(favoriteSongs));
	}

	/**
	 * Does something?
	 */
	public void leaveTheBuilding() {
		// Still not sure what goes here...but it does something.
	}

}

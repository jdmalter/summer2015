/**
 * Illustrates a class hierarchy replacement of a tagged class from item 20:
 * prefer class hierarchies to tagged classes.
 */
package inheritanceovertagging;

/**
 * A general figure contract intended to be built upon.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public abstract class Figure {

	/**
	 * Returns area of a figure based on its properties.
	 * 
	 * @return area of a figure.
	 */
	abstract double area();

}
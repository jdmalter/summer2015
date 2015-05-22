package comparison;

/**
 * Specifies the natural ordering of objects.
 * 
 * @author Jacob Malter
 *
 * @param <T>
 *            type being compared
 */
public interface Comparator<T> {

	/**
	 * Returns an integer comparison between two objects.
	 * 
	 * Overriding methods must be partially ordered sets. Comparisons must be
	 * reflexive: {@code sgn(compare(x,x)) == 0} must return true. Comparisons
	 * must be antisymmetric: {@code sgn(compare(x,y)) == 1} implies
	 * {@code sgn(compare(y,x)) == -1} and vice versa. Comparisons must be
	 * transitive: {@code sgn(compare(x,y)) == 1 && sgn(compare(y,z)) == 1}
	 * implies {@code sgn(compare(x,y)) == 1}.
	 * 
	 * Regardless of consistency with the equals method, an implementation
	 * should follow this contract. Any implementation consistent with equals
	 * which returns {@code compare(x,y) == 0} implies
	 * {@code x.equals(y) == true}.
	 * 
	 * If null is passed as a parameter, a NullPointerException will be thrown.
	 * 
	 * @param o1
	 *            first object being compared
	 * @param o2
	 *            second object being compared
	 * @return a negative, zero, or positive integer if the first object is less
	 *         than, equal to, or greater than the second object
	 */
	public int compare(T o1, T o2);

	/**
	 * Tests two objects for equality.
	 * 
	 * Overriding methods must be equivalence relations. Firstly, any comparison
	 * with null returns false: {@code x.equals(null) == false}. Comparisons
	 * must be reflexive: {@code x.equals(x) == true}. Comparisons must be
	 * symmetric: {@code x.equals(y) == true} implies
	 * {@code y.equals(x) == true}. Comparisons must be transitive:
	 * {@code x.equals(y) == true && y.equals(z) == true} implies
	 * {@code x.equals(z) == true}.
	 * 
	 * @param obj
	 *            other object
	 * @return true if logically equal, false otherwise
	 */
	public boolean equals(Object obj);

}

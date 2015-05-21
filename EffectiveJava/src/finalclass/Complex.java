/**
 * Illustrates a practice from item 15: minimize mutability.
 */
package finalclass;

/**
 * A complex number with real and imaginary numbers.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public final class Complex {

	// Mandatory fields provided

	private final double re;
	private final double im;

	// Convenience examples provided

	/** Zero without real or imaginary. */
	public static final Complex ZERO = new Complex(0, 0);
	/** One without imaginary. */
	public static final Complex ONE = new Complex(1, 0);
	/** I without real. */
	public static final Complex I = new Complex(0, 1);
	/** One and I. */
	public static final Complex ONE_PLUS_I = new Complex(1, 1);

	/**
	 * Constructs a complex number with real and imaginary parts.
	 * 
	 * @param re
	 *            real part
	 * @param im
	 *            imaginary part
	 */
	public Complex(double re, double im) {
		this.re = re;
		this.im = im;
	}

	// Static factories added to a constructor

	/**
	 * Returns a complex number with real and imaginary parts.
	 * 
	 * @param re
	 *            real part
	 * @param im
	 *            imaginary part
	 * @return new complex number
	 */
	public static Complex valueOf(double re, double im) {
		return new Complex(re, im);
	}

	/**
	 * Converts a polar coordinate into a complex number. The horizontal
	 * component of r becomes the real number, and the vertical component of r
	 * becomes the imaginary number.
	 * 
	 * @param r
	 *            radius
	 * @param theta
	 *            angle in radians
	 * @return new complex number
	 */
	public static Complex valueOfPolar(double r, double theta) {
		return new Complex(r * Math.cos(theta), r * Math.sin(theta));
	}

	// Accessor with no corresponding mutators

	/**
	 * Returns the real part.
	 * 
	 * @return the re
	 */
	public double realPart() {
		return re;
	}

	/**
	 * Returns the imaginary part.
	 * 
	 * @return the im
	 */
	public double imaginaryPart() {
		return im;
	}

	/**
	 * Adds the parameter complex number to the invoking complex number.
	 * 
	 * @param c
	 *            other operand
	 * @return the result
	 */
	public Complex add(Complex c) {
		return new Complex(re + c.re, im + c.im);
	}

	/**
	 * Subtracts the parameter complex number from the invoking complex number.
	 * 
	 * @param c
	 *            other operand
	 * @return the result
	 */
	public Complex subtract(Complex c) {
		return new Complex(re - c.re, im - c.im);
	}

	/**
	 * Multiplies the invoking complex number by the parameter complex number.
	 * 
	 * Not properly rounded and fail at bounds cases (zero and infinity).
	 * 
	 * @param c
	 *            other operand
	 * @return the result
	 */
	public Complex multiply(Complex c) {
		return new Complex(re * c.re - im * c.im, re * c.im + im * c.re);
	}

	/**
	 * Divides the invoking complex number by the parameter complex number.
	 * 
	 * Not properly rounded and fail at bounds cases (zero and infinity).
	 * 
	 * @param c
	 *            other operand
	 * @return the result
	 */
	public Complex divide(Complex c) {
		double temp = c.re * c.re + c.im * c.im;
		return new Complex((re * c.re + im * c.im) / temp, (im * c.re - re
				* c.im)
				/ temp);
	}

	/**
	 * Satisfies equal contract with reflexive, symmetric, transitive, and
	 * "non-nullity".
	 * 
	 * @param obj
	 *            object being compared to
	 * @return true if logically equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Complex))
			return false;
		Complex other = (Complex) obj;
		return Double.compare(re, other.re) == 0
				&& Double.compare(im, other.im) == 0;
	}

	/**
	 * Generates a hashcode representation of the object.
	 * 
	 * @return int hashcode
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hashDouble(re);
		result = prime * result + hashDouble(im);
		return result;
	}

	/**
	 * Generates a hashcode for a double value.
	 * 
	 * @param val
	 *            double being hashed
	 * @return int hashcode
	 */
	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(val);
		return (int) (longBits ^ (longBits >>> 32));
	}

	/**
	 * Returns a string representation of this complex number. The string
	 * contains six characters plus any characters created by the numbers. The
	 * format is "(R + Ii)" where "R" is a real number and "I" is an imaginary
	 * number.
	 * 
	 * There are single spaces before and after the "+".
	 * 
	 * @return string representation of this object
	 */
	@Override
	public String toString() {
		return "(" + re + " + " + im + "i)";
	}

}

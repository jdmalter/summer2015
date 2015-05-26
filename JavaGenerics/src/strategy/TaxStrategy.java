package strategy;

/**
 * Declares an interface for computing tax based on the type of person paying
 * tax.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *            type of person paying tax
 */
public interface TaxStrategy<P extends TaxPayer<P>> {

	/**
	 * Computes tax given a tax payer.
	 * 
	 * @param p
	 *            taxpayer
	 * @return the tax
	 */
	public long computeTax(P p);

}

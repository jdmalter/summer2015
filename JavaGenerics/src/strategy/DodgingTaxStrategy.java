package strategy;

/**
 * A tax strategy that invokes dodging a tax.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *            type of person paying tax
 */
public class DodgingTaxStrategy<P extends TaxPayer<P>> implements
		TaxStrategy<P> {

	@Override
	public long computeTax(P p) {
		return 0;
	}

}

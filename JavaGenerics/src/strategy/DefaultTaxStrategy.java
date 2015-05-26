package strategy;

/**
 * A default tax strategy that implements a default implementation.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 *            type of person paying tax
 */
public class DefaultTaxStrategy<P extends TaxPayer<P>> implements
		TaxStrategy<P> {
	private static final double RATE = 0.40;

	@Override
	public long computeTax(P p) {
		return Math.round(p.income() * RATE);
	}

}

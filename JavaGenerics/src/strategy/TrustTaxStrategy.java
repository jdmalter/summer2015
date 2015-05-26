package strategy;

/**
 * A tax strategy for trusts that relies on default tax strategy.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 */
public class TrustTaxStrategy extends DefaultTaxStrategy<Trust> {

	/**
	 * This implementation returns 0 if trust is a nonProfit and computes
	 * default tax otherwise.
	 */
	@Override
	public long computeTax(Trust trust) {
		return trust.isNonProfit() ? 0 : super.computeTax(trust);
	}

}

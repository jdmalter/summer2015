package strategy;

/**
 * A type of taxable income which could be nonProfit or forProfit.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 */
public final class Trust extends TaxPayer<Trust> {
	private boolean nonProfit;

	/**
	 * Constructs a trust given income and nonProfit status.
	 * 
	 * @param income
	 *            income in cents
	 * @param nonProfit
	 *            true if nonProfit, false otherwise (is forProfit)
	 * @param strategy
	 *            a tax strategy used to define implementation
	 */
	public Trust(long income, boolean nonProfit, TaxStrategy<Trust> strategy) {
		super(income, strategy);
		this.nonProfit = nonProfit;
	}

	/**
	 * Returns true if nonProfit is true, false otherwise.
	 * 
	 * @return true if nonProfit is true, false otherwise
	 */
	public boolean isNonProfit() {
		return nonProfit;
	}

	@Override
	protected Trust getThis() {
		return this;
	}

}

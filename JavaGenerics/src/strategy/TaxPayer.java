package strategy;

/**
 * A generic taxPayer which has income.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 * @param <P>
 */
public abstract class TaxPayer<P extends TaxPayer<P>> {
	/** Income in cents. */
	public long income;
	private TaxStrategy<P> strategy;

	/**
	 * Constructs a TaxPayer given income.
	 * 
	 * @param income
	 *            income in cents
	 * @param strategy
	 *            a tax strategy used to define implementation
	 */
	public TaxPayer(long income, TaxStrategy<P> strategy) {
		this.income = income;
		this.strategy = strategy;
	}

	/**
	 * Returns the income.
	 * 
	 * @return the income
	 */
	public long income() {
		return income;
	}

	/**
	 * Returns instance of self.
	 * 
	 * @return instance of self.
	 */
	protected abstract P getThis();

	/**
	 * Computes tax.
	 * 
	 * @return the tax
	 */
	public long computeTax() {
		return strategy.computeTax(getThis());
	}

}

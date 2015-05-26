package strategy;

/**
 * A person who has taxable income.
 * 
 * This class is a part of an example on the strategy pattern.
 * 
 * @author Jacob Malter
 *
 */
public final class Person extends TaxPayer<Person> {

	/**
	 * Constructs a person given income.
	 * 
	 * @param income
	 *            income in cents
	 * @param strategy
	 *            a tax strategy used to define implementation
	 */
	public Person(long income, TaxStrategy<Person> strategy) {
		super(income, strategy);
	}

	@Override
	protected Person getThis() {
		return this;
	}
}

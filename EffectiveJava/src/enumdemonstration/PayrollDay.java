/**
 * Illustrates a new enum class from item 30: use enums instead of int constants.
 */
package enumdemonstration;

/**
 * Represents payroll on a single day of the week for every day in the week.
 * Finds pay based on which day is used.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public enum PayrollDay {
	/** Weekday workday. */
	MONDAY(PayType.WEEKDAY),
	/** Weekday workday. */
	TUESDAY(PayType.WEEKDAY),
	/** Weekday workday. */
	WEDNESDAY(PayType.WEEKDAY),
	/** Weekday workday. */
	THURSDAY(PayType.WEEKDAY),
	/** Weekday workday. */
	FRIDAY(PayType.WEEKDAY),
	/** Weekend workday. */
	SATURDAY(PayType.WEEKEND),
	/** Weekend workday. */
	SUNDAY(PayType.WEEKEND);

	private final PayType payType;

	/**
	 * Constructs pay days based on pay type.
	 * 
	 * @param payType
	 *            weekday or weekend pay types
	 */
	private PayrollDay(PayType payType) {
		this.payType = payType;
	}

	/**
	 * Calculates pay with hours worked and pay rate based on the pay type.
	 * 
	 * @param hoursWorked
	 *            hours worked in a day
	 * @param payRate
	 *            wage given per hours worked
	 * @return daily wage
	 */
	double pay(double hoursWorked, double payRate) {
		return payType.pay(hoursWorked, payRate);
	}

	/**
	 * An enum type representing two types of pay periods: weekdays and
	 * weekends. Uses a strategy design pattern to provide different behavior
	 * when passed into PayrollDay constructor.
	 * 
	 * @author Jacob Malter
	 *
	 */
	private enum PayType {
		WEEKDAY {
			@Override
			double overtimePay(double hours, double payRate) {
				return hours <= HOURS_PER_SHIFT ? 0 : (hours - HOURS_PER_SHIFT)
						* payRate / 2;
			}
		},
		WEEKEND {
			@Override
			double overtimePay(double hours, double payRate) {
				return hours * payRate / 2;
			}
		};

		private static final int HOURS_PER_SHIFT = 8;

		/**
		 * Calculates overtime pay with hours worked and pay rate given the pay
		 * type.
		 * 
		 * @param hours
		 *            hours worked in a day
		 * @param payRate
		 *            wage given per hours worked
		 * @return overtime pay
		 */
		abstract double overtimePay(double hours, double payRate);

		/**
		 * Calculates pay with hours worked and pay rate given the pay type.
		 * 
		 * @param hoursWorked
		 *            hours worked in a day
		 * @param payRate
		 *            wage given per hours worked
		 * @return daily wage
		 */
		double pay(double hoursWorked, double payRate) {
			double basePay = hoursWorked * payRate;
			return basePay + overtimePay(hoursWorked, payRate);
		}

	}

}

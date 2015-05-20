/**
 * Illustrates a new enum class from item 30/34: use enums instead of int 
 * constants/emulate extensible enums with interfaces.
 */
package enumdemonstration;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents two extended calculation functions.
 * 
 * @author Jacob Malter
 *
 */
public enum ExtendedOperation implements Operation {
	/** Raises first argument to power of second argument. */
	EXP("^") {
		@Override
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	/** Takes modulus of first argument from second argument. */
	MOD("%") {
		@Override
		public double apply(double x, double y) {
			return x % y;
		}
	};

	private final String symbol;

	/**
	 * Constructs an operation with a symbol.
	 * 
	 * @param symbol
	 *            represents operation in string form.
	 */
	ExtendedOperation(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * Functions as a getter method for the symbol.
	 */
	@Override
	public String toString() {
		return symbol;
	}

	@Override
	public abstract double apply(double x, double y);

	// Implementing a fromString method on an enum type
	private static final Map<String, ExtendedOperation> STRING_TO_ENUM = new HashMap<String, ExtendedOperation>();
	static { // Initialized map from constant name to enum constant
		for (ExtendedOperation op : values()) {
			STRING_TO_ENUM.put(op.toString(), op);
		}
	}

	/**
	 * Returns Operation for String, or null if string is invalid.
	 * 
	 * @param symbol
	 *            string representation used as key
	 * @return Operation used as value
	 */
	public static ExtendedOperation fromString(String symbol) {
		return STRING_TO_ENUM.get(symbol);
	}

}

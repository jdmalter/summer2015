/**
 * Illustrates a new enum class from item 30/34: use enums instead of int 
 * constants/emulate extensible enums with interfaces.
 */
package enumdemonstration;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents four basic calculation functions.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public enum BasicOperation implements Operation {
	/** Adds second argument to first argument. */
	PLUS("+") {
		@Override
		public double apply(double x, double y) {
			return x + y;
		}
	},
	/** Subtracts second argument from first argument. */
	MINUS("-") {
		@Override
		public double apply(double x, double y) {
			return x - y;
		}
	},
	/** Mutliples first argument by second argument. */
	MULTIPLY("*") {
		@Override
		public double apply(double x, double y) {
			return x * y;
		}
	},
	/** Divides first argument by second argument. */
	DIVIDE("/") {
		@Override
		public double apply(double x, double y) {
			return x / y;
		}
	};

	private final String symbol;

	/**
	 * Constructs an operation with a symbol.
	 * 
	 * @param symbol
	 *            represents operation in string form.
	 */
	BasicOperation(String symbol) {
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
	private static final Map<String, BasicOperation> STRING_TO_ENUM = new HashMap<String, BasicOperation>();
	static { // Initialized map from constant name to enum constant
		for (BasicOperation op : values()) {
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
	public static BasicOperation fromString(String symbol) {
		return STRING_TO_ENUM.get(symbol);
	}

	/**
	 * Switch on an enum to simulate a missing method.
	 * 
	 * @param op
	 *            operation finding inverse for
	 * @return inverse operation of parameter
	 */
	public static BasicOperation inverse(BasicOperation op) {
		switch (op) {
		case PLUS:
			return BasicOperation.MINUS;
		case MINUS:
			return BasicOperation.PLUS;
		case MULTIPLY:
			return BasicOperation.DIVIDE;
		case DIVIDE:
			return BasicOperation.MULTIPLY;
		default:
			throw new AssertionError("Unknown op: " + op);
		}
	}

}

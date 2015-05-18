/**
 * Illustrates a design pattern from item 2: consider a builder when faced with
 * many constructor parameters.
 */
package builderpattern;

/**
 * Models a nutrition label with some required labels and some extra labels.
 * Builder pattern allows optional addition of labels with lengthy, confusing
 * constructor parameters. Creates a final, immutable object.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public class NutritionFacts {

	// All possible labels
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	/**
	 * Builds a nutrition facts object and accept inputs from user.
	 * 
	 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
	 *         Joshua Bloch
	 *
	 */
	public static class Builder {

		// Required parameters
		private int servingSize;
		private int servings;

		// Optional parameters
		private int calories = 0;
		private int fat = 0;
		private int sodium = 0;
		private int carbohydrate = 0;

		/**
		 * Creates a minimum builder with the required parameters and nothing
		 * more.
		 * 
		 * @param servingSize
		 *            how large a serving is
		 * @param servings
		 *            how many servings belong to the food with the label
		 */
		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		/**
		 * Sets calories to int parameter.
		 * 
		 * @param val
		 *            number of calories
		 * @return builder modified
		 */
		public Builder calories(int val) {
			calories = val;
			return this;
		}

		/**
		 * Sets fat to int parameter.
		 * 
		 * @param val
		 *            number of fat
		 * @return builder modified
		 */
		public Builder fat(int val) {
			fat = val;
			return this;
		}

		/**
		 * Sets sodium to int parameter.
		 * 
		 * @param val
		 *            number of sodium
		 * @return builder modified
		 */
		public Builder sodium(int val) {
			sodium = val;
			return this;
		}

		/**
		 * Sets carbohydrate to int parameter.
		 * 
		 * @param val
		 *            number of carbohydrate
		 * @return builder modified
		 */
		public Builder carbohydrate(int val) {
			carbohydrate = val;
			return this;
		}

		/**
		 * Creates a nutrition facts object with invoking builder object.
		 * 
		 * @return new NutritionFacts object
		 */
		public NutritionFacts build() {
			return new NutritionFacts(this);
		}

	}

	/**
	 * Cannot be instantiated by a client. Allows custom building through inner
	 * static class.
	 * 
	 * @param builder
	 *            builds object with information passed into builder
	 */
	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	/**
	 * Generates a string representation of a nutrition facts object.
	 * 
	 * @return a string representation
	 */
	public String toString() {
		return "NutritionFacts [servingSize=" + servingSize + ", servings="
				+ servings + ", calories=" + calories + ", fat=" + fat
				+ ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
	}

	/**
	 * Tests out class and label.
	 * 
	 * @param args
	 *            Command line arguments not used in this program.
	 */
	public static void main(String[] args) {

		// Tests out builder with multiple combinations of inputs.
		System.out.println(new NutritionFacts.Builder(10, 20).build()
				.toString());
		System.out.println(new NutritionFacts.Builder(10, 20).calories(30)
				.build().toString());
		System.out.println(new NutritionFacts.Builder(10, 20).fat(40).build()
				.toString());
		System.out.println(new NutritionFacts.Builder(10, 20).calories(30)
				.sodium(50).build().toString());
		System.out.println(new NutritionFacts.Builder(10, 20).calories(30)
				.sodium(50).carbohydrate(60).build().toString());
		System.out.println(new NutritionFacts.Builder(10, 20).calories(30)
				.fat(40).sodium(50).carbohydrate(60).build().toString());
	}
}
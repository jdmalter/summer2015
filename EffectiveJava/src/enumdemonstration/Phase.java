/**
 * Illustrates an EnumMap from item 33: use EnumMap instead of ordinal indexing.
 */
package enumdemonstration;

import java.util.EnumMap;
import java.util.Map;

/**
 * Represents separate phases of matter.
 * 
 * @author Jacob Malter based on content of Effective Java (2nd Edition) by
 *         Joshua Bloch
 *
 */
public enum Phase {
	// three phases

	/** Lowest energy. */
	SOLID,
	/** Middle energy. */
	LIQUID,
	/** Highest energy. */
	GAS;

	/**
	 * Represents phase transitions from one phase to another.
	 * 
	 * @author Jacob Malter
	 *
	 */
	public enum Transition {
		// nine transitions

		/** Melting a solid. */
		MELT(SOLID, LIQUID),
		/** Freezing a liquid. */
		FREEZE(LIQUID, SOLID),
		/** Boiling a liquid. */
		BOIL(LIQUID, GAS),
		/** Condensing a gas. */
		CONDENSE(GAS, LIQUID),
		/** Subliming a solid. */
		SUBLIME(SOLID, GAS),
		/** Deposing a gas. */
		DEPOSIT(GAS, SOLID);

		final Phase src;
		final Phase dst;

		/**
		 * Constructs a change in phase.
		 * 
		 * @param src
		 *            initial phase
		 * @param dst
		 *            final phase
		 */
		private Transition(Phase src, Phase dst) {
			this.src = src;
			this.dst = dst;
		}

		// Initialize phase transition map
		private static final Map<Phase, Map<Phase, Transition>> M = new EnumMap<Phase, Map<Phase, Transition>>(
				Phase.class);
		static {
			for (Phase p : Phase.values())
				M.put(p, new EnumMap<Phase, Transition>(Phase.class));
			for (Transition trans : Transition.values())
				M.get(trans.src).put(trans.dst, trans);
		}

		/**
		 * Finds a phase transition given initial and final phase.
		 * 
		 * @param src
		 *            initial phase
		 * @param dst
		 *            final phase
		 * @return change in phase
		 */
		public static Transition from(Phase src, Phase dst) {
			return M.get(src).get(dst);
		}

	}

}

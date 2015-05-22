package comparison;

import java.util.HashMap;
import java.util.Map;

/**
 * Corresponds to enum SEASON { WINTER, SPRING, SUMMER, FALL }.
 * 
 * @author Jacob Malter
 *
 */
public final class Season extends Enum<Season> {
	private Season(String name, int ordinal) {
		super(name, ordinal);
	}

	/** First season in the year. */
	public static final Season WINTER = new Season("Winter", 0);
	/** Second season in the year. */
	public static final Season SPRING = new Season("Spring", 1);
	/** Third season in the year. */
	public static final Season SUMMER = new Season("Summer", 2);
	/** Fourth season in the year. */
	public static final Season FALL = new Season("Fall", 3);

	/**
	 * This implementation creates a new season array upon every invocation for
	 * security reasons.
	 * 
	 * @return seasons array
	 */
	public static Season[] values() {
		return new Season[] { WINTER, SPRING, SUMMER, FALL };
	}

	// Useful field for valueOf method.

	private static final Map<String, Season> STRING_TO_ENUM = new HashMap<String, Season>();
	static {
		for (Season s : values())
			STRING_TO_ENUM.put(s.toString(), s);
	}

	/**
	 * Returns a season mapped by its name or null if the name does not exist.
	 * 
	 * @param name
	 *            key name of season
	 * @return value season from name
	 */
	public static Season valueOf(String name) {
		return STRING_TO_ENUM.get(name);
	}

}

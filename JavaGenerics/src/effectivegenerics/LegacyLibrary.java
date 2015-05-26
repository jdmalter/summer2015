package effectivegenerics;

import java.util.ArrayList;
import java.util.List;

/**
 * A legacy library containing methods to add items and return a new list.
 * 
 * @author Jacob Malter
 *
 */
public class LegacyLibrary {

	/**
	 * Adds two integers into parameter list.
	 * 
	 * @param list
	 *            list integers added into
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Legacy code without parameterized types, list throws class cast exception
	// when used by newer clients
	public static void addItems(List list) {
		list.add(new Integer(1));
		list.add("two");
	}

	/**
	 * Returns a new list whose items are integers.
	 * 
	 * @return a new list
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	// Legacy code without parameterized types, list throws class cast exception
	// when used by newer clients
	public static List getItems() {
		List list = new ArrayList();
		list.add(new Integer(3));
		list.add("four");
		return list;
	}

}

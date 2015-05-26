package effectivegenerics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Catches errors thrown by legacy library.
 * 
 * @author Jacob Malter
 *
 */
public class WaryClient {

	/**
	 * Does some operations.
	 */
	@SuppressWarnings({ "unchecked" })
	// using legacy code without generics
	public static void processItems() {
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> view = Collections.checkedList(list, Integer.class);
		LegacyLibrary.addItems(view); // class cast exception
		List<Integer> list2 = LegacyLibrary.getItems(); // unchecked
		int s = 0;
		for (int i : list)
			// class cast exception
			s += i;
		for (int i : list2)
			// class cast exception
			s += i;
		System.out.println(s);
	}
}

package effectivegenerics;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * A class containing static methods using the ListString interface.
 * 
 * @author Jacob Malter
 *
 */
public class ListStrings {

	/**
	 * Wraps a list instance into a String specific class with either Random or
	 * Sequential access.
	 * 
	 * @param list
	 *            list being wrapped
	 * @return String specific wrapped list
	 */
	public static ListString wrap(final List<String> list) {

		/**
		 * ListString with random access.
		 * 
		 * @author Jacob Malter
		 *
		 */
		class Random extends AbstractList<String> implements ListString,
				RandomAccess {

			@Override
			public String get(int index) {
				return list.get(index);
			}

			@Override
			public int size() {
				return list.size();
			}

			@Override
			public String set(int index, String s) {
				return list.set(index, s);
			}

			@Override
			public String remove(int index) {
				return list.remove(index);
			}

			@Override
			public void add(int index, String s) {
				list.add(index, s);
			}

		}

		/**
		 * ListString with sequential access.
		 * 
		 * @author Jacob Malter
		 *
		 */
		class Sequentail extends AbstractSequentialList<String> implements
				ListString {

			@Override
			public int size() {
				return list.size();
			}

			@Override
			public ListIterator<String> listIterator(int index) {
				final ListIterator<String> it = list.listIterator(index);
				return new ListIterator<String>() {

					@Override
					public void add(String s) {
						it.add(s);
					}

					@Override
					public boolean hasNext() {
						return it.hasNext();
					}

					@Override
					public String next() {
						return it.next();
					}

					@Override
					public boolean hasPrevious() {
						return it.hasPrevious();
					}

					@Override
					public String previous() {
						return it.previous();
					}

					@Override
					public int nextIndex() {
						return it.nextIndex();
					}

					@Override
					public int previousIndex() {
						return it.previousIndex();
					}

					@Override
					public void remove() {
						it.remove();
					}

					@Override
					public void set(String s) {
						it.set(s);
					}

				};
			}

		} // end of listiterator
		return list instanceof RandomAccess ? new Random() : new Sequentail();
	}

}

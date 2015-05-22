package comparison;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A non-instantiable class containing static operations for comparators.
 * 
 * @author Jacob Malter
 *
 */
public class Comparators {
	private Comparators() {
	}

	/**
	 * Finds maximum element in a collection using a comparator.
	 * 
	 * This implementation differs from its overloaded counterpart
	 * {@code max(Collection<? extends T> coll)} only in its comparison. The
	 * other method uses {@code compareTo(T t)} rather than
	 * {@code compare(T t1, T t2)}.
	 * 
	 * 
	 * @param coll
	 *            collection providing elements
	 * @param cmp
	 *            comparator consuming elements used for ordering
	 * @param <T>
	 *            type of element in collection
	 * @return maximum element from collection
	 * @throws NullPointerException
	 *             when null parameters are passed
	 */
	public static <T> T max(Collection<? extends T> coll,
			Comparator<? super T> cmp) {
		if (coll == null || cmp == null)
			throw new NullPointerException();

		Iterator<? extends T> it = coll.iterator();
		T candidate = it.next();

		while (it.hasNext()) {
			T elt = it.next();
			if (cmp.compare(candidate, elt) < 0)
				candidate = elt;
		}
		return candidate;
	}

	/**
	 * Finds maximum element in a collection using a comparator.
	 * 
	 * Refer to other overloaded method for implementation detail.
	 * 
	 * @param coll
	 *            collection providing elements
	 * @param <T>
	 *            type of element in collection
	 * @return maximum element from collection
	 * @throws NullPointerException
	 *             when null parameters are passed
	 */
	public static <T extends Comparable<? super T>> T max(
			Collection<? extends T> coll) {
		if (coll == null)
			throw new NullPointerException();

		Iterator<? extends T> it = coll.iterator();
		T candidate = it.next();

		while (it.hasNext()) {
			T elt = it.next();
			if (candidate.compareTo(elt) < 0)
				candidate = elt;
		}
		return candidate;
	}

	/**
	 * Finds minimum element in a collection using a comparator.
	 * 
	 * This implementation differs from its overloaded counterpart
	 * {@code min(Collection<? extends T> coll)} only in its comparison. The
	 * other method uses {@code compareTo(T t)} rather than
	 * {@code compare(T t1, T t2)}.
	 * 
	 * @param coll
	 *            collection providing elements
	 * @param cmp
	 *            comparator consuming elements used for ordering
	 * @param <T>
	 *            type of element in collection
	 * @return minimum element from collection
	 * @throws NullPointerException
	 *             when null parameters are passed
	 */
	public static <T> T min(Collection<? extends T> coll,
			Comparator<? super T> cmp) {
		if (coll == null || cmp == null)
			throw new NullPointerException();

		Comparator<? super T> rev = reverseOrder(cmp);
		Iterator<? extends T> it = coll.iterator();

		T candidate = it.next();
		while (it.hasNext()) {
			T elt = it.next();
			if (rev.compare(candidate, elt) < 0)
				candidate = elt;
		}
		return candidate;
	}

	/**
	 * Finds minimum element in a collection using a comparator.
	 * 
	 * Refer to other overloaded method for implementation detail.
	 * 
	 * @param coll
	 *            collection providing elements
	 * @param <T>
	 *            type of element in collection
	 * @return minimum element from collection
	 * @throws NullPointerException
	 *             when null parameters are passed
	 */
	public static <T extends Comparable<? super T>> T min(
			Collection<? extends T> coll) {
		if (coll == null)
			throw new NullPointerException();

		Iterator<? extends T> it = coll.iterator();
		T candidate = it.next();

		while (it.hasNext()) {
			T elt = it.next();
			if (elt.compareTo(candidate) < 0)
				candidate = elt;
		}
		return candidate;
	}

	/**
	 * Returns a new natural order comparator.
	 * 
	 * This implementation constructs a new comparator upon every invocation.
	 * 
	 * @param <T>
	 *            type of element in collection
	 * @return natural order comparator
	 */
	public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
		return new Comparator<T>() {
			public int compare(T o1, T o2) {
				return o1.compareTo(o2);
			}
		};
	}

	/**
	 * Reverses the order of the comparator parameter. Unlike
	 * {@code reverseOrder()}, this
	 * 
	 * This implementation constructs a new comparator with the old comparator
	 * like {@code cmp.compare(o2, o1)}. Rather than calling
	 * {@code o1.compareTo(o2)}.
	 * 
	 * @param cmp
	 *            comparator being reversed
	 * @param <T>
	 *            type of element in collection
	 * @return reverse order comparator of parameter
	 * @throws NullPointerException
	 *             when null parameters are passed
	 */
	public static <T> Comparator<T> reverseOrder(final Comparator<T> cmp) {
		if (cmp == null)
			throw new NullPointerException();

		return new Comparator<T>() {
			public int compare(T o1, T o2) {
				return cmp.compare(o2, o1);
			}
		};
	}

	/**
	 * Returns a new reverse order comparator.
	 * 
	 * This implementation constructs a new comparator upon every invocation.
	 * 
	 * @param <T>
	 *            type of element in collection
	 * @return reverse order comparator
	 */
	public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
		return new Comparator<T>() {
			public int compare(T o1, T o2) {
				return o2.compareTo(o1);
			}
		};
	}

	/**
	 * Returns a new list comparator which takes two {@code List<E>} arguments
	 * and compares them.
	 * 
	 * @param cmp
	 *            comparator used to compare elements within lists
	 * @param <E>
	 *            type of element in collection
	 * @return list comparator
	 */
	public static <E> Comparator<List<E>> listComparator(
			final Comparator<? super E> cmp) {
		return new Comparator<List<E>>() {
			public int compare(List<E> list1, List<E> list2) {
				int n1 = list1.size();
				int n2 = list2.size();

				if (n1 != n2)
					return n1 < n2 ? -1 : 1;

				for (int i = 0; i < n1; i++) {
					int k = cmp.compare(list1.get(i), list2.get(i));
					if (k != 0)
						return k;
				}
				return 0;
			}
		};
	}

}

/**
 * The collection package offers an implementation of a collections framework.
 */
package collection;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A skeletal implementation of the basic collection interface.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public abstract class AbstractCollection<E> implements Collection<E> {

	/**
	 * Protected from outside use.
	 */
	protected AbstractCollection() {
		// Does nothing.
	}

	@Override
	public abstract boolean add(E obj);

	@Override
	public boolean addAll(Collection<? extends E> coll) {
		boolean changed = false;
		for (E e : coll) {
			changed = changed | add(e);
		}
		return changed;
	}

	@Override
	public void clear() {
		forEach((element) -> remove(element));
	}

	@Override
	public boolean contains(Object obj) {
		Iterator<E> it = iterator();
		if (obj == null) {
			while (it.hasNext())
				if (it.next() == null)
					return true;
		} else {
			while (it.hasNext())
				if (obj.equals(it.next()))
					return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		for (Object e : coll)
			if (!contains(e))
				return false;
		return true;

	}

	/**
	 * Empties iterator into array. Helps {@code toArray()} function properly.
	 * 
	 * @param it
	 *            iterator being emptied
	 * @param arr
	 *            array receiving objects
	 * @return filled array
	 * @throws ArrayStoreException
	 *             when iterator type cannot be converted into T; fortunately,
	 *             this should not happen under normal function since the only
	 *             iterators passed in have already done this cast in calling
	 *             {@code toArray(T[] a}.
	 */
	@SuppressWarnings("unchecked")
	private <T> T[] finishArray(Iterator<?> it, T[] arr) {
		int index = arr.length;

		while (it.hasNext()) {
			int capacity = arr.length;
			if (index == capacity)
				arr = Arrays.copyOf(arr, arr.length * 2 + 1);
			arr[index++] = (T) it.next();
		}

		// Trim if still too many elements
		return index == arr.length ? arr : Arrays.copyOf(arr, index);
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public abstract Iterator<E> iterator();

	@Override
	public boolean remove(Object obj) {
		Iterator<E> it = iterator();
		if (obj == null) {
			while (it.hasNext())
				if (it.next() == null) {
					it.remove();
					return true;
				}
		} else {
			while (it.hasNext())
				if (obj.equals(it.next())) {
					it.remove();
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		boolean changed = false;
		Iterator<E> it = iterator();
		while (it.hasNext())
			if (coll.contains(it.next())) {
				it.remove();
				changed = true;
			}
		return changed;
	}

	@Override
	public boolean retainAll(Collection<?> coll) {
		boolean changed = false;
		Iterator<E> it = iterator();
		while (it.hasNext())
			if (!coll.contains(it.next())) {
				it.remove();
				changed = true;
			}
		return changed;
	}

	@Override
	public abstract int size();

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size()];
		Iterator<E> it = iterator();

		for (int i = 0; i < arr.length; i++) {
			// Too few element
			if (!it.hasNext())
				return Arrays.copyOf(arr, i);
			arr[i] = it.next();
		}

		// Too many elements
		return it.hasNext() ? finishArray(it, arr) : arr;
	}

	// Unchecked cast suppressed since ArrayStoreException may be thrown to
	// malicious clients who provide T which cannot cast E into T
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] array) {
		int size = size();
		// Suppressed unchecked cast of (T[]) since an array passed as parameter
		// must be of type T and therefore an array of T
		T[] a = array.length >= size() ? array : (T[]) java.lang.reflect.Array
				.newInstance(array.getClass().getComponentType(), size);
		Iterator<E> it = iterator();

		for (int i = 0; i < a.length; i++) {
			// Too few elements
			if (!it.hasNext()) {
				if (array == a)
					a[i] = null;
				else if (a.length < i)
					return Arrays.copyOf(a, i);
				else {
					System.arraycopy(a, 0, array, 0, array.length);
					if (a.length > i)
						a[i] = null;
				}
				return a;
			}
			a[i] = (T) it.next();
		}

		// Too many elements
		return it.hasNext() ? finishArray(it, a) : a;
	}

	/**
	 * Creates a string representation of a collection. Minimum of two
	 * characters, brackets which start and end the string.
	 * 
	 * This implementation recursively calls each element's (if present)
	 * {@code toString()} method.
	 * 
	 * If no elements are present, this method returns "[]". Otherwise, the
	 * first element is appended on the first bracket without any space. Single
	 * element collections return "[{@code element.toString()}]".
	 * 
	 * For every proceeding element, there is one comma followed by one space
	 * ", " before the next element's toString. After all elements are
	 * exhausted, the end bracket is appended with any space on the last
	 * element.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Iterator<E> it = iterator();

		sb.append("[");
		if (it.hasNext())
			sb.append(it.next().toString());
		while (it.hasNext()) {
			sb.append(", ");
			sb.append(it.next().toString());
		}
		sb.append("]");

		return sb.toString();
	}
}

package collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A multiset implemented through hashing.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class HashMultiset<E> extends AbstractMultiset<E> {

	/**
	 * A single entry in the hash array.
	 * 
	 * @author Jacob Malter
	 *
	 * @param <E>
	 *            The type of the elements stored in this collection.
	 */
	private static class Entry<E> {

		/** How many added objects equal data sorted in this node. */
		private int count;
		/** Object stored */
		private E data;
		/** Reference to next linked entry */
		private Entry<E> next;

		/**
		 * Constructs an entry with data and a linked entry.
		 * 
		 * @param data
		 *            object stored
		 * @param next
		 *            reference to next linked entry
		 */
		private Entry(E data, Entry<E> next) {
			this.count = 1;
			this.data = data;
			this.next = next;
		}

	}

	/** prime number */
	public static final int DEFAULT_CAPACITY = 23;
	/** Reasonably high load rehashing */
	public static final float DEFAULT_LOAD = 0.75f;

	/** percentage of capacity filled before rehash occurs */
	private float load;
	/** number of entries inserted */
	private int size;
	/** memory for entries */
	private Entry<E>[] table;

	/**
	 * Constructs HashBag with default capacity and default load.
	 */
	@SuppressWarnings("unchecked")
	public HashMultiset() {
		load = DEFAULT_LOAD;
		table = new Entry[DEFAULT_CAPACITY];
		// suppression safe since only elements of type E will be inserted
	}

	@Override
	public boolean add(E obj) {
		if (table.length * load < size)
			rehash();
		int hash = hash(obj);

		// Check if node already exists
		Entry<E> current = table[hash];
		while (current != null) {
			if (current.data == null ? obj == null : current.data.equals(obj)) {
				current.count++;
				size++;
				return true;
			}
			current = current.next;
		}

		// Add node at the top
		table[hash] = new Entry<E>(obj, table[hash]);
		size++;
		return true;
	}

	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++)
			table[i] = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		int hash = hash(obj);
		Entry<E> current = table[hash];
		while (current != null) {
			if (current.data == null ? obj == null : current.data.equals(obj))
				return true;
			current = current.next;
		}
		return false;
	}

	/**
	 * Generates unsigned integer position for indexing.
	 * 
	 * @param obj
	 *            hashing subject
	 * @return index
	 */
	private int hash(Object obj) {
		return obj == null ? 0 : (obj.hashCode() & 0x7FFFFFFF) % table.length;
	}

	/**
	 * This implementation ignores changes to underlying HashBag.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Entry<E>[] iteratorTable = Arrays.copyOf(table,
					table.length);
			private int pointer = 0;

			@Override
			public boolean hasNext() {
				return pointer < size;
			}

			@Override
			public E next() {
				if (!hasNext())
					throw new NoSuchElementException(
							"No more elements remaining in iterator");

				Entry<E> entry = iteratorTable[pointer];
				E result = entry.data;
				iteratorTable[pointer].count--;
				if (iteratorTable[pointer].count < 1)
					if (entry.next != null)
						iteratorTable[pointer] = entry.next;
					else
						pointer++;
				return result;
			}

		};
	}

	/**
	 * Resizes entry array and rehashes entries.
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		Entry<E>[] oldTable = Arrays.copyOf(table, table.length);
		table = (Entry<E>[]) new Entry[table.length * 2 + 1];
		// suppression safe since only elements of type E will be inserted

		// add old elements
		for (int j = 0; j < table.length; j++) {
			while (oldTable[j] != null) {
				for (int k = 0; k < oldTable[j].count; k++)
					add(oldTable[j].data);
				oldTable[j] = oldTable[j].next;
			}
		}
	}

	@Override
	public boolean remove(Object obj) {
		int hash = hash(obj);

		if (table[hash] != null) {
			// Check top bucket
			if (table[hash].data == null ? obj == null : table[hash].data
					.equals(obj)) {
				table[hash].count--;
				if (table[hash].count == 0)
					table[hash] = table[hash].next;
				size--;
				return true;
			}

			// Check lower buckets
			Entry<E> prev = table[hash];
			Entry<E> current = table[hash].next;
			while (current != null) {
				if (current.data == null ? obj == null : current.data
						.equals(obj)) {
					current.count--;
					if (current.count == 0)
						prev.next = prev.next.next;
					size--;
					return true;
				}
				prev = current;
				current = current.next;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}

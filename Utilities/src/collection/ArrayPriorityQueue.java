package collection;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Heap based priority queue implemented by array.
 * 
 * @author Jacob Malter
 *
 * @param <E>
 *            The type of the elements stored in this collection.
 */
public class ArrayPriorityQueue<E extends Comparable<? super E>> extends
		AbstractQueue<E> implements Navigable<E> {

	/** Used to compare elements */
	private final Comparator<? super E> comparator;
	/** elements */
	private E[] heap;
	/** Number of elements */
	private int size;

	/**
	 * Constructs an empty ArrayPriorityQueue.
	 */
	public ArrayPriorityQueue() {
		this(null);
	}

	/**
	 * Constructs an empty ArrayPriorityQueue given a comparator.
	 * 
	 * @param comparator
	 *            imposes ordering on elements
	 */
	@SuppressWarnings("unchecked")
	public ArrayPriorityQueue(Comparator<? super E> comparator) {
		this.comparator = comparator;
		heap = (E[]) Arrays.DEFAULT_ARRAY;
		// suppression safe since only elements of type E will be inserted
	}

	@Override
	public boolean add(E obj) {
		ensureCapacity(size() + 1);
		heap[size] = obj;
		siftUp(size++);
		return true;
	}

	/**
	 * Converts an array into a maxheap.
	 * 
	 * @param array
	 *            converted subject
	 */
	@SuppressWarnings("unused")
	private void buildMaxHeap(E[] array) {
		for (int i = (array.length - 2) / 2; i > -1; i--)
			maxHeapify(array, i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		// Suppression safe...you know why.
		heap = (E[]) Arrays.DEFAULT_ARRAY;
		size = 0;
	}

	/**
	 * Returns comparator used for ordering.
	 * 
	 * @return comparator used for ordering
	 */
	public Comparator<? super E> comparator() {
		return comparator;
	}

	@Override
	public E ceiling(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Creates room for new elements.
	 */
	@SuppressWarnings("unchecked")
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = heap.length;
		if (minCapacity >= oldCapacity) {
			int newCapacity = oldCapacity * 2 + 1;
			E[] copy = heap;
			heap = (E[]) new Object[newCapacity];
			System.arraycopy(copy, 0, heap, 0, copy.length);
		}
	}

	@Override
	public E element() {
		return heap[0];
	}

	@Override
	public E floor(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E higher(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E lower(E e) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Rearranges array elements into a max-heap.
	 * 
	 * PRECONDTION: subtrees already are max-heaps
	 * 
	 * @param array
	 *            subject of maxHeapify
	 * @param index
	 *            position below which elements are sorted
	 */
	private void maxHeapify(E[] array, int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException("Index less than zero.");
		else if (index >= heap.length)
			throw new IndexOutOfBoundsException("Index greater than capacity.");
		int size = array.length;
		if (2 * index + 1 < size
				&& array[index].compareTo(array[2 * index + 1]) < 0) {
			Arrays.swap(array, index, 2 * index + 1);
			maxHeapify(array, 2 * index + 1);
		} else if (2 * index + 2 < size
				&& array[index].compareTo(array[2 * index + 2]) < 0) {
			Arrays.swap(array, index, 2 * index + 2);
			maxHeapify(array, 2 * index + 2);
		}
	}

	@Override
	public E maximum() {
		return heap[0];
	}

	@Override
	public E minimum() {
		int index = heap.length / 2;
		E min = heap[index++];
		for (int i = index; i < heap.length; i++)
			min = heap[index].compareTo(min) < 0 ? heap[index] : min;
		return min;
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	private void siftUp(int index) {
		if (index == 0)
			return;
		else if (index < 0)
			throw new IndexOutOfBoundsException("Index less than zero.");
		else if (index >= heap.length)
			throw new IndexOutOfBoundsException("Index greater than capacity.");
		if (heap[index].compareTo(heap[(index - 1) / 2]) > 0) {
			Arrays.swap(heap, index, (index - 1) / 2);
			siftUp((index - 1) / 2);
		}
	}

	@Override
	public int size() {
		return size;
	}

}

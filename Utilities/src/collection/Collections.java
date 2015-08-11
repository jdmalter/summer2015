package collection;

import java.util.Random;

/**
 * Auxilary class containing static operations for collections.
 * 
 * @author Jacob Malter
 *
 */
public class Collections {

	/** Empty collection for general use. */
	public static final Collection<?> EMPTY_COLLECTION = new CircularArray<Object>();
	/** Empty collection used for fast iteration and remove operations. */
	public static final Collection<?> ITERABLE_AND_REMOVE_COLLECTION = new CircularArray<Object>();
	private static final int QUICK_TO_INSERT_SORT = 24;

	private Collections() {
	}

	/**
	 * Finds the index of a given element in a given sorted list.
	 * 
	 * PRECONDITION: List is sorted from least to greatest, and elements are
	 * comparable.
	 * 
	 * @param list
	 *            containing elements
	 * @param element
	 *            target being found in list
	 * @param <T>
	 *            type of element
	 * @return index of element in list, -1 if not found
	 */
	public static <T extends Comparable<? super T>> int binarySearch(
			List<T> list, T element) {
		return binarySearch(list, element, 0, list.size() - 1);
	}

	private static <T extends Comparable<? super T>> int binarySearch(
			List<T> list, T element, int min, int max) {
		if (list.size() < 1 || max < min)
			return -1;
		int half = (min + max) / 2;
		int comparison = ((list.get(half)).compareTo(element));

		return comparison < 0 ? binarySearch(list, element, half + 1, max)
				: (comparison == 0 ? half : binarySearch(list, element, min,
						half - 1));
	}

	/**
	 * Converts a decimal number into any given base greater than one.
	 * 
	 * @param value
	 *            number being converted
	 * @param base
	 *            base of number system converted into
	 * @return list of Integer with each number being a digit in the new number
	 */
	public static List<Integer> decompse(int value, int base) {
		Stack<Integer> stack = new CircularArray<Integer>();
		while (value > 0 && base > 1) {
			stack.push(value % base);
			value = value / base;
		}
		List<Integer> result = new CircularArray<Integer>();
		while (!stack.isEmpty())
			result.add(stack.pop());
		return result;
	}

	/**
	 * Computes dot product of two lists of Integer.
	 * 
	 * @param listOne
	 *            target list
	 * @param listTwo
	 *            target list
	 * @return dot product
	 * @throws IllegalArgumentException
	 *             if parameter list lengths do not match
	 */
	public static int dotProduct(List<Integer> listOne, List<Integer> listTwo) {
		if (listOne.size() != listTwo.size())
			throw new IllegalArgumentException("Array lengths must be equal");
		int result = 0;
		for (int i = 0; i < listOne.size(); i++)
			result += listOne.get(i) * listTwo.get(i);
		return result;
	}

	/**
	 * Sorts an list by swapping elements until an element is less than its
	 * previous swapped element or at the beginning. Reasonable on smaller (less
	 * than few dozen) lists.
	 * 
	 * Stability: Equal elements are not changed.
	 * 
	 * Extra Space: O(1)
	 * 
	 * Comparisons and Swaps: Performs well on nearly sorted lists. Performs
	 * worst on reversed lists. Therefore adaptive. O(n) to O(n^2)
	 * 
	 * @param list
	 *            subject of insertionSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void insertionSort(
			List<T> list) {
		for (int i = 2; i < list.size(); i++) {
			for (int j = i; j > 1
					&& (list.get(j)).compareTo(list.get(j - 1)) < 0; j--) {
				swap(list, j, j - 1);
			}
		}
	}

	/**
	 * Sorts a list by heapifying it. Least elements are last.
	 * 
	 * Stability: Equal elements are changed.
	 * 
	 * Extra Space: O(n) * this implementation is o(n)...thanks Java!! *
	 * 
	 * Comparisons and Swaps: O(nlog(n))
	 * 
	 * @param list
	 *            subject of heapSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void heapSort(List<T> list) {
		ArrayPriorityQueue<T> heap = new ArrayPriorityQueue<T>();
		for (int i = 0; i < list.size(); i++)
			heap.add(list.get(i));
		for (int i = 0; i < list.size(); i++)
			list.set(i, heap.remove());
	}

	/**
	 * Returns the greatest element within a given list.
	 * 
	 * This implementation returns the index of the first occurrence of max.
	 * 
	 * @param list
	 *            contains elements
	 * @param <T>
	 *            type of element
	 * @return greatest element
	 */
	public static <T extends Comparable<? super T>> T maximum(List<T> list) {
		if (list.size() == 0)
			return null;
		T max = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			T current = list.get(i);
			max = max.compareTo(current) < 0 ? current : max;
		}
		return max;
	}

	/**
	 * Recursively sorts a list by merging sorted halves. Its main advantages
	 * are stability and predictable performance on large lists. Reasonable on
	 * larger lists (greater than few dozen).
	 * 
	 * Stability: Equal elements are not changed.
	 * 
	 * Extra Space: O(n)
	 * 
	 * Comparisons and Swaps: Performs moderately well on all types of sorted
	 * lists. Minimum swaps on already sorted lists. Maximum swaps on for random
	 * lists. Not adaptive. O(nlog(n))
	 * 
	 * @param list
	 *            subject of mergeSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void mergeSort(List<T> list) {
		if (list.size() < 2)
			return;
		int half = list.size() / 2;

		// suppress safe since only elements of type T will be copied into list
		List<T> firstHalf = new CircularArray<T>();
		for (int i = 0; i < half; i++)
			firstHalf.set(i, list.get(i));
		List<T> secondHalf = new CircularArray<T>();
		for (int i = half; i < list.size(); i++)
			secondHalf.set(i, list.get(i));

		mergeSort(firstHalf);
		mergeSort(secondHalf);
		for (int i = 0, j = 0, k = 0; i < list.size(); i++)
			if (j < firstHalf.size() && k < secondHalf.size())
				list.set(
						i,
						(firstHalf.get(j)).compareTo(secondHalf.get(k)) < 0 ? firstHalf
								.get(j++) : secondHalf.get(k++));
			else if (j < firstHalf.size() && k >= secondHalf.size())
				list.set(i, firstHalf.get(j++));
			else if (k < secondHalf.size() && j >= firstHalf.size())
				list.set(i, secondHalf.get(k++));
	}

	/**
	 * Returns the least element within a given list.
	 * 
	 * This implementation returns the index of the first occurrence of min.
	 * 
	 * @param list
	 *            contains elements
	 * @param <T>
	 *            type of element
	 * @return least element
	 */
	public static <T extends Comparable<? super T>> T minimum(List<T> list) {
		if (list.size() == 0)
			return null;
		T min = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			T current = list.get(i);
			min = min.compareTo(current) > 0 ? current : min;
		}
		return min;
	}

	/**
	 * Cuts a list into two parts. Elements at the front less than the pivot and
	 * elements at the end greater than the pivot. Ideally, each part is 50/50
	 * split. 10/90 split is bounded by O(nlog(n)). Worst case, the pivot is at
	 * the end.
	 * 
	 * This implementation picks its pivot as the first element.
	 * 
	 * @param list
	 *            target list being partitioned
	 * @param first
	 *            where first part starts
	 * @param last
	 *            where second part starts
	 * @param pivot
	 *            element used to divide other elements
	 * @return pivot index
	 */
	private static <T extends Comparable<? super T>> int partition(
			List<T> list, int first, int last, T pivot) {
		int i = first;
		for (int j = first + 1; j < last; j++)
			if ((list.get(j)).compareTo(pivot) <= 0)
				swap(list, ++i, j);
		swap(list, first, i);
		return i;
	}

	/**
	 * Reasonable as general purpose sorting. Relies on insertion sort for small
	 * lists. Uses randomized pivots to reduce worse-case scenarios. With
	 * tuning, runs much faster than mergesort.
	 * 
	 * Stability: Equal elements are changed.
	 * 
	 * Extra space: O(1)
	 * 
	 * Comparisons: O(n^2) but typically O(nlog(n))
	 * 
	 * Swaps: O(n^2) but typically O(nlog(n))
	 * 
	 * @param list
	 *            target list with elements
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void quickSort(List<T> list) {
		quickSort(list, 0, list.size());
	}

	private static <T extends Comparable<? super T>> void quickSort(
			List<T> list, int first, int last) {
		if (list.size() < 2)
			return;
		if (list.size() < QUICK_TO_INSERT_SORT)
			insertionSort(list);
		else if (first < last) {
			int pivot = randomPartition(list, first, last);
			if (pivot < list.size() / 2) {
				quickSort(list, first, pivot - 1);
				quickSort(list, pivot + 1, last);
			} else {
				quickSort(list, pivot + 1, last);
				quickSort(list, first, pivot - 1);
			}
		}
	}

	/**
	 * Cuts an list into two parts. Elements at the front less than the pivot
	 * and elements at the end greater than the pivot. Ideally, each part is
	 * 50/50 split. 10/90 split is bounded by O(nlog(n)). Worst case, the pivot
	 * is at the end.
	 * 
	 * This implementation picks its pivot at random.
	 * 
	 * @param list
	 *            target list being partitioned
	 * @param first
	 *            where first part starts
	 * @param last
	 *            where second part starts
	 * @return pivot index
	 */
	private static <T extends Comparable<? super T>> int randomPartition(
			List<T> list, int first, int last) {
		swap(list, first, new Random().nextInt(last));
		return partition(list, first, last, list.get(first));
	}

	/**
	 * Checks if given indices are valid for a given collection length. Throws
	 * exceptions if an index is invalid.
	 * 
	 * @param list
	 *            target collection with elements
	 * @param indexFirst
	 *            position of first element
	 * @param indexSecond
	 *            position of second element
	 */
	private static void rangeCheck(int listLength, int firstIndex, int lastIndex) {
		if (lastIndex < firstIndex)
			throw new IllegalArgumentException("firstIndex (" + firstIndex
					+ ") is greater than lastIndex (" + lastIndex + ")");
		if (lastIndex >= listLength)
			throw new IndexOutOfBoundsException();
		if (firstIndex < 0)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * Reverses elements within a given list using (n / 2) swaps where n is the
	 * number of element.
	 * 
	 * @param list
	 *            target list with elements
	 * @param <T>
	 *            type of element
	 */
	public static <T> void reverse(List<T> list) {
		if (list.size() < 2)
			return;
		for (int i = 0; i < list.size() / 2; i++)
			swap(list, i, list.size() - i - 1);
	}

	/**
	 * Generally slower than any other sort algorithm. Its main advantage is
	 * guaranteed O(n) swaps. If swaps are expensive, selection sort is ideal.
	 * Reasonable on small lists.
	 * 
	 * Stability: Equal elements are changed.
	 * 
	 * Extra Space: O(1)
	 * 
	 * Comparisons: O(n^2)
	 * 
	 * Swaps: O(n)
	 * 
	 * @param list
	 *            subject of selectionSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void selectionSort(
			List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			int min = i;
			for (int j = i + 1; j < list.size(); j++) {
				min = (list.get(j)).compareTo(list.get(min)) < 0 ? j : min;
			}
			swap(list, i, min);
		}
	}

	/**
	 * Swaps elements at two given indices within an list. Last index must be
	 * greater than first index.
	 * 
	 * @param list
	 *            target list with elements
	 * @param firstIndex
	 *            position of first element
	 * @param secondIndex
	 *            position of second element
	 * @param <T>
	 *            type of element
	 */
	public static <T> void swap(List<T> list, int firstIndex, int secondIndex) {
		rangeCheck(list.size(), firstIndex, secondIndex);
		T temp = list.get(firstIndex);
		list.set(firstIndex, list.get(secondIndex));
		list.set(secondIndex, temp);
	}

}
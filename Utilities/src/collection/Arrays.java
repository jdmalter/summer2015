package collection;

import java.util.Random;

/**
 * Auxilary class containing static operations for arrays.
 * 
 * @author Jacob Malter
 *
 */
public class Arrays {

	/** Default size for starting array in collections back by an array */
	public static final int DEFAULT_CAPACITY = 16;
	/** Empty array used to initially back array based collections */
	public static final Object[] DEFAULT_ARRAY = new Object[DEFAULT_CAPACITY];
	private static final int QUICK_TO_INSERT_SORT = 24;

	private Arrays() {
	}

	/**
	 * Finds the index of a given element in a given sorted array.
	 * 
	 * PRECONDITION: Array is sorted from least to greatest, and elements are
	 * comparable.
	 * 
	 * @param array
	 *            containing elements
	 * @param element
	 *            target being found in array
	 * @param <T>
	 *            type of element
	 * @return index of element in array, -1 if not found
	 */
	public static <T extends Comparable<? super T>> int binarySearch(T[] array,
			T element) {
		return binarySearch(array, element, 0, array.length - 1);
	}

	private static <T extends Comparable<? super T>> int binarySearch(
			T[] array, T element, int min, int max) {
		if (array.length < 1 || max < min)
			return -1;
		int half = (min + max) / 2;
		int comparison = (array[half].compareTo(element));

		return comparison < 0 ? binarySearch(array, element, half + 1, max)
				: (comparison == 0 ? half : binarySearch(array, element, min,
						half - 1));
	}

	/**
	 * Sorts an array by swapping elements until an element is less than its
	 * previous swapped element or at the beginning. Reasonable on smaller (less
	 * than few dozen) arrays.
	 * 
	 * Stability: Equal elements are not changed.
	 * 
	 * Extra Space: O(1)
	 * 
	 * Comparisons and Swaps: Performs well on nearly sorted arrays. Performs
	 * worst on reversed arrays. Therefore adaptive. O(n) to O(n^2)
	 * 
	 * @param array
	 *            subject of insertionSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void insertionSort(T[] array) {
		if (array.length < 2)
			return;
		for (int i = 2; i < array.length; i++) {
			for (int j = i; j > 1 && (array[j].compareTo(array[j - 1])) < 0; j--) {
				swap(array, j, j - 1);
			}
		}
	}

	/**
	 * Returns the greatest element within a given array.
	 * 
	 * This implementation returns the index of the first occurrence of max.
	 * 
	 * @param array
	 *            contains elements
	 * @param <T>
	 *            type of element
	 * @return greatest element
	 */
	public static <T extends Comparable<? super T>> T maximum(T[] array) {
		if (array.length == 0)
			return null;
		T max = array[0];
		for (int i = 1; i < array.length; i++)
			if (max.compareTo(array[i]) < 0)
				max = array[i];
		return max;
	}

	/**
	 * Recursively sorts an array by merging sorted halves. Its main advantages
	 * are stability and predictable performance on large arrays. Reasonable on
	 * larger arrays (greater than few dozen).
	 * 
	 * Stability: Equal elements are not changed.
	 * 
	 * Extra Space: O(n)
	 * 
	 * Comparisons and Swaps: Performs moderately well on all types of sorted
	 * arrays. Minimum swaps on already sorted arrays. Maximum swaps on for
	 * random arrays. Not adaptive. O(nlog(n))
	 * 
	 * @param array
	 *            subject of mergeSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void mergeSort(T[] array) {
		if (array.length < 2)
			return;
		int half = array.length / 2;

		// suppress safe since only elements of type T will be copied into array
		@SuppressWarnings("unchecked")
		T[] firstHalf = (T[]) new Object[half];
		for (int i = 0; i < half; i++)
			firstHalf[i] = array[i];
		@SuppressWarnings("unchecked")
		T[] secondHalf = (T[]) new Object[array.length - half];
		for (int i = 0; i < array.length - half; i++)
			secondHalf[i] = array[i + half];

		mergeSort(firstHalf);
		mergeSort(secondHalf);
		for (int i = 0, j = 0, k = 0; i < array.length; i++)
			if (j < firstHalf.length && k < secondHalf.length)
				array[i] = (firstHalf[j]).compareTo(secondHalf[k]) < 0 ? firstHalf[j++]
						: secondHalf[k++];
			else if (j < firstHalf.length && k >= secondHalf.length)
				array[i] = firstHalf[j++];
			else if (k < secondHalf.length && j >= firstHalf.length)
				array[i] = secondHalf[k++];
	}

	/**
	 * Returns the least element within a given array.
	 * 
	 * This implementation returns the index of the first occurrence of min.
	 * 
	 * @param array
	 *            contains elements
	 * @param <T>
	 *            type of element
	 * @return least element
	 */
	public static <T extends Comparable<? super T>> T minimum(T[] array) {
		if (array.length == 0)
			return null;
		T min = array[0];
		for (int i = 1; i < array.length; i++)
			if (min.compareTo(array[i]) > 0)
				min = array[i];
		return min;
	}

	/**
	 * Cuts an array into two parts. Elements at the front less than the pivot
	 * and elements at the end greater than the pivot. Ideally, each part is
	 * 50/50 split. 10/90 split is bounded by O(nlog(n)). Worst case, the pivot
	 * is at the end.
	 * 
	 * @param array
	 *            target array being partitioned
	 * @param first
	 *            where first part starts
	 * @param last
	 *            where second part starts
	 * @param pivot
	 *            element used to divide other elements
	 * @param <T>
	 *            type of element
	 * @return pivot index
	 */
	private static <T extends Comparable<? super T>> int partition(T[] array,
			int first, int last, T pivot) {
		int i = first;
		for (int j = first + 1; j < last; j++)
			if ((array[j]).compareTo(pivot) <= 0)
				swap(array, ++i, j);
		swap(array, first, i);
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
	 * @param array
	 *            target array with elements
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void quickSort(T[] array) {
		quickSort(array, 0, array.length);
	}

	private static <T extends Comparable<? super T>> void quickSort(T[] array,
			int first, int last) {
		if (array.length < 2)
			return;
		if (array.length < QUICK_TO_INSERT_SORT)
			insertionSort(array);
		else if (first < last) {
			int pivot = randomPartition(array, first, last);
			if (pivot < array.length / 2) {
				quickSort(array, first, pivot - 1);
				quickSort(array, pivot + 1, last);
			} else {
				quickSort(array, pivot + 1, last);
				quickSort(array, first, pivot - 1);
			}
		}
	}

	/**
	 * Cuts an array into two parts. Elements at the front less than the pivot
	 * and elements at the end greater than the pivot. Ideally, each part is
	 * 50/50 split. 10/90 split is bounded by O(nlog(n)). Worst case, the pivot
	 * is at the end.
	 * 
	 * This implementation picks its pivot at random.
	 * 
	 * @param array
	 *            target array being partitioned
	 * @param first
	 *            where first part starts
	 * @param last
	 *            where second part starts
	 * @param <T>
	 *            type of element
	 * @return pivot index
	 */
	private static <T extends Comparable<? super T>> int randomPartition(
			T[] array, int first, int last) {
		swap(array, first, new Random().nextInt(last));
		return partition(array, first, last, array[first]);
	}

	/**
	 * Checks if given indices are valid for a given array length. Throws
	 * exceptions if an index is invalid.
	 * 
	 * @param array
	 *            target array with elements
	 * @param indexFirst
	 *            position of first element
	 * @param indexSecond
	 *            position of second element
	 */
	private static void rangeCheck(int arrayLength, int firstIndex,
			int lastIndex) {
		if (lastIndex < firstIndex)
			throw new IllegalArgumentException("firstIndex (" + firstIndex
					+ ") is greater than lastIndex (" + lastIndex + ")");
		if (lastIndex >= arrayLength)
			throw new ArrayIndexOutOfBoundsException(lastIndex);
		if (firstIndex < 0)
			throw new ArrayIndexOutOfBoundsException(firstIndex);
	}

	/**
	 * Reverses elements within a given array using (n / 2) swaps where n is the
	 * number of element.
	 * 
	 * @param array
	 *            target array with elements
	 */
	@SuppressWarnings("unused")
	private static <T> void reverse(T[] array) {
		if (array.length < 2)
			return;
		for (int i = 0; i < array.length / 2; i++)
			swap(array, i, array.length - i - 1);
	}

	/**
	 * Generally slower than any other sort algorithm. Its main advantage is
	 * guaranteed O(n) swaps. If swaps are expensive, selection sort is ideal.
	 * Reasonable on small arrays.
	 * 
	 * Stability: Equal elements are changed.
	 * 
	 * Extra Space: O(1)
	 * 
	 * Comparisons: O(n^2)
	 * 
	 * Swaps: O(n)
	 * 
	 * @param array
	 *            subject of selectionSort
	 * @param <T>
	 *            type of element
	 */
	public static <T extends Comparable<? super T>> void selectionSort(T[] array) {
		if (array.length < 2)
			return;
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				min = (array[j]).compareTo(array[min]) < 0 ? j : min;
			}
			swap(array, i, min);
		}
	}

	/**
	 * Swaps elements at two given indices within an array. Last index must be
	 * greater than first index.
	 * 
	 * @param array
	 *            target array with elements
	 * @param firstIndex
	 *            position of first element
	 * @param secondIndex
	 *            position of second element
	 * @param <T>
	 *            type of element
	 */
	public static <T> void swap(T[] array, int firstIndex, int secondIndex) {
		rangeCheck(array.length, firstIndex, secondIndex);
		T temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}

}

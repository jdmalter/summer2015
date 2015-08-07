package collection;

/**
 * Auxilary class containing static operations for arrays.
 * 
 * @author Jacob Malter
 *
 */
public class Arrays {

	private Arrays() {
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
	 */
	@SuppressWarnings("unchecked")
	public static <T> void insertionSort(T[] array) {
		for (int i = 2; i < array.length; i++) {
			for (int j = i; j > 1
					&& ((Comparable<Object>) array[j]).compareTo(array[j - 1]) < 0; j--) {
				swap(array, j, j - 1);
			}
		}
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
	 */
	@SuppressWarnings("unchecked")
	public static <T> void mergeSort(T[] array) {
		if (array.length < 2)
			return;
		int half = array.length / 2;

		// suppress safe since only elements of type T will be copied into array
		T[] firstHalf = (T[]) new Object[half];
		for (int i = 0; i < half; i++)
			firstHalf[i] = array[i];
		T[] secondHalf = (T[]) new Object[array.length - half];
		for (int i = half; i < array.length; i++)
			firstHalf[i] = array[i];

		mergeSort(firstHalf);
		mergeSort(secondHalf);
		for (int i = 0, j = 0, k = 0; i < array.length; i++)
			if (j < firstHalf.length && k < secondHalf.length)
				array[i] = ((Comparable<Object>) firstHalf[j])
						.compareTo(secondHalf[k]) < 0 ? firstHalf[j++]
						: secondHalf[k++];
			else if (j < firstHalf.length && k >= secondHalf.length)
				array[i] = firstHalf[j++];
			else if (k < secondHalf.length && j >= firstHalf.length)
				array[i] = secondHalf[k++];
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
		if (lastIndex > arrayLength)
			throw new ArrayIndexOutOfBoundsException(lastIndex);
		if (firstIndex < 0)
			throw new ArrayIndexOutOfBoundsException(firstIndex);
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
	 */
	@SuppressWarnings("unchecked")
	public static <T> void selectionSort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i + 1; j < array.length; j++) {
				min = ((Comparable<Object>) array[j]).compareTo(min) < 0 ? j
						: min;
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
	 * @param indexFirst
	 *            position of first element
	 * @param indexSecond
	 *            position of second element
	 */
	public static <T> void swap(T[] array, int firstIndex, int lastIndex) {
		rangeCheck(array.length, firstIndex, lastIndex);
		T temp = array[firstIndex];
		array[firstIndex] = array[lastIndex];
		array[lastIndex] = temp;
	}

}

package collection;

/**
 * Auxilary class containing static operations for collections.
 * 
 * @author Jacob Malter
 *
 */
public class Collections {

	private Collections() {
	}

	/**
	 * Sorts an array by swapping elements until an element is less than its
	 * previous swapped element or at the beginning. Reasonable on smaller (less
	 * than few dozen) lists.
	 * 
	 * Stability: Equal elements are not changed.
	 * 
	 * Extra Space: O(1)
	 * 
	 * Comparisons and Swaps: Performs well on nearly sorted arrays. Performs
	 * worst on reversed lists. Therefore adaptive. O(n) to O(n^2)
	 * 
	 * @param list
	 *            subject of insertionSort
	 */
	@SuppressWarnings("unchecked")
	public static <T> void insertionSort(List<T> list) {
		for (int i = 2; i < list.size(); i++) {
			for (int j = i; j > 1
					&& ((Comparable<Object>) list.get(j)).compareTo(list
							.get(j - 1)) < 0; j--) {
				swap(list, j, j - 1);
			}
		}
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
	 * arrays. Minimum swaps on already sorted lists. Maximum swaps on for
	 * random lists. Not adaptive. O(nlog(n))
	 * 
	 * @param list
	 *            subject of mergeSort
	 */
	@SuppressWarnings("unchecked")
	public static <T> void mergeSort(List<T> list) {
		if (list.size() < 2)
			return;
		int half = list.size() / 2;

		// suppress safe since only elements of type T will be copied into array
		List<T> firstHalf = new CircularArray<T>(half);
		for (int i = 0; i < half; i++)
			firstHalf.set(i, list.get(i));
		List<T> secondHalf = new CircularArray<T>(list.size() - half);
		for (int i = half; i < list.size(); i++)
			secondHalf.set(i, list.get(i));

		mergeSort(firstHalf);
		mergeSort(secondHalf);
		for (int i = 0, j = 0, k = 0; i < list.size(); i++)
			if (j < firstHalf.size() && k < secondHalf.size())
				list.set(i, ((Comparable<Object>) firstHalf.get(j))
						.compareTo(secondHalf.get(k)) < 0 ? firstHalf.get(j++)
						: secondHalf.get(k++));
			else if (j < firstHalf.size() && k >= secondHalf.size())
				list.set(i, firstHalf.get(j++));
			else if (k < secondHalf.size() && j >= firstHalf.size())
				list.set(i, secondHalf.get(k++));
	}

	/**
	 * Checks if given indices are valid for a given collection length. Throws
	 * exceptions if an index is invalid.
	 * 
	 * @param array
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
		if (lastIndex > listLength)
			throw new ArrayIndexOutOfBoundsException(lastIndex);
		if (firstIndex < 0)
			throw new ArrayIndexOutOfBoundsException(firstIndex);
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
	 */
	@SuppressWarnings("unchecked")
	public static <T> void selectionSort(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			int min = i;
			for (int j = i + 1; j < list.size(); j++) {
				min = ((Comparable<Object>) list.get(j)).compareTo(list
						.get(min)) < 0 ? j : min;
			}
			swap(list, i, min);
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
	public static <T> void swap(List<T> list, int firstIndex, int secondIndex) {
		rangeCheck(list.size(), firstIndex, secondIndex);
		T temp = list.get(firstIndex);
		list.set(firstIndex, list.get(secondIndex));
		list.set(secondIndex, temp);
	}

}
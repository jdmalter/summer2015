/**
 * 
 */
package collection;

/**
 * Provides useful operations for sorted collections that act independently of
 * an instance.
 * 
 * @author Jacob Malter
 */
public class Sorts {

    /**
     * Unable to be instantiated.
     */
    private Sorts() {
    }

    /**
     * Fastest sort for nearly sorted data. Sorts nearly sorted lists extremely
     * quickly. Sorts few unique and random slightly slower. Sorts reversed
     * extremely slow. Addtionally, good for small lists.
     * 
     * @param list
     *            list being sorted
     */
    public static void insertionSort(List<?> list) {

    }

    /**
     * Constant number of swaps O(n). Useful if cost of swap is high. Do not use
     * on nearly sorted data.
     * 
     * @param list
     *            list being sorted
     */
    public static void selectionSort(List<?> list) {

    }

    /**
     * Same properties as insertionSort, but requires one additional search.
     * 
     * @param list
     *            list being sorted
     */
    public static void bubbleSort(List<?> list) {

    }

    /**
     * Worst case is O(n^1.5). Inherits properties of insertionSort. Runs faster
     * than insertionSort on small structures.
     * 
     * @param list
     *            list being sorted
     */
    public static void shellSort(List<?> list) {

    }

    /**
     * Predictable with same run speed on any list O(nlogn). Useful for linked
     * list and when random access is slower than sequentail access.
     * 
     * @param list
     *            list being sorted
     */
    public static void mergeSort(List<?> list) {

    }

    /**
     * Fastest sort for random data, reversed data, and few unique but sorts
     * reversed and unique the fastest.
     * 
     * @param list
     *            list being sorted
     */
    public static void heapSort(List<?> list) {

    }
}

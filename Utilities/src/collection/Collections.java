package collection;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Provides useful operations for collections that act independently of an
 * instance.
 * 
 * @author Jacob Malter
 */
public class Collections {

    /**
     * Unable to be instantiated.
     */
    private Collections() {
    }

    /**
     * 
     * @param dest
     * @param src
     */
    public static <T> void copy(List<? super T> dest, List<? extends T> src) {

    }

    /**
     * 
     * @param coll
     * @return
     */
    public static <T> T max(Collection<? extends T> coll) {
	return null;
    }

    /**
     * 
     * @param coll
     * @param comp
     * @return
     */
    public static <T> T max(Collection<? extends T> coll,
	    Comparator<? super T> comp) {
	return null;
    }

    /**
     * 
     * @param coll
     * @return
     */
    public static <T> T min(Collection<? extends T> coll) {
	return null;
    }

    /**
     * 
     * @param coll
     * @param comp
     * @return
     */
    public static <T> T min(Collection<? extends T> coll,
	    Comparator<? super T> comp) {
	return null;
    }

    /**
     * 
     * @param list
     * @param oldVal
     * @param newVal
     * @return
     */
    public static <T> boolean replaceAll(List<T> list, T oldVal, T newVal) {
	return false;
    }

    /**
     * 
     * @param list
     */
    public static void reverse(List<?> list) {

    }

    /**
     * 
     * @param list
     */
    public static void shuffle(List<?> list) {

    }

    /**
     * 
     * @param list
     * @param rand
     */
    public static void shuffle(List<?> list, Random rand) {

    }

    /**
     * 
     * @param list
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {

    }

    /**
     * 
     * @param list
     * @param c
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list,
	    Comparator<? super T> c) {

    }

    /**
     * 
     * @param list
     * @param i
     * @param j
     */
    public void swap(List<?> list, int i, int j) {

    }

    /**
     * 
     * @param array
     * @param i
     * @param j
     */
    public void swap(Object[] array, int i, int j) {

    }
}

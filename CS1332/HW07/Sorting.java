import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement cocktail shaker sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * When writing your sort, don't recheck already sorted items. The amount of
     * items you are comparing should decrease by 1 for each pass of the array
     * (in either direction). See the PDF for more info.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailShakerSort(T[] arr,
                                              Comparator<T> comparator)
            throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Empty Array or null comparator");
        } else {
            boolean swapped = true;
            //i insures sorted elements are not tested again
            for (int i = 0; i < arr.length / 2; i++) {
                //if no swaps happen array is sorted
                while (swapped) {
                    swapped = false;
                    for (int j = i; j < arr.length - i - 1; j++) {
                        //forward bubble
                        int det = comparator.compare(arr[j], arr[j + 1]);
                        if (det > 0) {
                            swap(arr, j, j + 1);
                            swapped = true;
                        }
                    }
                    for (int k = arr.length - 2 - i; k > i; k--) { //back bubble
                        int det = comparator.compare(arr[k], arr[k - 1]);
                        if (det < 0) {
                            swap(arr, k, k - 1);
                            swapped = true;
                        }
                    }
                }
            }
        }
    }

    /**
     * Swaps 2 elements
     * @param arr array that needs swapping
     * @param i position of lst element
     * @param j position of 2nd element
     * @param <T> Generic Type
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * See the PDF for more info on this sort.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator)
        throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Empty Array or null comparator");
        } else {
            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0; j--) {
                    int det = comparator.compare(arr[j], arr[j - 1]);
                    if (det < 0) {
                        swap(arr, j, j - 1);
                    }
                }
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator)
        throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Empty Array or null comparator");
        } else {
            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int det = comparator.compare(arr[i], arr[j]);
                    if (det > 0) {
                        swap(arr, i, j);
                    }
                }
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand)
            throws IllegalArgumentException {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException(
                    "Empty Array or null comparator");
        } else {
            quickSort(arr, 0, arr.length - 1, comparator, rand);
        }
    }

    /**
     * Helper QuickSort method
     * @param arr array to sort
     * @param lIdx left index
     * @param rIdx right index
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param <T> Generic Data Type
     */
    private static <T> void quickSort(T[] arr, int lIdx, int rIdx,
                                      Comparator<T> comparator, Random rand) {
        int i = lIdx;
        int j = rIdx;
        int pivotIdx = rand.nextInt(rIdx - lIdx) + lIdx;
        while (i <= j) {
            int det = comparator.compare(arr[i], arr[pivotIdx]);
            while (det < 0) {
                i++;
                det = comparator.compare(arr[i], arr[pivotIdx]);
            }
            det = comparator.compare(arr[j], arr[pivotIdx]);
            while (det > 0) {
                j--;
                det = comparator.compare(arr[j], arr[pivotIdx]);
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
            if (lIdx < j) {
                quickSort(arr, lIdx, j, comparator, rand);
            }
            if (i < rIdx) {
                quickSort(arr, i, rIdx, comparator, rand);
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * ********************* IMPORTANT ************************
     * FAILURE TO DO SO MAY CAUSE ClassCastException AND CAUSE
     * YOUR METHOD TO FAIL ALL THE TESTS FOR MERGE SORT
     * ********************************************************
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator)
        throws IllegalArgumentException {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException(
                    "Empty Array or null comparator");
        } else {
            Object[] tmp = new Object[arr.length];
            T[] helper = (T[]) tmp;
            mergeSort(arr, helper, comparator, 0, arr.length - 1);
        }
    }

    /**
     * MergeSort Helper
     * @param arr array being sorted
     * @param tmp secondary array to assist sorting
     * @param comparator the Comparator used to compare the data in arr
     * @param low left bound
     * @param high right bound
     * @param <T> Generic Data Type
     */
    private static <T> void mergeSort(T[] arr, T[] tmp,
                                      Comparator<T> comparator,
                                      int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, tmp, comparator, low, mid);
            mergeSort(arr, tmp, comparator, mid + 1, high);
            merge(arr, tmp, comparator, low, mid, high);
        }
    }

    /**
     * Performs merge for mergeSort
     * @param arr array being sorted
     * @param tmp secondary array to assist sorting
     * @param comparator the Comparator used to compare the data in arr
     * @param low left bound
     * @param mid middle of array
     * @param high right bound
     * @param <T> Generic Data Type
     */
    private static <T> void merge(T[] arr, T[] tmp,
                                  Comparator comparator,
                                  int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            tmp[i] = arr[i];
        }
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            int det = comparator.compare(tmp[i], tmp[j]);
            if (det <= 0) {
                arr[k] = tmp[i];
                i++;
            } else {
                arr[k] = tmp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = tmp[i];
            k++;
            i++;
        }
    }


    /**
     * Implement radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code!
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Any duplicates in the array should be in the same relative position after
     * sorting as they were before sorting. (stable)
     *
     * DO NOT USE {@code Math.pow()} in your sort. Instead, if you need to, use
     * the provided {@code pow()} method below.
     *
     * You may use an ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     * @return the sorted array
     */
    public static int[] radixSort(int[] arr) throws IllegalArgumentException {
        if (arr == null) {
            throw new IllegalArgumentException("Null Array");
        } else {
            LinkedList<Integer>[] buckets = new LinkedList[11];
            boolean sorted = false;
            int div = 0;
            while (!sorted) {
                for (int x : arr) {
                    int bucket = (x / pow(10, div)) % 10;
                    if (bucket < 0) {
                        bucket = 0;
                    } else {
                        bucket++;
                    }
                    if (buckets[bucket] == null) {
                        buckets[bucket] = new LinkedList<Integer>();
                    }
                    buckets[bucket].add(x);
                }
                int size = 0;
                if (buckets[0] != null && buckets[1] != null) {
                    size = buckets[0].size() + buckets[1].size();
                } else if (buckets[1] != null) {
                    size = buckets[1].size();
                } else if (buckets[0] != null) {
                    size = buckets[0].size();
                }
                if (size == arr.length) {
                    sorted = true;
                }

                int arrPos = 0;
                int x = 0;
                while (x < 10) {
                    if (buckets[x] != null) {
                        while (buckets[x].peekFirst() != null) {
                            arr[arrPos] = buckets[x].poll();
                            arrPos++;
                        }
                        x++;
                    } else {
                        x++;
                    }
                }
                div++;
            }
            return arr;
        }
    }

    /**
     * Calculate the result of a number raised to a power. Use this method in
     * your radix sort instead of {@code Math.pow()}. DO NOT MODIFY THIS METHOD.
     *
     * @param base base of the number
     * @param exp power to raise the base to. Must be 0 or greater.
     * @return result of the base raised to that power.
     */
    private static int pow(int base, int exp) {
        if (exp < 0) {
            throw new IllegalArgumentException("Invalid exponent.");
        } else if (base == 0 && exp == 0) {
            throw new IllegalArgumentException(
                    "Both base and exponent cannot be 0.");
        } else if (exp == 0) {
            return 1;
        } else if (exp == 1) {
            return base;
        }
        int halfPow = pow(base, exp / 2);
        if (exp % 2 == 0) {
            return halfPow * halfPow;
        } else {
            return halfPow * pow(base, (exp / 2) + 1);
        }
    }
}

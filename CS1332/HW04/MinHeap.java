import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a Heap.
     */
    public MinHeap() {
        Comparable[] tmp = new Comparable[STARTING_SIZE];
        backingArray = (T[]) tmp;
    }

    @Override
    public void add(T item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Given item is null");
        } else if (backingArray[1] == null) {
            size++;
            backingArray[1] = item;
        } else if (size == (backingArray.length - 1)) {
            resize(backingArray.length * 2);
            size++;
            backingArray[size] = item;
            heapify();
        } else {
            size++;
            backingArray[size] = item;
            heapify();
        }
    }

    /**
     * bubble up heap
     */
    private void heapify() {
        int i = size;
        int check = backingArray[i].compareTo(backingArray[i / 2]);
        while (check < 0 && i > 1) {
            T tmp = backingArray[i / 2];
            backingArray[i / 2] = backingArray[i];
            backingArray[i] = tmp;
            i = i / 2;
            if (i / 2 > 0) {
                check = backingArray[i].compareTo(backingArray[i / 2]);
            } else {
                check = -1;
            }
        }
    }

    /**
     * resize the heap
     * @param x new size
     */
    private void resize(int x) {
        T[] tmp = (T[]) new Comparable[x];
        for (int y = 0; y <= size; y++) {
            tmp[y] = backingArray[y];
        }
        backingArray = tmp;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty Heap");
        } else {
            T remove = backingArray[1];
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            if (size != 1) {
                backHeapify();
            }
            return remove;
        }
    }

    /**
     * bubble down heap
     */
    private void backHeapify() {
        int i = 1;
        int check;
        T tmp;
        int newPos;
        while (i < size && i > 0) {
            if ((i * 2) + 1 <= size) {
                T tmp1 = backingArray[i * 2];
                T tmp2 = backingArray[(i * 2) + 1];
                int compare = tmp2.compareTo(tmp1);
                if (compare > 0) {
                    check = backingArray[i].compareTo(backingArray[(i * 2)]);
                    tmp = tmp1;
                    newPos = i * 2;
                } else {
                    check = backingArray[i].compareTo(backingArray[(i * 2)
                            + 1]);
                    tmp = tmp2;
                    newPos = (i * 2) + 1;
                }
            } else if (i * 2 <= size) {
                check = backingArray[i].compareTo(backingArray[i * 2]);
                tmp = backingArray[i * 2];
                newPos = i * 2;
            } else {
                check = 0;
                tmp = null;
                newPos = 0;
            }
            if (check > 0) {
                backingArray[newPos] = backingArray[i];
                backingArray[i] = tmp;
            }
            i = newPos;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Comparable[] tmpArray = new Comparable[STARTING_SIZE];
        backingArray = (T[]) tmpArray;
        size = 0;
    }

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing array
     */
    public Comparable[] getBackingArray() {
        return backingArray;
    }

}

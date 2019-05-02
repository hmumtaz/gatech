import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularDoublyLinkedList
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */
public class CircularDoublyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Creates an empty circular doubly-linked list.
     */
    public CircularDoublyLinkedList() {

    }

    /**
     * Creates a circular doubly-linked list with
     * {@code data} added to the list in order.
     * @param data The data to be added to the LinkedList.
     * @throws java.lang.IllegalArgumentException if {@code data} is null or
     * any item in {@code data} is null.
     */
    public CircularDoublyLinkedList(T[] data) {
        for (T dat : data) {
            addToBack(dat);
        }
    }

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data");
        } else if (size == 0) {
            head = new LinkedListNode<>(data);
            head.setPrevious(head);
            head.setNext(head);
            size++;
        } else if (size == 1) {
            LinkedListNode<T> oldHead = head;
            LinkedListNode<T> newHead = new LinkedListNode<>(data);
            oldHead.setPrevious(newHead);
            oldHead.setNext(newHead);
            head = new LinkedListNode<>(data, oldHead, oldHead);
            size++;
        } else {
            head = new LinkedListNode<>(data, head.getPrevious(), head);
            head.getNext().setPrevious(head);
            head.getPrevious().setNext(head);
            size++;
        }
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data");
        } else if (size == 0) {
            head = new LinkedListNode<>(data);
            head.setPrevious(head);
            head.setNext(head);
            size++;
        } else if (size == 1) {
            LinkedListNode<T> tail = new LinkedListNode<T>(data, head, head);
            head.setPrevious(tail);
            head.setNext(tail);
            size++;
        } else {
            head.setPrevious(new LinkedListNode<>(data, head.getPrevious(),
                head));
            head.getPrevious().getPrevious().setNext(head.getPrevious());
            size++;
        }
    }

    @Override
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException,
        IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else if (data == null) {
            throw new IllegalArgumentException("Invalid Data");
        } else if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> current = head;
            int curIdx = 0;
            while (curIdx < index) {
                current = current.getNext();
                curIdx += 1;
            }
            current = new LinkedListNode<>(data, current.getPrevious(),
                current);
            current.getNext().setPrevious(current);
            current.getPrevious().setNext(current);
            size++;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else if (index == 0) {
            return head.getData();
        } else if (index == (size - 1)) {
            return head.getPrevious().getData();
        } else {
            LinkedListNode<T> current = head;
            int curIdx = 0;
            while (curIdx < index) {
                current = current.getNext();
                curIdx += 1;
            }
            return current.getData();
        }

    }

    @Override
    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            T retData = head.getData();
            clear();
            return retData;

        } else if (size == 2) {
            T retData = head.getData();
            LinkedListNode<T> aHead = new LinkedListNode<>(head
                .getNext().getData());
            head = new LinkedListNode<T>(aHead.getData(), aHead, aHead);
            size--;
            return retData;
        } else {
            T retData = head.getData();
            head = new LinkedListNode<>(head.getNext().getData(), head
                .getPrevious(), head.getNext().getNext());
            head.getNext().setPrevious(head);
            head.getPrevious().setNext(head);
            size--;
            return retData;
        }
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            T retData = head.getData();
            clear();
            return retData;
        } else if (size == 2) {
            T retData = head.getPrevious().getData();
            head = new LinkedListNode<>(head.getData());
            head.setPrevious(head);
            head.setNext(head);
            size--;
            return retData;
        } else {
            T retData = head.getPrevious().getData();
            head.setPrevious(new LinkedListNode<>(head.getPrevious()
                .getPrevious().getData(), head.getPrevious().getPrevious()
                .getPrevious(), head));
            head.getPrevious().getPrevious().setNext(head.getPrevious());
            size--;
            return retData;
        }
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == (size - 1)) {
            return removeFromBack();
        } else {
            LinkedListNode<T> current = head;
            int curIdx = 0;
            while (curIdx < index) {
                current = current.getNext();
                curIdx += 1;
            }
            T retData = current.getData();
            current = new LinkedListNode<>(current.getNext().getData(),
                current.getPrevious(), current.getNext().getNext());
            current.getNext().setPrevious(current);
            current.getPrevious().setNext(current);
            size--;
            return retData;
        }
    }

    @Override
    public int removeFirstOccurrence(T data) throws NoSuchElementException,
        IllegalArgumentException {
        int curIdx = 0;
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data");
        } else if (data == head.getData()) {
            removeFromFront();
            return 0;
        } else {
            LinkedListNode<T> current = head;
            while (curIdx < size) {
                if (current.getData() == data) {
                    current = new LinkedListNode<>(current.getNext().getData(),
                        current.getPrevious(), current.getNext().getNext());
                    current.getPrevious().setNext(current);
                    current.getNext().setPrevious(current);
                    size--;
                    return (curIdx);
                } else {
                    current = current.getNext();
                    curIdx++;
                }
            }
        }
        throw new NoSuchElementException("Element not in data set");
    }

    @Override
    public boolean removeAllOccurrences(T data)
        throws IllegalArgumentException {
        int oldSize = size;
        int curIdx = 0;
        if (data == null) {
            throw new IllegalArgumentException("Invalid Data");
        } else {
            LinkedListNode<T> current = head;
            while (curIdx < size) {
                if (data == head.getData()) {
                    removeFromFront();
                } else if (data == head.getPrevious().getData()) {
                    removeFromBack();
                } else if (current.getData() == data) {
                    current = new LinkedListNode<>(current.getNext().getData(),
                        current.getPrevious(), current.getNext().getNext());
                    current.getNext().setPrevious(current);
                    current.getPrevious().setNext(current);
                    size--;
                } else {
                    curIdx++;
                }
                current = current.getNext();
            }
        }
        return (oldSize - size != 0);
    }

    @Override
    public Object[] toArray() {
        Object[] nodeArray = new Object[size];
        int curIdx = 0;
        LinkedListNode<T> current = head;
        while (curIdx < size) {
            nodeArray[curIdx] = current.getData();
            current = current.getNext();
            curIdx++;
        }
        return nodeArray;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = new LinkedListNode<>(null);
        size = 0;
    }

    /* DO NOT MODIFY THIS METHOD */
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }
}

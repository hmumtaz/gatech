import java.util.NoSuchElementException;

/**
 * Your implementation of a SinglyLinkedList
 *
 * @author Hussain Mumtaz
 * @version 1.0
 */

public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addToFront(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            head = new LinkedListNode<>(data, head);
            size++;
        }
    }

    @Override
    public void addAtIndex(int index, T data) throws IndexOutOfBoundsException,
        IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (index == size) {
            addToBack(data); //method increments size
        } else if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            LinkedListNode<T> current = head;
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            int curIdx = 1;
            while (curIdx < index) {
                current = current.getNext();
                curIdx += 1;
            }
            LinkedListNode<T> next = current.getNext();
            current.setNext(newNode);
            newNode.setNext(next);
            size++;
        }
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return head.getData();
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
        } else {
            LinkedListNode<T> remove = head;
            head = head.getNext();
            size--;
            return remove.getData();
        }
    }

    @Override
    public T removeAtIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return removeFromFront(); //removeFromFront decrements size
        } else {
            LinkedListNode<T> current = head;
            int curIdx = 1;
            while (curIdx < index) {
                current = current.getNext();
                curIdx += 1;
            }
            LinkedListNode<T> remove = current.getNext();
            current.setNext(remove.getNext());
            size--;
            return remove.getData();
        }
    }

    @Override
    public void addToBack(T data) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            LinkedListNode<T> newNode = new LinkedListNode<>(data);
            if (isEmpty()) {
                head = newNode;
                size++;
            } else {
                LinkedListNode<T> current = head;
                int curIdx = 1;
                while (curIdx < size) {
                    current = current.getNext();
                    curIdx += 1;
                }
                current.setNext(newNode);
                size++;
            }
        }
    }

    @Override
    public T removeFromBack() {
        if (isEmpty()) {
            return null;
        } else if (size == 1) {
            LinkedListNode<T> tmp = head;
            removeFromFront();
            return tmp.getData();
        } else {
            LinkedListNode<T> current = head;
            int curIdx = 2;
            while (curIdx < size) {
                current = current.getNext();
                curIdx += 1;
            }
            LinkedListNode<T> remove = current.getNext();
            current.setNext(null);
            size--;
            return remove.getData();
        }
    }

    @Override
    public int removeFirstOccurrence(T data) throws NoSuchElementException,
        IllegalArgumentException {
        int curIdx = 0;
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (curIdx == size) {
            throw new NoSuchElementException();
        } else if (data == head.getData()) {
            removeFromFront(); //method decrements size
            return 0;
        } else {
            LinkedListNode<T> previous = head;
            LinkedListNode<T> current = previous.getNext();
            while (curIdx < size) {
                if (current.getData() == data) {
                    previous.setNext(current.getNext());
                    return (curIdx + 1);
                } else {
                    previous = previous.getNext();
                    if (current.getNext() == null) {
                        throw new NoSuchElementException();
                    } else {
                        current = current.getNext();
                    }
                    curIdx++;
                }
            }
        }
        throw new NoSuchElementException();
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
        head = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}

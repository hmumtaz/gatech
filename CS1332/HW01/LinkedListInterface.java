/**
 * This interface describes the public methods needed for
 * SinglyLinkedList, which should be singly linked and should
 * have a head pointer.
 */
public interface LinkedListInterface<T> {

    /**
     * Adds an element to the specified index, i
     * Runtime: O(n)
     *
     * Throws {@code java.lang.IndexOutOfBoundsException} if i < 0 || i > size.
     * Throws {@code java.lang.IllegalArgumentException} if data is null.
     *
     * @param i    The index of the new element.
     * @param data Generic object
     */
    public void addAtIndex(int i, T data);

    /**
     * Add a new node to the front of your linked list
     * Runtime: O(1)
     *
     * Throws {@code java.lang.IllegalArgumentException} if data is null.
     *
     * @param data The data of the new node.
     */
    public void addToFront(T data);

    /**
     * Add a new node to the back of your linked list
     * Runtime: O(n)
     *
     * Throw {@code java.lang.IllegalArgumentException} if data is null.
     *
     * @param data The data that the new node should hold.
     */
    public void addToBack(T data);

    /**
     * Returns the element at the given index, i
     * Runtime: O(n)
     *
     * Throw {@code java.lang.IndexOutOfBoundsException} if i < 0 || i >= size.
     *
     * @param i The index of the element
     * @return The object stored at that index.
     */
    public T get(int i);

    /**
     * Removes and returns the element at the given index, i
     * Runtime: O(n)
     *
     * Throw {@code java.lang.IndexOutOfBoundsException} if i < 0 || i >= size.
     *
     * @param i The index of the element
     * @return The object that was formerly at that index.
     */
    public T removeAtIndex(int i);

    /**
     * Remove the node at the head of the list and return the data from it.
     * If the list is empty, return null.
     * Runtime: O(1).
     *
     * @return The data from the node at the head or null.
     */
    public T removeFromFront();

    /**
     * Remove the node at the tail of the list and return the data from it.
     * If the list is empty, return null.
     * Runtime: O(n).
     *
     * @return The data from the last node or null.
     */
    public T removeFromBack();

    /**
     * Remove the first occurrence of the data object in the list.
     * Runtime: O(n).
     *
     * Throws {@code java.util.NoSuchElementException} if data not in list.
     * Throws {@code java.lang.IllegalArgumentException} if data is null.
     *
     * @param data The data to be removed from the list.
     * @return the index of the removed node.
     */
    public int removeFirstOccurrence(T data);

    /**
     * Return the linked list represented as an array of objects.
     * Runtime: O(n).
     *
     * @return A copy of the linked list data as an array.
     */
    public Object[] toArray();

    /**
     * Return a boolean value representing whether or not the list is empty.
     * Runtime: O(1).
     *
     * @return True if empty. False otherwise.
     */
    public boolean isEmpty();

    /**
     * Return the size of the list as an integer.
     * Runtime: O(1).
     *
     * @return The size of the list.
     */
    public int size();

    /**
     * Clear the list.
     * Runtime: O(1).
     */
    public void clear();

    /**
     * Reference to the head node of the linked list.
     *
     * @return Node representing the head of the linked list.
     */
    public LinkedListNode<T> getHead();
}

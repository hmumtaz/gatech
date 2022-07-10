/**
 * LinkedListNode
 */
public class LinkedListNode<T> {

    private T data;
    private LinkedListNode<T> next;

    /**
     * Create a new LinkedListNode with the given data object and next node.
     *
     * @param data data to store in the node
     * @param next refrence to next node
     */
    public LinkedListNode(T data, LinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Create a new LinkedListNode with the given data object.
     *
     * @param data data to store in this node
     */
    public LinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Get node data
     *
     * @return data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Get next node.
     *
     * @return next node.
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Set next node.
     *
     * @param next new next node.
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }

}

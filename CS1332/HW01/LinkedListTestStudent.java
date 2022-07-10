import org.junit.Rule;
import org.junit.Test;
import org.junit.Before;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.NoSuchElementException;

public class LinkedListTestStudent {

    private LinkedListInterface<String> list;

    @Rule
    public Timeout timeout = Timeout.millis(200);

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test
    public void testAddStringsAtIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        assertNull(current.getNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddStringAtIndexGreaterThanSize() {
        list.addAtIndex(1, "does not add");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddStringAtIndexLessThanZero() {
        list.addAtIndex(-1, "does not add");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullAtIndex() {
        list.addAtIndex(0, null);
    }

    @Test
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();
        assertNull(current);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullFront() {
        list.addToFront(null);
    }

    @Test
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());
        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());
        current = current.getNext();
        assertNull(current);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullBack() {
        list.addToBack(null);
    }

    @Test
    public void testGet() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        String current = list.get(0);
        assertEquals("5a", current);
        current = list.get(1);
        assertEquals("4a", current);
        current = list.get(2);
        assertEquals("3a", current);
        current = list.get(3);
        assertEquals("2a", current);
        current = list.get(4);
        assertEquals("1a", current);
        current = list.get(5);
        assertEquals("0a", current);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetGreaterThanSize() {
        list.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLessThanZero() {
        list.get(-1);
    }

    @Test
    public void testRemoveStringAtIndex() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        LinkedListNode<String> current = list.getHead();
        assertEquals("0a", current.getData());
        current = current.getNext();
        assertEquals("1a", current.getData());
        current = current.getNext();
        assertEquals("3a", current.getData());
        current = current.getNext();
        assertEquals("4a", current.getData());
        current = current.getNext();
        assertEquals("5a", current.getData());
        current = current.getNext();
        assertNull(current);
    }

    @Test
    public void testRemoveAllAtIndex() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeAtIndex(0)); // 1a 2a 3a 4a 5a
        assertEquals(5, list.size());
        assertEquals("1a", list.removeAtIndex(0)); // 2a 3a 4a 5a
        assertEquals(4, list.size());
        assertEquals("2a", list.removeAtIndex(0)); // 3a 4a 5a
        assertEquals(3, list.size());
        assertEquals("3a", list.removeAtIndex(0)); // 4a 5a
        assertEquals(2, list.size());
        assertEquals("4a", list.removeAtIndex(0)); // 5a
        assertEquals(1, list.size());
        assertEquals("5a", list.removeAtIndex(0));
        assertEquals(0, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNull(current);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexGreaterThanSize() {
        list.removeAtIndex(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexLessThanZero() {
        list.removeAtIndex(-1);
    }

    @Test
    public void testRemoveAtFront() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeFromFront()); // 1a 2a 3a 4a 5a
        assertEquals(5, list.size());
        assertEquals("1a", list.removeFromFront()); // 2a 3a 4a 5a
        assertEquals(4, list.size());
        assertEquals("2a", list.removeFromFront()); // 3a 4a 5a
        assertEquals(3, list.size());
        assertEquals("3a", list.removeFromFront()); // 4a 5a
        assertEquals(2, list.size());
        assertEquals("4a", list.removeFromFront()); // 5a
        assertEquals(1, list.size());
        assertEquals("5a", list.removeFromFront());
        assertEquals(0, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNull(current);
    }

    @Test
    public void testRemoveAtFrontKeepsReturningNull() {
        assertEquals(0, list.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(null, list.removeFromFront());
        }
    }

    @Test
    public void testRemoveAtBack() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("5a", list.removeFromBack()); // 0a 1a 2a 3a 4a
        assertEquals(5, list.size());
        assertEquals("4a", list.removeFromBack()); // 0a 1a 2a 3a
        assertEquals(4, list.size());
        assertEquals("3a", list.removeFromBack()); // 0a 1a 2a
        assertEquals(3, list.size());
        assertEquals("2a", list.removeFromBack()); // 0a 1a
        assertEquals(2, list.size());
        assertEquals("1a", list.removeFromBack()); // 0a
        assertEquals(1, list.size());
        assertEquals("0a", list.removeFromBack());
        assertEquals(0, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNull(current);
    }

    @Test
    public void testRemoveAtBackKeepsReturningNull() {
        assertEquals(0, list.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(null, list.removeFromBack());
        }
    }

    @Test
    public void testRemoveFirstOccurenceOnlyRemovesFirst() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "0a");
        list.addAtIndex(3, "1a");
        list.addAtIndex(4, "0a");
        list.addAtIndex(5, "1a"); // 0a 1a 0a 1a 0a 1a

        assertEquals(6, list.size());
        assertEquals(1, list.removeFirstOccurrence("1a")); // 0a 0a 1a 0a 1a
        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();

        assertEquals(2, list.removeFirstOccurrence("1a")); // 0a 0a 0a 1a
        assertEquals(4, list.size());
        current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("0a", current.getData());
        current = current.getNext();

        assertNotNull(current);
        assertEquals("1a", current.getData());
        current = current.getNext();
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstOccurenceDoesNotExist() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "0a");
        list.addAtIndex(3, "1a");
        list.addAtIndex(4, "0a");
        list.addAtIndex(5, "1a"); // 0a 1a 0a 1a 0a 1a

        list.removeFirstOccurrence("2a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFirstOccurenceNull() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "0a");
        list.addAtIndex(3, "1a");
        list.addAtIndex(4, "0a");
        list.addAtIndex(5, "1a"); // 0a 1a 0a 1a 0a 1a

        list.removeFirstOccurrence(null);
    }

    @Test
    public void testIsEmptyOnEmptyList() {
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testIsEmptyOnNonEmptyList() {
        list.addToFront("0a");
        assertEquals(false, list.isEmpty());
    }

    @Test
    public void testClear() {
        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a
        assertEquals(false, list.isEmpty());
        assertEquals(6, list.size());

        list.clear();
        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
        LinkedListNode<String> current = list.getHead();
        assertEquals(null, current);

    }

    @Test
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }
}

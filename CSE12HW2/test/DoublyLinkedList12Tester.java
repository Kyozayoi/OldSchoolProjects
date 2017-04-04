/*
 Anthony Shih
 A11295870
 cs12wei
 */
package hw2;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ListIterator;

public class DoublyLinkedList12Tester {

    private DoublyLinkedList12<Integer> empty;
    private DoublyLinkedList12<Integer> one;
    private DoublyLinkedList12<Integer> several;
    private DoublyLinkedList12<String> slist;
    static final int DIM = 5;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and a
     * list with several entries (0,1,2)
     */
    @Before
    public void setUp() {
        empty = new DoublyLinkedList12<>();
        one = new DoublyLinkedList12<>();
        one.add(0, 0);
        several = new DoublyLinkedList12<>();
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--) {
            several.add(0, i);
        }

        // List: "First","Last"
        slist = new DoublyLinkedList12<>();
        slist.add(0, "First");
        slist.add(1, "Last");
    }

    /**
     * Test if heads of the lists are correct
     */
    @Test
    public void testGetHead() {
        assertEquals("Check 0", new Integer(0), one.get(0));
        assertEquals("Check 0", new Integer(1), several.get(0));
        assertEquals("Check slist", "First", slist.get(0));
    }

    /**
     * Test if size of lists are correct
     */
    @Test
    public void testListSize() {
        assertEquals("Check Empty Size", 0, empty.size());
        assertEquals("Check One Size", 1, one.size());
        assertEquals("Check Several Size", DIM, several.size());
        assertEquals("CHeck slist", 2, slist.size());
    }

    /**
     * Test setting a specific entry
     */
    @Test
    public void testSet() {
        slist.set(1, "Final");
        assertEquals("Setting specific value", "Final", slist.get(1));
        one.set(0, 4);
        assertEquals("Setting one", 4, (long) one.get(0));
    }

    /**
     * Test isEmpty
     */
    @Test
    public void testEmpty() {
        assertTrue("empty is empty", empty.isEmpty());
        assertTrue("one is not empty", !one.isEmpty());
        assertTrue("several is not empty", !several.isEmpty());
        assertTrue("slist is not empty", !slist.isEmpty());
    }

    /**
     * Test out of bounds exception on get
     */
    @Test
    public void testGetException() {
        try {
            empty.get(0);
            fail("Should have generated an exception");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            several.add(100, 99);
            fail("Should have generated an exception");
        } catch (IndexOutOfBoundsException e) {

        }
        try {
            slist.add(0, null);
            fail("Should have generated an exception");
        } catch (NullPointerException e) {

        }
    }

    /**
     * Test iterator on empty list and several list
     */
    @Test
    public void testIterator() {
        int counter = 0;
        ListIterator<Integer> iter;
        for (iter = empty.listIterator(); iter.hasNext();) {
            fail("Iterating empty list and found element");
        }
        for (iter = several.listIterator(); iter.hasNext(); iter.next()) {
            counter++;
        }
        assertEquals("Iterator several count", DIM, counter);
    }
}

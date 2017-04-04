/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleEndedLLTester {

    private DoubleEndedLL testSubject;
    private DoubleEndedLL guineaPig;
    
    /*
    Preps two DELL for the tests
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Before
    public void setUp() throws Exception{
        testSubject = new DoubleEndedLL();
        guineaPig = new DoubleEndedLL();
        guineaPig.addFirst(15);
        //guineaPig's purpose is to test nonempty lists
    }
    
    /*
    Tests isEmpty method to check if list is empty
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTisEmpty() {
        assertTrue(testSubject.isEmpty());
        assertFalse(guineaPig.isEmpty());
    }
    
    /*
    Tests size method to return proper size
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTsize() {
        assertEquals(0, testSubject.size());
        assertEquals(1, guineaPig.size());
    }
    
    /*
    Tests addFirst and removeFirst method tests
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTaddFirst(){
        guineaPig.addFirst(5);
        guineaPig.addFirst(19);
        assertEquals(19, guineaPig.removeFirst());
    }
    
    /*
    Tests addLast and removeLast method tests
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTaddLast() {
        guineaPig.addFirst(11);
        guineaPig.addLast(6);
        guineaPig.addLast(16);
        assertEquals(16, guineaPig.removeLast());
        assertEquals(6, guineaPig.removeLast());
    }
    
}

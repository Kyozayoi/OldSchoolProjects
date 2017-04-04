/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw4;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyStackTester {

    private MyStack testSubject;
    private MyStack guineaPig;
    
    /*
    Creates two stacks for testing
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Before
    public void setUp() throws Exception{
        testSubject = new MyStack();
        guineaPig = new MyStack();
        guineaPig.addElement(24);
    }
    
    /*
    Tests isEmpty method to check if stack is empty
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
    Tests addElement and removeElement method tests
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTaddElement(){
        testSubject.addElement(5);
        testSubject.addElement(15);
        assertEquals(15, testSubject.removeElement());
        assertEquals(24, guineaPig.removeElement());
    }
    
    /*
    Tests the peek method
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    @Test
    public void TESTpeekElement() {
        testSubject.addElement(7);
        testSubject.addElement(17);
        assertEquals(17, testSubject.peekElement());
        assertEquals(24, guineaPig.peekElement());
    }
    
}

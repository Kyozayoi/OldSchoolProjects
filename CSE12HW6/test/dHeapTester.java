/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw6;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class dHeapTester {
    
    private dHeap testHeapa;
    private dHeap testHeapb;
    
    /*
    Prepare two dHeaps for testing
    @author Anthony Shih
    @version 1.0
    @since 02-19-2016
    */
    @Before
    public void setUp() {
        testHeapa = new dHeap(13, 3);
        testHeapb = new dHeap(15);
        for(int j= 1; j < 10; j++){
            testHeapa.add(j);
        }
        for(int i = 1; i < 12; i++){
            testHeapb.add(i);
        }
    }
    
    /*
    Testing Add method
    @author Anthony Shih
    @version 1.0
    @since 02-19-2016
    */
    @Test
    public void testAdd(){
        testHeapa.add(11);
        testHeapa.add(4);
        testHeapb.add(14);
        testHeapb.add(0);
        testHeapa.printHeap();
        testHeapb.printHeap();
    }
    
    /*
    Testing Size method
    @author Anthony Shih
    @version 1.0
    @since 02-19-2016
    */
    @Test
    public void testSize(){
        assertEquals(9, testHeapa.size());
        assertEquals(11, testHeapb.size());
    }
    
    /*
    Testing RemoveSmallest method
    @author Anthony Shih
    @version 1.0
    @since 02-19-2016
    */
    @Test
    public void testRemoveSmallest(){
        assertEquals(1, testHeapa.removeSmallest());
        assertEquals(1, testHeapb.removeSmallest());
    }   
}

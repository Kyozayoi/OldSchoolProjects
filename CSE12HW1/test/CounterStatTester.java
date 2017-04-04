/*
 Anthony Shih
 A11295870
 cs12wei
 */

package hw1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CounterStatTester {
    
    private CounterStat stat;

    /* this sets up the test fixture. JUnit invokes this method before
     every testXXX method.  The @Before tag tells JUnit to run this method 
     before each test */
    @Before
    public void setUp() throws Exception {
        stat = new CounterStat(1, 2, 3);
    }

    /* The @Test tag tells JUnit this is a test to run */
    @Test
    public void testgetTotalGames() {
        System.out.println("Checking getTotalGames");
        assertEquals(6, stat.getTotalGames());
    }

    @Test
    public void testIncrements() {
        System.out.println("Checking Proper Increment");
        stat.incrementComputerWins();
        assertEquals(7, stat.getTotalGames());
        stat.incrementUserWins();
        assertEquals(8, stat.getTotalGames());
        stat.incrementTies();
        assertEquals(9, stat.getTotalGames());
    }

    @Test
    public void testReset() {
        System.out.println("Checking Reset");
        stat.reset();
        assertEquals(0, stat.getTotalGames());
    }
    
    @Test
    public void testAverageGames() {
        System.out.println("Checking Average Games");
        assertEquals(16, stat.averageGames(0));
        assertEquals(33, stat.averageGames(1));
        assertEquals(50, stat.averageGames(2));
        assertEquals(-1, stat.averageGames(3));
    }
    
    @Test
    public void testResetWrong() {
        System.out.println("Checking Reset Wrong");
        stat.resetWrong();
        assertEquals(0, stat.getTotalGames());
    }
    
}
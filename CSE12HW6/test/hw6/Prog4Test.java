/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw6;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Anthony
 */
public class Prog4Test {
    
    public Prog4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class Prog4.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Prog4.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserInput method, of class Prog4.
     */
    @Test
    public void testGetUserInput() {
        System.out.println("getUserInput");
        CreditCardNumber expResult = null;
        CreditCardNumber result = Prog4.getUserInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayCred method, of class Prog4.
     */
    @Test
    public void testDisplayCred() {
        System.out.println("displayCred");
        String ch = "";
        CreditCardNumber cred = null;
        Prog4.displayCred(ch, cred);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputArray method, of class Prog4.
     */
    @Test
    public void testGetInputArray() {
        System.out.println("getInputArray");
        CreditCardNumber[] expResult = null;
        CreditCardNumber[] result = Prog4.getInputArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayArray method, of class Prog4.
     */
    @Test
    public void testDisplayArray() {
        System.out.println("displayArray");
        CreditCardNumber[] objArray = null;
        Prog4.displayArray(objArray);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tryAnother method, of class Prog4.
     */
    @Test
    public void testTryAnother() {
        System.out.println("tryAnother");
        CreditCardNumber[] cred = null;
        boolean expResult = false;
        boolean result = Prog4.tryAnother(cred);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

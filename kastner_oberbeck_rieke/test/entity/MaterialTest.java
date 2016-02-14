/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author woors
 */
public class MaterialTest {
    
    public MaterialTest() {
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
     * Test of getName method, of class Material.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Material instance = new Material();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Material.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "test";
        Material instance = new Material();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getAnzahl method, of class Material.
     */
    @Test
    public void testGetAnzahl() {
        System.out.println("getAnzahl");
        Material instance = new Material();
        int expResult = 0;
        int result = instance.getAnzahl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnzahl method, of class Material.
     */
    @Test
    public void testSetAnzahl() {
        System.out.println("setAnzahl");
        int anzahl = 320;
        Material instance = new Material();
        instance.setAnzahl(anzahl);
        assertEquals(anzahl, instance.getAnzahl());
    }

    /**
     * Test of getId method, of class Material.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Material instance = new Material();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }
    
}

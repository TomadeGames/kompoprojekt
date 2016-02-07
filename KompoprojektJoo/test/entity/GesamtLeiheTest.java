/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
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
public class GesamtLeiheTest {
    
    public GesamtLeiheTest() {
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
     * Test of getId method, of class GesamtLeihe.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        GesamtLeihe instance = new GesamtLeihe();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class GesamtLeihe.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 1L;
        GesamtLeihe instance = new GesamtLeihe();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getName method, of class GesamtLeihe.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        GesamtLeihe instance = new GesamtLeihe();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class GesamtLeihe.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "test";
        GesamtLeihe instance = new GesamtLeihe();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getEinzelleihen method, of class GesamtLeihe.
     */
    @Test
    public void testGetEinzelleihen() {
        System.out.println("getEinzelleihen");
        GesamtLeihe instance = new GesamtLeihe();
        List<Leihe> expResult = null;
        List<Leihe> result = instance.getEinzelleihen();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEinzelleihen method, of class GesamtLeihe.
     */
    @Test
    public void testSetEinzelleihen() {
        System.out.println("setEinzelleihen");
        List<Leihe> einzelleihen = new ArrayList<>();
        Leihe l = new Leihe();
        einzelleihen.add(l);
        GesamtLeihe instance = new GesamtLeihe();
        instance.setEinzelleihen(einzelleihen);
        assertEquals(einzelleihen, instance.getEinzelleihen());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LeiheTest {
    
    public LeiheTest() {
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
     * Test of getId method, of class Leihe.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Leihe instance = new Leihe();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Leihe.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 1L;
        Leihe instance = new Leihe();
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    /**
     * Test of getMaterial method, of class Leihe.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        Leihe instance = new Leihe();
        Material expResult = null;
        Material result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaterial method, of class Leihe.
     */
    @Test
    public void testSetMaterial() {
        System.out.println("setMaterial");
        Material material = new Material();
        material.setAnzahl(20);
        material.setName("testMaterial");
        Leihe instance = new Leihe();
        instance.setMaterial(material);
        assertEquals(material, instance.getMaterial());
    }

    /**
     * Test of getAnzahl method, of class Leihe.
     */
    @Test
    public void testGetAnzahl() {
        System.out.println("getAnzahl");
        Leihe instance = new Leihe();
        int expResult = 0;
        int result = instance.getAnzahl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnzahl method, of class Leihe.
     */
    @Test
    public void testSetAnzahl() {
        System.out.println("setAnzahl");
        int anzahl = 0;
        Leihe instance = new Leihe();
        instance.setAnzahl(anzahl);
        assertEquals(anzahl, instance.getAnzahl());
    }

    /**
     * Test of getStartDatum method, of class Leihe.
     */
    @Test
    public void testGetStartDatum() {
        System.out.println("getStartDatum");
        Leihe instance = new Leihe();
        Date expResult = null;
        Date result = instance.getStartDatum();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartDatum method, of class Leihe.
     */
    @Test
    public void testSetStartDatum() {
        System.out.println("setStartDatum");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDatum = null;
        try {
            startDatum = df.parse("2017-02-14");
        } catch (ParseException ex) {
            fail("Fehler beim parsen");
        }
        Leihe instance = new Leihe();
        instance.setStartDatum(startDatum);
        assertEquals(startDatum, instance.getStartDatum());
    }

    /**
     * Test of getEndeDatum method, of class Leihe.
     */
    @Test
    public void testGetEndeDatum() {
        System.out.println("getEndeDatum");
        Leihe instance = new Leihe();
        Date expResult = null;
        Date result = instance.getEndeDatum();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndeDatum method, of class Leihe.
     */
    @Test
    public void testSetEndeDatum() {
        System.out.println("setEndeDatum");
        Date endeDatum = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Leihe instance = new Leihe();
        try {
            endeDatum = df.parse("2017-02-14");
        } catch (ParseException ex) {
            fail("Fehler beim parsen");
        }
        instance.setStartDatum(endeDatum);
        assertEquals(endeDatum, instance.getStartDatum());
    }
    
}

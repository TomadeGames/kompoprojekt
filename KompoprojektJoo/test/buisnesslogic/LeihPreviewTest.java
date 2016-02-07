/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import java.util.Date;
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
public class LeihPreviewTest {
    
    public LeihPreviewTest() {
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
     * Test of getMaterial method, of class LeihPreview.
     */
    @Test
    public void testGetMaterial() {
        System.out.println("getMaterial");
        LeihPreview instance = new LeihPreview();
        String expResult = null;
        String result = instance.getMaterial();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaterial method, of class LeihPreview.
     */
    @Test
    public void testSetMaterial() {
        System.out.println("setMaterial");
        String material = "";
        LeihPreview instance = new LeihPreview();
        instance.setMaterial(material);
        assertEquals(material, instance.getMaterial());
    }

    /**
     * Test of getName method, of class LeihPreview.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        LeihPreview instance = new LeihPreview();
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class LeihPreview.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = null;
        LeihPreview instance = new LeihPreview();
        instance.setName(name);
        assertEquals(name, instance.getName());
    }

    /**
     * Test of getAnzahl method, of class LeihPreview.
     */
    @Test
    public void testGetAnzahl() {
        System.out.println("getAnzahl");
        LeihPreview instance = new LeihPreview();
        int expResult = 0;
        int result = instance.getAnzahl();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAnzahl method, of class LeihPreview.
     */
    @Test
    public void testSetAnzahl() {
        System.out.println("setAnzahl");
        int anzahl = 0;
        LeihPreview instance = new LeihPreview();
        instance.setAnzahl(anzahl);
        assertEquals(anzahl, instance.getAnzahl());
    }

    /**
     * Test of getVon method, of class LeihPreview.
     */
    @Test
    public void testGetVon() {
        System.out.println("getVon");
        LeihPreview instance = new LeihPreview();
        String expResult = null;
        String result = instance.getVon();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVon method, of class LeihPreview.
     */
    @Test
    public void testSetVon() {
        System.out.println("setVon");
        Date von = null;
        LeihPreview instance = new LeihPreview();
        instance.setVon(von);
        assertEquals(von, instance.getVon());
    }

    /**
     * Test of getBis method, of class LeihPreview.
     */
    @Test
    public void testGetBis() {
        System.out.println("getBis");
        LeihPreview instance = new LeihPreview();
        String expResult = null;
        String result = instance.getBis();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBis method, of class LeihPreview.
     */
    @Test
    public void testSetBis() {
        System.out.println("setBis");
        Date bis = null;
        LeihPreview instance = new LeihPreview();
        instance.setBis(bis);
        assertEquals(bis, instance.getBis());
    }
    
}

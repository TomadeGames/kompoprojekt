/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author woors
 */
public class BestellungTest {
    
    public BestellungTest() {
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
     * Test of getMaterialId method, of class Bestellung.
     */
    @Test
    public void testGetMaterialId() {
        System.out.println("getMaterialId");
        Bestellung instance = new Bestellung();
        Long expResult = null;
        Long result = instance.getMaterialId();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setMaterialId method, of class Bestellung.
     */
    @Test
    public void testSetMaterialId() {
        System.out.println("setMaterialId");
        Long materialId = 10L;
        Bestellung instance = new Bestellung();
        instance.setMaterialId(materialId);
        Assert.assertEquals(instance.getMaterialId(),materialId);
    }

    /**
     * Test of getAnzahl method, of class Bestellung.
     */
    @Test
    public void testGetAnzahl() {
        System.out.println("getAnzahl");
        Bestellung instance = new Bestellung();
        int expResult = 0;
        int result = instance.getAnzahl();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setAnzahl method, of class Bestellung.
     */
    @Test
    public void testSetAnzahl() {
        System.out.println("setAnzahl");
        int anzahl = 3;
        Bestellung instance = new Bestellung();
        instance.setAnzahl(anzahl);
        Assert.assertEquals(instance.getAnzahl(), anzahl);
    }

    /**
     * Test of getVon method, of class Bestellung.
     */
    @Test
    public void testGetVon() {
        System.out.println("getVon");
        Bestellung instance = new Bestellung();
        String expResult = null;
        String result = instance.getVon();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of getVonDate method, of class Bestellung.
     */
    @Test
    public void testGetVonDate() {
        System.out.println("getVonDate");
        Bestellung instance = new Bestellung();
        Date expResult = null;
        Date result = instance.getVonDate();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setVon method, of class Bestellung.
     */
    @Test
    public void testSetVon() throws Exception {
        System.out.println("setVon");
        String von = "2013-05-01";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date vonDate = df.parse(von);
        Bestellung instance = new Bestellung();
        instance.setVon(von);
        Assert.assertEquals(instance.getVon(), von);
        Assert.assertEquals(instance.getVonDate(),vonDate);
    }

    /**
     * Test of getBis method, of class Bestellung.
     */
    @Test
    public void testGetBis() {
        System.out.println("getBis");
        Bestellung instance = new Bestellung();
        String expResult = null;
        String result = instance.getBis();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of getBisDate method, of class Bestellung.
     */
    @Test
    public void testGetBisDate() {
        System.out.println("getBisDate");
        Bestellung instance = new Bestellung();
        Date expResult = null;
        Date result = instance.getBisDate();
        Assert.assertEquals(expResult, result);
        
    }

    /**
     * Test of setBis method, of class Bestellung.
     */
    @Test
    public void testSetBis() throws Exception {
        System.out.println("setBis");
        String bis = "2013-05-01";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date bisDate = df.parse(bis);
        Bestellung instance = new Bestellung();
        instance.setVon(bis);
        Assert.assertEquals(instance.getVon(), bis);
        Assert.assertEquals(instance.getVonDate(), bisDate);
    }

    /**
     * Test of getKommentar method, of class Bestellung.
     */
    @Test
    public void testGetKommentar() {
        System.out.println("getKommentar");
        Bestellung instance = new Bestellung();
        String expResult = null;
        String result = instance.getKommentar();
        Assert.assertEquals(expResult, result);
    }

    /**
     * Test of setKommentar method, of class Bestellung.
     */
    @Test
    public void testSetKommentar() {
        System.out.println("setKommentar");
        String kommentar = "neuesKommi";
        Bestellung instance = new Bestellung();
        instance.setKommentar(kommentar);
        Assert.assertTrue(instance.getKommentar().equals(kommentar));
    }    
}

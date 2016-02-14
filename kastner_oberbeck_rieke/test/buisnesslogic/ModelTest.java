/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import java.text.ParseException;
import java.util.Random;
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
public class ModelTest {
    public ModelTest() {
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

    @Test
    public void testCheckLeiheFail1(){
        System.out.println("checkLeiheFail1");
        Model instance = new Model();
        Bestellung b = new Bestellung();
        assertFalse(instance.checkLeihe(b));
    }
    
    @Test
    public void testCheckLeiheFail2(){
        System.out.println("checkLeiheFail2");
        Model instance = new Model();
        Bestellung b = new Bestellung();
        Random rnd = new Random();
        b.setAnzahl(rnd.nextInt());
        assertFalse(instance.checkLeihe(b));
    }
    
    @Test
    public void testCheckLeiheFail3(){
        System.out.println("checkLeiheFail3");
        Model instance = new Model();
        Bestellung b = new Bestellung();
        Random rnd = new Random();
        b.setAnzahl(rnd.nextInt());try {
            b.setBis("2002-02-03");
            b.setVon("2002-02-02");
        } catch (ParseException ex) {
            Assert.fail();
        }
        assertFalse(instance.checkLeihe(b));
    }
    
    @Test
     public void testCheckLeiheFail4(){
        System.out.println("checkLeiheFail4");
        Model instance = new Model();
        Bestellung b = new Bestellung();
        Random rnd = new Random();
        b.setAnzahl(rnd.nextInt());
        try {
            b.setBis("2002-02-02");
        } catch (ParseException ex) {
            Assert.fail();
        }
        assertFalse(instance.checkLeihe(b));
    }
     
    @Test
     public void testCheckLeiheFail5(){
        System.out.println("checkLeiheFail5");
        Model instance = new Model();
        Bestellung b = new Bestellung();
        Random rnd = new Random();
        b.setAnzahl(rnd.nextInt());
        try {
            b.setBis("2002-02-02");
            b.setVon("2002-02-02");
        } catch (ParseException ex) {
            Assert.fail();
        }
        assertFalse(instance.checkLeihe(b));
    }
}

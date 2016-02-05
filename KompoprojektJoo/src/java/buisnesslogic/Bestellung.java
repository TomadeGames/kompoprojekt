/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author woors
 * Hilfsklasse zum Bestellen von Materialien
 */
public class Bestellung {
    private Long materialId;
    private int anzahl;
    private Date von;
    private Date bis;
    private String kommentar;
    
    public Bestellung(){
        
    }
    
    public Bestellung(Long materialId, int anzahl, Date von, Date bis, String kommentar){
        this.materialId = materialId;
        this.anzahl = anzahl;
        this.von = von;
        this.bis = bis;
        this.kommentar = kommentar;
    }

    /**
     * @return the materialId
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId the materialId to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }

    /**
     * @param anzahl the anzahl to set
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    /**
     * @return the von
     */
    public String getVon() {
        if(this.von == null){
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(von);
    }

    public Date getVonDate(){
        return this.von;
    }
    /**
     * @param von the von to set
     * @throws java.text.ParseException
     */
    public void setVon(String von) throws ParseException {
        if(von.equals("")){
            this.von = null;
        }
        else{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("SetVon: " + von);
            this.von = df.parse(von);
        }
    }

    /**
     * @return the bis
     */
    public String getBis() {
        if(this.bis == null){
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(bis);
    }
    
    public Date getBisDate(){
        return this.bis;
    }

    /**
     * @param bis the bis to set
     * @throws java.text.ParseException
     */
    public void setBis(String bis) throws ParseException {
        if(bis.equals("")){
            this.bis = null;
        }
        else{
            System.out.println("SetBis: " + bis);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.von = df.parse(bis);
        }
    }

    /**
     * @return the kommentar
     */
    public String getKommentar() {
        return kommentar;
    }

    /**
     * @param kommentar the kommentar to set
     */
    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }
}

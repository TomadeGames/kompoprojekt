/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Hilfsklasse zum Anzeigen von Leihen
 * @author woors
 */
public class LeihPreview {
    private String material;
    private String name;
    private int anzahl;
    private Date von;
    private Date bis;
    private Long id;
    
    public LeihPreview(){
        
    }
    
    public LeihPreview(Long id, String material, String name, int anzahl, Date von, Date bis){
        this.material = material;
        this.name = name;
        this.anzahl = anzahl;
        this.von = von;
        this.bis = bis;
        this.id = id;
    }

    /**
     * @return the material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
        return df.format(this.von);
    }

    /**
     * @param von the von to set
     */
    public void setVon(Date von) {
        this.von = von;
    }

    /**
     * @return the bis
     */
    public String getBis() {
        if(this.bis == null){
            return null;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(this.bis);
    }

    /**
     * @param bis the bis to set
     */
    public void setBis(Date bis) {
        this.bis = bis;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import persistence.Persistence;

/**
 *
 * @author woors
 */
public class Leihe {
    private Long id;
    private Long materialId;
    private int anzahl;
    private Date von;
    private Date bis;
    
    private final SimpleStringProperty idProperty;
    private final SimpleStringProperty materialProperty;
    private final SimpleStringProperty anzahlProperty;
    private final SimpleStringProperty vonProperty;
    private final SimpleStringProperty bisProperty;

    public Leihe(){
        idProperty = new SimpleStringProperty();
        materialProperty = new SimpleStringProperty();
        anzahlProperty = new SimpleStringProperty();
        vonProperty = new SimpleStringProperty();
        bisProperty = new SimpleStringProperty();
    }
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    
    public String getIdProperty(){
        return idProperty.get();
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
        idProperty.set(""+ id);
    }

    /**
     * @return the material
     */
    public Long getMaterialId() {
        return materialId;
    }
    
    public String getMaterialProperty() {
        return this.materialProperty.get();
    }

    /**
     * @param materialId the material to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
        String value = Persistence.getMaterial(materialId).getName();
        materialProperty.set(value);
    }

    /**
     * @return the anzahl
     */
    public int getAnzahl() {
        return anzahl;
    }
    
    public String getAnzahlProperty() {
        return this.anzahlProperty.get();
    }

    /**
     * @param anzahl the anzahl to set
     */
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
        this.anzahlProperty.set("" + anzahl);
    }

    /**
     * @return the von
     */
    public Date getVon() {
        return von;
    }
    
    public String getVonProperty() {
        return this.vonProperty.get();
    }

    /**
     * @param von the von to set
     */
    public void setVon(Date von) {
        this.von = von;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.vonProperty.set(df.format(von));
    }

    /**
     * @return the bis
     */
    public Date getBis() {
        return bis;
    }

    public String getBisProperty(){
        return this.bisProperty.get();
    }
    /**
     * @param bis the bis to set
     */
    public void setBis(Date bis) {
        this.bis = bis;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        this.bisProperty.set(df.format(bis));
    }
}

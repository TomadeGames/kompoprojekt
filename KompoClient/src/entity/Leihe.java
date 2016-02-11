/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author woors
 */
public class Leihe {
    private Long id;
    private Long materialId;
    private int Anzahl;
    private Date von;
    private Date bis;

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

    /**
     * @return the material
     */
    public Long getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId the material to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    /**
     * @return the Anzahl
     */
    public int getAnzahl() {
        return Anzahl;
    }

    /**
     * @param Anzahl the Anzahl to set
     */
    public void setAnzahl(int Anzahl) {
        this.Anzahl = Anzahl;
    }

    /**
     * @return the von
     */
    public Date getVon() {
        return von;
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
    public Date getBis() {
        return bis;
    }

    /**
     * @param bis the bis to set
     */
    public void setBis(Date bis) {
        this.bis = bis;
    }
}

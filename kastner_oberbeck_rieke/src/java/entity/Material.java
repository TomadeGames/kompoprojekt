/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Das Material, dass ausgeliehen werden kann.
 * @author woors
 */
@Entity
public class Material implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;
    private int anzahl;
    private boolean geloescht;

    public Material(){
        geloescht = false;
    }
    /**
     * Material erzeugen.
     * @param name Der Name des Materials
     * @param anzahl Die maximale Anzahl
     */
    public Material(String name, int anzahl){
        this.name = name;
        this.anzahl = anzahl;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id + ": " + this.anzahl + "x " + this.name;
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the geloescht
     */
    public boolean isGeloescht() {
        return geloescht;
    }

    /**
     * @param geloescht the geloescht to set
     */
    public void setGeloescht(boolean geloescht) {
        this.geloescht = geloescht;
    }
}

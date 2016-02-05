/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author woors
 */
@Entity
public class Leihe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToOne
    private Material material;
    private int anzahl;    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDatum;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endeDatum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Leihe)) {
            return false;
        }
        Leihe other = (Leihe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Leihe[ id=" + id + " ]";
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
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
     * @return the startDatum
     */
    public Date getStartDatum() {
        return startDatum;
    }

    /**
     * @param startDatum the startDatum to set
     */
    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    /**
     * @return the endeDatum
     */
    public Date getEndeDatum() {
        return endeDatum;
    }

    /**
     * @param endeDatum the endeDatum to set
     */
    public void setEndeDatum(Date endeDatum) {
        this.endeDatum = endeDatum;
    }
}

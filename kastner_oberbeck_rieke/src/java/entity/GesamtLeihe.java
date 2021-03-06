/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Sammlung von mehreren Materialien, für eine Ausleihe.
 * @author woors
 */
@Entity
public class GesamtLeihe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;

    
    @OneToMany
    private List<Leihe> einzelleihen;

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
        if (!(object instanceof GesamtLeihe)) {
            return false;
        }
        GesamtLeihe other = (GesamtLeihe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GesamtLeihe[ id=" + id + " ]";
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
     * @return the einzelleihen
     */
    public List<Leihe> getEinzelleihen() {
        return einzelleihen;
    }

    /**
     * @param einzelleihen the einzelleihen to set
     */
    public void setEinzelleihen(List<Leihe> einzelleihen) {
        this.einzelleihen = einzelleihen;
    }
    
}

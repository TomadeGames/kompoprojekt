/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author woors
 */
public class GesamtLeihe {
    private Long id;
    private List<Leihe> leihen;
    private String name;

    /**
     * @return the leihen
     */
    public List<Leihe> getLeihen() {
        return leihen;
    }

    /**
     * @param leihen the leihen to set
     */
    public void setLeihen(List<Leihe> leihen) {
        this.leihen = leihen;
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

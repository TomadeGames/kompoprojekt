/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisnesslogik.Model;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author woors
 */
@Named("viewmodel")
@RequestScoped
public class Viewmodel {
    @Inject
    private Model model;
    
    private String materialName;
    private int materialAnzahl;

    public void addNewForum(){
        model.addMaterial(materialName, materialAnzahl);
    }

    /**
     * @return the MaterialName
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * @param MaterialName the MaterialName to set
     */
    public void setMaterialName(String MaterialName) {
        this.materialName = MaterialName;
    }

    /**
     * @return the MaterialAnzahl
     */
    public int getMaterialAnzahl() {
        return materialAnzahl;
    }

    /**
     * @param MaterialAnzahl the MaterialAnzahl to set
     */
    public void setMaterialAnzahl(int MaterialAnzahl) {
        this.materialAnzahl = MaterialAnzahl;
    }
}

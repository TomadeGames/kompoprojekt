package view;

import buisnesslogic.Model;
import java.io.Serializable;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author denrieke
 */

@Named("addmaterialview")
@RequestScoped
public class AddMaterialView implements Serializable{
    @Inject
    private Model model;

    private String materialname;
    private int materialanzahl;
    private String einfuegStatus = "";

    /**
     * @return the materialName
     */
    public String getMaterialname() {
        return materialname;
    }

    /**
     * @param materialname the materialName to set
     */
    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    /**
     * @return the materialAnzahl
     */
    public int getMaterialanzahl() {
        return materialanzahl;
    }

    /**
     * @param materialanzahl the materialAnzahl to set
     */
    public void setMaterialanzahl(int materialanzahl) {
        this.materialanzahl = materialanzahl;
    }
    
    public void addNewMaterial(){
        try{
            model.addMaterial(materialname, materialanzahl);
            einfuegStatus = materialname + " erfolgreich hinzugefügt";
        }
        catch(EJBException e){
            System.out.println("Exception: " + e);
            einfuegStatus = materialname + " existiert bereits";
        }
    }

    /**
     * @return the einfuegStatus
     */
    public String getEinfuegStatus() {
        return einfuegStatus;
    }
    
}

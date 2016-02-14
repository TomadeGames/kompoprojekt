package view;

import buisnesslogic.Model;
import entity.Material;
import java.io.Serializable;
import java.util.List;
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
 * View zum bearbeiten von Materialien.
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
    private Long materialId;

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
    
    /**
     * Löscht Material aus DB.
     * @param item zu löschendes Material
     */
    public void loeschen(Material item){
        this.model.removeMaterial(item);
    }
    
    /**
     * Neues Material in der DB anlegen.
     * Aufruf über Button.
     */
    public void addNewMaterial(){
        try{
            if(this.materialname == null || this.materialname.equals("")){
                this.einfuegStatus = "Kein Name angegeben";
                return;
            }
            if(this.materialanzahl < 1){
                this.einfuegStatus = "Mindestens ein Material muss eingelagert werden";
                return;
            }
            this.model.addMaterial(this.materialname, this.materialanzahl);
            this.einfuegStatus = this.materialname + " erfolgreich hinzugefügt";
        }
        catch(EJBException e){
            System.out.println("Exception: " + e);
            this.einfuegStatus = this.materialname + " existiert bereits";
        }
    }
    
    /**
     * Prüft ob Material gelöscht wurde. 
     * @param m zu prüfendes Material
     * @return Beschriftung für den Löschen/Hinzufügen Button 
     */
    public String isDeletet(Material m){
        if(m.isGeloescht()){
            return "+";
        }
        return "X";
    }
    
    /**
     * Gibt alle Materialien der DB zurück.
     * @return alle Materialien
     */
    public List<Material> getAllMaterialien(){
        return this.model.allMaterials();
    }
    
    /**
     * Ändern eines Materials.
     */
    public void changeMaterial(){
        try{
            if(this.materialname == null || this.materialname.equals("")){
                this.einfuegStatus = "Kein Name angegeben";
                return;
            }
            if(this.materialanzahl < 1){
                this.einfuegStatus = "Mindestens ein Material muss eingelagert werden";
                return;
            }
            Material mat = this.model.getMaterial(materialname);
            if(mat != null){
                this.einfuegStatus = "Materialname schon vorhanden";
                return;
            }
            if(model.changeMaterial(getMaterialId(), this.materialname, this.materialanzahl)){
                einfuegStatus = this.materialname + " erfolgreich hinzugefügt";                
            }
            else{
                this.einfuegStatus = "ID nicht gefunden";
            }
        }
        catch(EJBException e){
            System.out.println("Exception: " + e);
            this.einfuegStatus = this.materialname + " existiert bereits";
        }
    }

    /**
     * @return the einfuegStatus
     */
    public String getEinfuegStatus() {
        return this.einfuegStatus;
    }

    /**
     * @return the materialId
     */
    public Long getMaterialId() {
        return this.materialId;
    }

    /**
     * @param materialId the materialId to set
     */
    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }
    
}

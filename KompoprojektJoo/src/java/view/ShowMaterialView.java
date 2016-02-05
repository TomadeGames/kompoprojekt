/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisnesslogic.Bestellung;
import buisnesslogic.Model;
import entity.Material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author woors
 */
@Named("showmaterialview")
@SessionScoped
@ManagedBean
public class ShowMaterialView implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Inject
    private Model model;

    private String bestellstatus = "";
    private String leihename = "";
    private List<Bestellung> bestellungen = new ArrayList<>();
    
    public List<Material> getMaterialien(){
        List<Material> erg =  model.restMaterialien();
        return erg;
    }

    public void leihen(){        
        for(Bestellung b: bestellungen){
            if(!this.model.checkLeihe(b)){
                return;
            }
        }
        if(leihename == "" || leihename == null){
            this.bestellstatus = "Kein Name angegeben";
            return;
        }
        this.model.addLeihe(leihename, bestellungen);
        this.bestellstatus = "Bestellung erfolgreich";
    }
    
    public void addBestellung(){
        bestellungen.add(new Bestellung());
    }
    
    public void removeBestellung(Bestellung bestellung){
        bestellungen.remove(bestellung);
    }
    
    /**
     * @return the leihename
     */
    public String getLeihename() {
        return leihename;
    }

    /**
     * @param leihename the leihename to set
     */
    public void setLeihename(String leihename) {
        this.leihename = leihename;
    }

    /**
     * @return the bestellungen
     */
    public List<Bestellung> getBestellungen() {
        return bestellungen;
    }

    /**
     * @return the bestellstatus
     */
    public String getBestellstatus() {
        return bestellstatus;
    }
}

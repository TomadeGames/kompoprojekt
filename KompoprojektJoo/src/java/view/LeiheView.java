/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import buisnesslogic.Bestellung;
import buisnesslogic.LeihPreview;
import buisnesslogic.Model;
import entity.GesamtLeihe;
import entity.Leihe;
import entity.Material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author woors
 */
@Named("leiheview")
@SessionScoped
@ManagedBean
public class LeiheView implements Serializable{
    private static final long serialVersionUID=1L;
    
    @Inject
    private Model model;

    private String bestellstatus = "";
    private String leihename = "";
    private List<Bestellung> bestellungen = new ArrayList<>();
    private Map<String, Long> materialienMap;
    
    public LeiheView(){
        bestellungen.add(new Bestellung());
    }
    
    public List<Material> getMaterialien(){
        List<Material> erg =  model.materialien();
        return erg;
    }
    
    public List<LeihPreview> getAllLeihen(){
        List<LeihPreview> erg = new ArrayList<>();
        List<GesamtLeihe> leihen = this.model.getGesamtLeihen();
        leihen.stream().forEach((l) -> {
            List<Leihe> einzel = l.getEinzelleihen();
            einzel.stream().forEach((e) -> {
                erg.add(new LeihPreview(e.getId(), e.getMaterial().getName(), l.getName()
                        , e.getAnzahl(), e.getStartDatum(), e.getEndeDatum()));
            });
        });
        return erg;
    }

    public void leihen(){        
        for(Bestellung b: bestellungen){
            if(!this.model.checkLeihe(b)){
                return;
            }
        }
        if(leihename.equals("") || leihename == null){
            this.bestellstatus = "Kein Name angegeben";
            return;
        }
        if(this.bestellungen.isEmpty()){
            this.bestellstatus = "Kein Material angegeben";
            return;
        }
        this.model.addLeihe(leihename, bestellungen);
        this.leihename = "";
        this.bestellungen.removeAll(bestellungen);
        this.bestellungen.add(new Bestellung());
        
        this.bestellstatus = "Bestellung erfolgreich";
    }
    
    public void addBestellung(){
        bestellungen.add(new Bestellung());
    }
    
    public void removeBestellung(Bestellung bestellung){
        bestellungen.remove(bestellung);
    }
    
    public void removeLeihe(LeihPreview l){
        System.out.println("Leihpreview Id: " + l.getId());
        Leihe leihe = this.model.getLeihe(l.getId());
        if(leihe == null){
            System.out.println("Fehlerhafte Leihe!!!!!");
        }
        this.model.removeLeihe(leihe);
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

    /**
     * @return the materialienMap
     */
    public Map<String, Long> getMaterialienMap() {
        materialienMap = new HashMap();
        List<Material> mats = this.model.materialien();
        mats.stream().forEach((m) -> {
            materialienMap.put(m.getName(), m.getId());
        });
        System.out.println("Map size: " + materialienMap.size());
        return materialienMap;
    }
}

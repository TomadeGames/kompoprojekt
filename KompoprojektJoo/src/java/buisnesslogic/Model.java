/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import entity.GesamtLeihe;
import entity.Leihe;
import entity.Material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import persistence.Persistence;

/**
 *
 * @author denrieke
 */
@Dependent
public class Model implements Serializable{
    @Inject
    private Persistence db;
    
    public Material getMaterial(Long id){
        return this.db.getMaterial(id);
    }
    
    public List<String> materialNames(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName());
        });
        return erg;
    }
    
    public List<String> fullMaterialStrings(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName() + ": " + m.getAnzahl());
        });
        return erg;
    }
    
    public List<String> getLeihen(){
        List<String> erg = new ArrayList<>();
        this.db.getAllLeihen().stream().forEach((l) -> {
            erg.add(l.getMaterial().getName() + ": " + l.getAnzahl());
        });
        return erg;
    }
    
    public List<Material> restMaterialien(){
        List<Material> mats = this.db.getAllMaterials();
        List<Leihe> leihen = this.db.getAllLeihen();
        
        leihen.stream().forEach((l) -> {    //Map füllen mit [Materialname], [anzahl bereits Ausgeliehen]
            mats.stream().forEach((m) -> {
                if(l.getMaterial().getName().equals(m.getName())){
                    m.setAnzahl(m.getAnzahl() - l.getAnzahl());
                }
            });
        });
        return mats;
    }
    
    public void addMaterial(String name, int anzahl){
        Material m = new Material();
        System.out.println(name);
        System.out.println(anzahl);
        m.setName(name);
        m.setAnzahl(anzahl);
        this.db.persist(m);
    }
    
    public void addLeihe(String name, List<Bestellung> bestellungen){
        List<Leihe> leihen = new ArrayList<>();
        for(Bestellung b: bestellungen){
            Leihe l = new Leihe();
            l.setAnzahl(b.getAnzahl());
            l.setEndeDatum(b.getBisDate());
            l.setStartDatum(b.getVonDate());
            Material m = this.db.getMaterial(b.getMaterialId());
            l.setMaterial(m);
            leihen.add(l);
            this.db.persist(l);
        }
        GesamtLeihe g = new GesamtLeihe();
        g.setEinzelleihen(leihen);
        g.setName(name);
        this.db.persist(g);
    }
    
    public boolean checkLeihe(Bestellung b){
        if(b.getBisDate().after(b.getVonDate())){
            b.setKommentar("Das Enddatum muss nach dem Startdatum sein");
            return false;
        }
        //Prüfung ob Material existiert
        Material m = this.db.getMaterial(b.getMaterialId());
        if(m == null){
            b.setKommentar("Material existiert nicht");
            return false;
        }
        
        //Prüfung auf Vorhandene Anzahl
        List<Leihe> leihen = this.db.getAllLeihen();
        int summe = 0;
        for(Leihe l: leihen){
            if(l.getEndeDatum().before(b.getVonDate())){
                if(l.getMaterial().getName().equals(m.getName())){
                    summe += l.getAnzahl();
                }
            }
        }
        if(summe + b.getAnzahl() < m.getAnzahl()){
            b.setKommentar("");
            return true;
        }
        b.setKommentar("So viele Artikel sind nicht verfügbar");
        return false;
    }
}

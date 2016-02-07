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
    
    public Material getMaterial(String name){
        List<Material> mats = this.db.getAllMaterials();
        for(Material m: mats){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }
    
    public List<String> materialNames(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName());
        });
        return erg;
    }
    
    public List<Material> materialien(){
        return this.db.getAllMaterials();
    }
    
    public List<String> fullMaterialStrings(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName() + ": " + m.getAnzahl());
        });
        return erg;
    }
    
    public List<Leihe> getLeihen(){
        return this.db.getAllLeihen();
    }
    
    public List<GesamtLeihe> getGesamtLeihen(){
        return this.db.getGesamtLeihen();
    }
    
    public void addMaterial(String name, int anzahl){
        Material m = new Material();
        System.out.println(name);
        System.out.println(anzahl);
        m.setName(name);
        m.setAnzahl(anzahl);
        this.db.persist(m);
    }
    
    public boolean changeMaterial(Long id, String name, int anzahl){
        Material mat = this.db.getMaterial(id);
        if(mat == null){
            return false;
        }
        mat.setName(name);
        mat.setAnzahl(anzahl);
        this.db.merge(mat);
        return true;
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
        System.out.println(b.toString());
        if(b.getVonDate() == null){
            b.setKommentar("Das Startdatum fehlt");
            return false;
        }if(b.getBisDate() == null){
            b.setKommentar("Das Enddatum fehlt");
            return false;
        }
        if(b.getAnzahl() <=0){
            b.setKommentar("Mindestens 1 Material muss ausgeliehen werden");
            return false;
        }
        if(b.getBisDate().before(b.getVonDate())){
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
            if(!(b.getBisDate().before(l.getStartDatum()) 
                    || b.getVonDate().after(l.getEndeDatum()))){
                if(l.getMaterial().getName().equals(m.getName())){
                    summe += l.getAnzahl();
                }
            }
        }
        if(summe + b.getAnzahl() <= m.getAnzahl()){
            b.setKommentar("");
            return true;
        }
        b.setKommentar("So viele Artikel sind nicht verfügbar");
        return false;
    }
}

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
 * Logik zwischen Persistance und View
 * @author denrieke
 */
@Dependent
public class Model implements Serializable{
    @Inject
    private Persistence db;
    
    /**
     * Gibt Material von DB, anhand von ID, zurück.
     * @param id Id des angeforderten Materials
     * @return angefordertes Material
     */
    public Material getMaterial(Long id){
        return this.db.getMaterial(id);
    }
    
    /**
     * Gibt Material von DB, anhand von Name, zurück.
     * @param name Name des Materials
     * @return gefordertes Material
     */
    public Material getMaterial(String name){
        List<Material> mats = this.db.getAllMaterials();
        for(Material m: mats){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }
    
    /**
     * Gibt Namen aller Materialien von DB zurück.
     * @return alle Materialnamen
     */
    public List<String> materialNames(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName());
        });
        return erg;
    }
    
    /**
     * Gibt alle Materialien der DB zurück.
     * @return alle Materialien
     */
    public List<Material> materialien(){
        return this.db.getAllMaterials();
    }
    
    /**
     * Gibt alle Materialien, inklusive gelöschte, der DB zurück.
     * @return alle Materialien
     */
    public List<Material> allMaterials(){
        return this.db.getAllAndRemovedMaterials();
    }
    
    /**
     * Gibt für jedes Material einen String (Name:Anzahl) zurück.
     * @return String für jedes Material
     */
    public List<String> fullMaterialStrings(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((m) -> {
            erg.add(m.getName() + ": " + m.getAnzahl());
        });
        return erg;
    }
    
    /**
     * Gibt alle Leihen der DB zurück.
     * @return alle Leihen
     */
    public List<Leihe> getLeihen(){
        return this.db.getAllLeihen();
    }
    
    /**
     * Gibt alle Gesamtleihen der DB zurück.
     * @return alle Gesamtleihen
     */
    public List<GesamtLeihe> getGesamtLeihen(){
        return this.db.getGesamtLeihen();
    }
    
    /**
     * Löscht Material aus DB
     * @param mat zu löschendes Material
     */
    public void removeMaterial(Material mat){
        mat.setGeloescht(!mat.isGeloescht());
        this.db.merge(mat);
    }
    
    /**
     * Fügt Material in DB ein.
     * @param name Name des Material
     * @param anzahl Anzahl des Material
     */
    public void addMaterial(String name, int anzahl){
        Material m = new Material();
        System.out.println(name);
        System.out.println(anzahl);
        m.setName(name);
        m.setAnzahl(anzahl);
        this.db.persist(m);
    }
    
    /**
     * Sucht Material anhand von ID aus DB und ändert dies dann.
     * @param id ID des zu ändernden Materials
     * @param name neuer Name des Materials
     * @param anzahl neue Anzahl des Materials
     * @return gibt zurück ob Material gefunden
     */
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
    
    /**
     * Löscht Leihe aus DB
     * @param l zu löschende Leihe
     */
    public void removeLeihe(Leihe l){
        List<GesamtLeihe> gls = this.db.getGesamtLeihen();
        for(GesamtLeihe gl: gls){
            List<Leihe> newList = new ArrayList<>();
            for(Leihe leihe: gl.getEinzelleihen()){
                if(!leihe.equals(l)){
                    newList.add(leihe);
                }
            }
            gl.setEinzelleihen(newList);
            this.db.merge(gl);
        }
        this.db.remove(l);
    }
    
    /**
     * Gibt Leihe anhand von ID aus DB zurück.
     * @param id ID der Leihe
     * @return gesuchte Leihe
     */
    public Leihe getLeihe(Long id){
        List<Leihe> leihen = this.db.getAllLeihen();
        for(Leihe l: leihen){
            if(l.getId().equals(id)){
                return l;
            }
        }
        return null;
    }
    
    /**
     * Fügt GesamtLeihe in DB ein.
     * @param name Name des Leihers
     * @param bestellungen einzelne Bestellungen
     */
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
    
    /**
     * Prüft ob eine Bestellung gültig ist.
     * @param b zu prüfende Bestellung
     * @return ob Bestellung gültig ist
     */
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
        
        if(!b.getBisDate().after(b.getVonDate())){
            b.setKommentar("Eine Leihe muss mindestens einen Tag dauern");
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
        
        b.setKommentar("So viele Artikel sind nicht verfügbar. Im Zeitraum sind " 
                + summe + " von " + m.getAnzahl() + " bereits ausgeliehen.");
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

import entity.Leihe;
import entity.Material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    public List<String> restMaterialien(){
        List<String> erg = new ArrayList<>();
        List<Leihe> leihen = this.db.getAllLeihen();
        Map<String, Integer> leihMap = new HashMap<>();
        
        leihen.stream().forEach((l) -> {    //Map füllen mit [Materialname], [anzahl bereits Ausgeliehen]
            if(leihMap.containsKey(l.getMaterial().getName())){
                int summe = leihMap.get(l.getMaterial().getName());
                summe += l.getAnzahl();
                leihMap.put(l.getMaterial().getName(), summe);
            }
            else{
                leihMap.put(l.getMaterial().getName(), l.getAnzahl());
            }
        });
        
        this.db.getAllMaterials().stream().forEach((m) -> {
            if(leihMap.get(m.getName()) != null){
                erg.add(m.getName() + ": " + (m.getAnzahl()-leihMap.get(m.getName())));
            }
            else{
                erg.add(m.getName() + ": " + m.getAnzahl());
            }
        });
        return erg;
    }
    
    public void addMaterial(String name, int anzahl){
        Material m = new Material();
        System.out.println(name);
        System.out.println(anzahl);
        m.setName(name);
        m.setAnzahl(anzahl);
        this.db.persist(m);
    }
    
    public boolean addLeihe(String name, Material m, int anzahl, Date start, Date ende){
        List<Leihe> leihen = this.db.getAllLeihen();
        int summe = 0;
        for(Leihe l: leihen){
            if(l.getMaterial().getName().equals(m.getName())){
                summe += l.getAnzahl();
            }
        }
        if(summe + anzahl < m.getAnzahl()){
            Leihe newL = new Leihe();
            newL.setAnzahl(anzahl);
            newL.setName(name);
            newL.setMaterial(m);
            newL.setEndeDatum(ende);
            newL.setStartDatum(start);
            this.db.persist(newL);
            return true;
        }
        return false;
    }
}

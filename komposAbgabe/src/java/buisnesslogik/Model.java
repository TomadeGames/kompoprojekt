/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogik;

import entities.Material;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import persistence.Persitence;

/**
 *
 * @author woors
 */
@Dependent
public class Model implements Serializable{
    @Inject
    private Persitence db;
    
    public List<String> materialien(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterial().stream().forEach((f) -> {
            erg.add(f.getName());
        });
        return erg;
    }
    
    public void addMaterial(String name, int anzahl){
        Material f = new Material();
        f.setName(name);
        f.setAnzahl(anzahl);
        this.db.persist(f);
    }
}

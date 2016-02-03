/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buisnesslogic;

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
    
    public List<String> materialNames(){
        List<String> erg = new ArrayList<>();
        this.db.getAllMaterials().stream().forEach((f) -> {
            erg.add(f.getName());
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Leihe;
import entity.Material;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author denrieke
 */
@Stateless
@PersistenceContext(unitName="AbgabePU")
public class Persistence {
    @Inject
    private EntityManager em;
    
    public void persist(Object object){
        em.persist(object);
    }
    
    public Object merge(Object object){
        return em.merge(object);
    }
    
    public Material getMaterial(String name){
        List<Material> mats = getAllMaterials();
        for(Material m: mats){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }
    
    public List<Material> getAllMaterials(){
        return em.createQuery("SELECT a FROM Material a", Material.class).getResultList();
    }
    
    public List<Leihe> getAllLeihen(){
        return em.createQuery("SELECT a FROM Leihe a", Leihe.class).getResultList();
    }
}

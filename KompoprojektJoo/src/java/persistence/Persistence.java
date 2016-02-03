/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

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
    
    public List<Material> getAllMaterials(){
        return em.createQuery("SELECT a FROM Material a", Material.class).getResultList();
    }
}

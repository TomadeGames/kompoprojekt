/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entities.Material;
import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author woors
 */
@Stateless
@PersistenceContext(unitName="komposAbgabePU")
public class Persitence {
    private EntityManager em;
    
    public void persist(Object object){
        em.persist(object);
    }
    
    public Object merge(Object object){
        return em.merge(object);
    }
    
    public List<Material> getAllMaterial(){
        return em.createQuery("SELECT a FROM Material a", Material.class).getResultList();
    }
}

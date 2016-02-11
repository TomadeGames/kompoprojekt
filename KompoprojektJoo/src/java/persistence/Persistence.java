/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.GesamtLeihe;
import entity.Leihe;
import entity.Material;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Regelt Datenbankzugriffe.
 * @author denrieke
 */
@Stateless
@PersistenceContext(unitName="AbgabePU")
public class Persistence {
    @Inject
    private EntityManager em;
    
    /**
     * Speichert neues Objekt in DB.
     * @param object zu speicherndes Objekt
     */
    public void persist(Object object){
        em.persist(object);
    }
    
    /**
     * Ändert Objekt in DB.
     * @param object zu änderndes Objekt
     * @return Geändertes Objekt
     */
    public Object merge(Object object){
        return em.merge(object);
    }
    
    /**
     * Entfernt Objekt aus DB
     * @param object zu entfernendes Objekt
     */
    public void remove(Object object){
        em.remove(em.merge(object));
    }
    
    /**
     * Lädt Material mit Id materialId aus DB.
     * @param materialId Id des Materials
     * @return das geladene Material
     */
    public Material getMaterial(Long materialId){
        List<Material> mats = getAllMaterials();
        for(Material m: mats){
            if(m.getId().equals(materialId)){
                return m;
            }
        }
        return null;
    }
    
    /**
     * Gibt alle Materialien zurück (auch gelöschte)
     * @return alle Materialien
     */
    public List<Material> getAllAndRemovedMaterials(){
        return em.createQuery("SELECT a FROM Material a", Material.class).getResultList();
    }
    
    /**
     * Gibt alle Materialien zurpck (keine gelöschten)
     * @return alle Materialien
     */
    public List<Material> getAllMaterials(){
        List<Material> mats = em.createQuery("SELECT a FROM Material a", Material.class).getResultList();
        List<Material> erg = new ArrayList<>();
        mats.stream().forEach((m) -> {
            if(!m.isGeloescht()){
                erg.add(m);
            }
        });
        return erg;
    }
    
    /**
     * Gibt alle Leihen zurück
     * @return alle Leihen
     */
    public List<Leihe> getAllLeihen(){
        return em.createQuery("SELECT a FROM Leihe a", Leihe.class).getResultList();
    }
    
    /**
     * Gibt alle GesamtLeihen zurück
     * @return alle GesamtLeihen
     */
    public List<GesamtLeihe> getGesamtLeihen(){
        return em.createQuery("SELECT a FROM GesamtLeihe a", GesamtLeihe.class).getResultList();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Erstellt EntityManager
 * @author denrieke
 */
public class Producer {
    @Produces
    @PersistenceContext(unitName="AbgabePU")
    private EntityManager em;
}

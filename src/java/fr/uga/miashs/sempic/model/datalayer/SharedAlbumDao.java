/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model.datalayer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SharedAlbumDao  extends AbstractJpaDao{
    
    
    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em;
    }
    
}

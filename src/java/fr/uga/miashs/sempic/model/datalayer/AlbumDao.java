/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SempicUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


@Stateless
public class AlbumDao extends AbstractJpaDao<Album,Long> {

    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;
    
    public AlbumDao(){
        super(Album.class);
    }
    
    
    @Override
    protected EntityManager getEntityManager() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em;
    }
    
    
    public Album getByName(String titre, Long owner_id){
        try{
            return (Album)
                    getEntityManager().createQuery("Select a FROM Album a " + 
                            "WHERE a.titre=:titre and a.owner_id=:owner_id").setParameter("owner_id", owner_id)
             .setParameter("titre", titre)
                .getSingleResult();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Album> getByUser(SempicUser owner){
        try{
            return 
                    getEntityManager().createQuery("Select a FROM Album a " + 
                            "WHERE a.owner=:owner")
             .setParameter("owner", owner)
                .getResultList();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public Album getById(Long id){
        try{
            return (Album)
                    getEntityManager().createQuery("Select a FROM Album a " + 
                            "WHERE a.id=:id")
             .setParameter("id", id)
                .getSingleResult();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SharedAlbum;
import fr.uga.miashs.sempic.model.Picture;
import fr.uga.miashs.sempic.model.SempicUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author denisbolshakov
 */

@Stateless
public class SharedAlbumDao  extends AbstractJpaDao <SharedAlbum, SempicUser>{
    
    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;
    
     public SharedAlbumDao(){
        super(SharedAlbum.class);
    }
    
    // 
     
    @Override
    protected EntityManager getEntityManager() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return em;
    }
    
    public List<SharedAlbum> getByReceiver(SempicUser receiver ){
        try{
            return 
                    getEntityManager().createQuery("Select a FROM SharedAlbum a " + 
                            "WHERE a.receiver=:receiver")
             .setParameter("receiver", receiver)
                .getResultList();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public List<SharedAlbum> getByAlbum(Album album){
        try{
            return 
                    getEntityManager().createQuery("Select a FROM SharedAlbum a " + 
                            "WHERE a.album=:album")
             .setParameter("album", album)
                .getResultList();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public SharedAlbum getById(Long id){
        try{
            return (SharedAlbum)
                    getEntityManager().createQuery("Select a FROM SharedAlbum a " + 
                            "WHERE a.id=:id")
             .setParameter("id", id)
                .getSingleResult();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
}

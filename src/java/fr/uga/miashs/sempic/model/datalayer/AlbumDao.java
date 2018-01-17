/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Album;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author denisbolshakov
 */
public class AlbumDao extends AbstractJpaDao<Album,Long> {

    @PersistenceContext(unitName = "Album")
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
                    getEntityManager().createQuery("Select a FROM ALBUM a" + 
                            "WHERE a.titre=:titre and a.owner_id=:owner_id").setParameter("owner_id", owner_id)
             .setParameter("titre", titre)
                .getSingleResult();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Album> getById(Long owner_id){
        try{
            return 
                    getEntityManager().createQuery("Select a FROM ALBUM a" + 
                            "WHERE a.owner_id=:owner_id")
             .setParameter("owner_id", owner_id)
                .getResultList();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
}
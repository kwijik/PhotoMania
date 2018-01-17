/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model.datalayer;

import fr.uga.miashs.sempic.model.Picture;
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
public class PictureDao extends AbstractJpaDao<Picture, Long>{

    @PersistenceContext(unitName = "SempicPU")
    private EntityManager em;

    public PictureDao(){
        super(Picture.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return em;
    }
    
    public Picture getByName(String name, String album_titre){
        try{
            return (Picture)
                    getEntityManager().createQuery("Select a FROM Picture p" + 
                            "WHERE p. ame=:name and a.titre=:album_titre").setParameter("name", name)
             .setParameter("titre", album_titre)
                .getSingleResult();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
    public List<Picture> getByAlbum(Long album_id){
        try{
            return 
                    getEntityManager().createQuery("Select p FROM Picture p" + 
                            "WHERE p.album_id=:album_id")
             .setParameter("album_id", album_id)
                .getResultList();
        }  catch (NoResultException e) {
            return null;
        }
    }
    
}

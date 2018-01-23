/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SharedAlbum;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
import fr.uga.miashs.sempic.model.datalayer.SempicUserDao;
import fr.uga.miashs.sempic.model.datalayer.SharedAlbumDao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author denisbolshakov
 */

@Named
@SessionScoped
public class SharedAlbums implements Serializable{
    private List<Album> albumList;
    
    @Inject
    AuthManager am;
    @EJB
    SharedAlbumDao dao;
    SempicUser connectedUser;
    SharedAlbum sharedAlbum;
    Long albumId;
    String email;
    @EJB
    SempicUserDao userDao;
    @EJB
    AlbumDao daoAlbum;
    
    public SharedAlbums(){
        
    }
    
    public SempicUser getConnectedUser() {
        if(connectedUser == null){
            connectedUser = am.getConnectedUser();
        }
        return connectedUser;
    }
    
    public String getEmail() {
        return email;
    }
    
     public void setEmail(String email) {
        this.email = email;
    }
    
    public List<SharedAlbum> getSharedAlbumList() {
        System.out.println("Carrot from get: "+dao.getByReceiver(getConnectedUser()));
        return dao.getByReceiver(getConnectedUser());
    }
    
     public void setSharedAlbum(SharedAlbum a) {
        this.sharedAlbum = a;
    }
    
    public SharedAlbum getSharedAlbum() {
        // if (sharedAlbum == null) {
        //    sharedAlbum = new SharedAlbum(getAlbumId(), );
        //}
        return sharedAlbum;
    }
    
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }
    
    public Long getAlbumId() {
       if (albumId == null){
            return Long.valueOf(0);
        }
        return albumId;
    }
    
    public String share() {
        try { 
            
            Long recieverId = userDao.getByEmail(email).getId();
            System.out.println("Email: " + email + " albumId: " + albumId + " recieverId: " + recieverId);
            dao.create(new SharedAlbum(daoAlbum.getById(albumId), userDao.getByEmail(email)));
        } catch (EJBException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException vEx = (ConstraintViolationException) ex.getCause();
                vEx.getConstraintViolations().forEach(cv -> {
                    System.out.println(cv);
                });
                vEx.getConstraintViolations().forEach(cv -> {
                    FacesContext.getCurrentInstance().addMessage("validationError", new FacesMessage(cv.getMessage()));
                });

            }
        }
        return "";
    }
    
}

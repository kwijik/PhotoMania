/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;


public class SharedAlbums {
    private List<Album> albumList;
    
    @Inject
    AuthManager am;
    @EJB
    AlbumDao dao;
    SempicUser connectedUser;
    Album album;
    
    public SharedAlbums(){
        
    }
    
    public SempicUser getConnectedUser() {
        if(connectedUser == null){
            connectedUser = am.getConnectedUser();
        }
        return connectedUser;
    }
    
    public List<Album> getAlbumList() {
        return dao.getByUser(getConnectedUser());
    }
    
    public void setAlbum(Album a) {
        this.album = a;
    }
    
    public Album getAlbum() {
        if (album == null) {
            album = new Album(am.getConnectedUser());
        }
        return album;
    }
    
    public String share() {
        try { 
            dao.create(album);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
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
public class AlbumList implements Serializable {
    private List<Album> albumList;
    
    @Inject
    AuthManager am;
    @EJB
    AlbumDao dao;
    SempicUser connectedUser;
    Album album;
    
    public AlbumList(){
        connectedUser = am.getConnectedUser();
        dao = new AlbumDao();
        connectedUser.getId();
        albumList = dao.getById(connectedUser.getId());
    }
    
    public List getList() {
        return albumList;
    }
    
    public void setAlbum(Album a) {
        this.album = a;
    }

    public String create() {
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

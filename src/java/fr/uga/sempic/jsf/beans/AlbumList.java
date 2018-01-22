/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.Picture;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.SharedAlbum;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
import fr.uga.miashs.sempic.model.datalayer.PictureDao;
import fr.uga.miashs.sempic.model.datalayer.PictureStore;
import fr.uga.miashs.sempic.model.datalayer.SharedAlbumDao;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;


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
    @EJB
    PictureDao pictureDao;
    @EJB
    SharedAlbumDao sharedAlbumDao;
    
    public AlbumList(){
       // System.out.println("AM: " + am);
        
       // connectedUser.getId();
        // albumList = dao.getById(connectedUser.getId());
    }
    
    @PostConstruct
    public void init(){
        //connectedUser = am.getConnectedUser();
       // dao = new AlbumDao();
       // albumList = dao.getById(connectedUser.getId());
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
    
    public String delete(Album album){
        List<Picture> pics = pictureDao.getByAlbum(album);
        for (Picture p: pics){
            if(pictureDao.getByName(p.getName()).size() == 1){
                System.out.println("Deleting image... " + p.getName());
                Path picPath = PictureStore.UPLOADS.resolve(p.getName());
                try {
                    Files.delete(picPath);
                } catch (IOException ex) {
                    Logger.getLogger(AlbumList.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pictureDao.delete(p);
            
        }
        System.out.println("Deleting..." + album.getId());
        dao.delete(album);
        
        List<SharedAlbum> sharedAlbums = sharedAlbumDao.getByAlbum(album);
        for(SharedAlbum sa:sharedAlbums){
            sharedAlbumDao.delete(sa);
        }

        return "album-list.xhtml";
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

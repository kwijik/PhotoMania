/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Album;
import fr.uga.miashs.sempic.model.Picture;
import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.AlbumDao;
import fr.uga.miashs.sempic.model.datalayer.PictureDao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author denisbolshakov
 */

@Named
@SessionScoped
public class Pictures implements Serializable{
    private List<Picture> pictureList;
    @Inject
    AuthManager am;
    @EJB
    PictureDao pictureDao;
    @EJB
    AlbumDao albumDao;
   // Album album;
    Long albumId;
    SempicUser connectedUser;
    
    
    public Pictures(){
        // this.albumList ;
      //  AuthManager am = new AuthManager();
    //    SempicUser connectedUser = am.getConnectedUser();
      //  AlbumDao ad = new AlbumDao();
       // albumList = ad.getById(connectedUser.getId());
    }
    
    public List<Picture> getList() {
        Album a = albumDao.getById(getAlbumId());
        return pictureDao.getByAlbum(a);
    }
    
    public Long getAlbumId(){
        if (albumId == null){
            return Long.valueOf(0);
        }
        return albumId;
    }
    
    public SempicUser getConnectedUser() {
        if(connectedUser == null){
            connectedUser = am.getConnectedUser();
        }
        return connectedUser;
    }
    // why np exeption ? 
    
    // as idea we can keep long and at the last moment get from it album
    public void setAlbumId(Long id) {
        this.albumId = id;
    }
    
}

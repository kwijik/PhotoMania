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


@Named
@SessionScoped
public class Pictures implements Serializable{
    private List<Picture> pictureList;
    @Inject
    AuthManager am;
    @EJB
    PictureDao pictureDao;
    AlbumDao albumDao;
    Album album;
    SempicUser connectedUser;
    
    
    public Pictures(){
        // this.albumList ;
        AuthManager am = new AuthManager();
    //    SempicUser connectedUser = am.getConnectedUser();
      //  AlbumDao ad = new AlbumDao();
       // albumList = ad.getById(connectedUser.getId());
    }
    
    public List getList() {
        return pictureDao.getByAlbum(album);
    }
    
    public Long getAlbum(){
        if (album == null){
            return Long.valueOf(0);
        }
        return album.getId();
    }
    
    public SempicUser getConnectedUser() {
        if(connectedUser == null){
            connectedUser = am.getConnectedUser();
        }
        return connectedUser;
    }
    
    
    public void setAlbum(Long id) {
        this.album = albumDao.getById(id);
    }
    
}

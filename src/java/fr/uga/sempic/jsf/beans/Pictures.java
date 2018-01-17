/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.Picture;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author denisbolshakov
 */
@Named
@SessionScoped
public class Pictures implements Serializable{
    private List<Picture> pictureList;
    public Pictures(){
        // this.albumList ;
        AuthManager am = new AuthManager();
    //    SempicUser connectedUser = am.getConnectedUser();
      //  AlbumDao ad = new AlbumDao();
       // albumList = ad.getById(connectedUser.getId());
    }
    
    public List getList() {
        return pictureList;
    }
}

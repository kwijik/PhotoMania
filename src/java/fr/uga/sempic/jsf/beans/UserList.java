/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.sempic.jsf.beans;

import fr.uga.miashs.sempic.model.SempicUser;
import fr.uga.miashs.sempic.model.datalayer.SempicUserDao;
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
public class UserList implements Serializable{
     private List<SempicUser> userList;
     
     @Inject
     AuthManager am;
     SempicUser connectedUser;
     @EJB
     SempicUserDao userDao;
     
     public UserList(){
         
     }
     
     public SempicUser getConnectedUser() {
        if(connectedUser == null){
            connectedUser = am.getConnectedUser();
        }
        return connectedUser;
    }
     
    public List<SempicUser> getList(){
        return userDao.readAll();
    }
    
    
}

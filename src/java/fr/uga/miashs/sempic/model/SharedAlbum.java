/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author denisbolshakov
 */

@Entity
public class SharedAlbum implements Serializable{
  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   // private Long receiverId;
    
   // private Long albumId;
    
    @NotNull
    @ManyToOne
    private SempicUser receiver;
    
    @NotNull
    @ManyToOne
    private Album album;
    
    public SharedAlbum ( ){
       
    }
    
    public SharedAlbum (Album album, SempicUser receiver ){
        this.album = album;
        this.receiver = receiver;
    }
    
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public SempicUser getReceiver() {
        return receiver;
    }

    public void setReceiver(SempicUser receiver) {
        this.receiver = receiver;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}

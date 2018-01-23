/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.model.datalayer.PictureStore;
import static fr.uga.miashs.sempic.model.datalayer.PictureStore.THUMBNAILS;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @ManyToOne
    private Album album;
    private String name;
    
    
   public String getPicturePath() {
        return PictureStore.UPLOADSWEB.resolve(name).toString();
    }
   
    public String getThumbnailPath() {
        Path pic = Paths.get(name);
        
        try {
            PictureStore ps = new PictureStore();
            ps.getPictureStream(pic, 120);
            return PictureStore.getAbsolutePath(PictureStore.THUMBNAILSWEB.resolve(String.valueOf(120)), pic).toString();
        } catch (IOException ex) {
            Logger.getLogger(Picture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
   
    public void setAlbum(Album album) {
        this.album = album;
    }
    
    
    public Album getAlbum() {
        return album;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Picture)) {
            return false;
        }
        Picture other = (Picture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.uga.miashs.sempic.model.Picture[ id=" + id + " ]";
    }
    
}

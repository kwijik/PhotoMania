/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.uga.miashs.sempic.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;



@Entity
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String description;
    
    @NotNull
    @ManyToOne
    private SempicUser owner;
    @OneToMany(mappedBy="album", fetch=FetchType.LAZY)
    private List<Picture> pictures; 

    /**
     *
     */
    protected Album(){
        
    }
    
    public Album(SempicUser u){
        owner = u;
    }
    
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SempicUser getOwner() {
        return owner;
    }

    public void setOwner(SempicUser owner) {
        this.owner = owner;
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
    
    public void addPicture(Picture p){
        this.pictures.add(p);
    }
    
    public List<Picture>  getPictures(){
        return this.pictures;
    }
    
    public int getNumberOfPictures(){
        return this.pictures.size();
    }
    
    public void deletePicture(Picture p){
        this.pictures.remove(p);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.uga.miashs.sempic.model.Album[ id=" + id + " ]";
    }
    
}

package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.model.Picture;
import fr.uga.miashs.sempic.model.SempicUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-17T01:27:33")
@StaticMetamodel(Album.class)
public class Album_ { 

    public static volatile SingularAttribute<Album, SempicUser> owner;
    public static volatile SingularAttribute<Album, String> titre;
    public static volatile SingularAttribute<Album, String> description;
    public static volatile SingularAttribute<Album, Long> id;
    public static volatile ListAttribute<Album, Picture> pictures;

}
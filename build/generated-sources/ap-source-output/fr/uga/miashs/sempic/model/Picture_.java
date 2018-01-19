package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.model.Album;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-19T14:16:35")
@StaticMetamodel(Picture.class)
public class Picture_ { 

    public static volatile SingularAttribute<Picture, Album> album;
    public static volatile SingularAttribute<Picture, String> name;
    public static volatile SingularAttribute<Picture, Long> id;

}
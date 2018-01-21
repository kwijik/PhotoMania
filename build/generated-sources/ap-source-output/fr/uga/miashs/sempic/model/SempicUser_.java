package fr.uga.miashs.sempic.model;

import fr.uga.miashs.sempic.util.Roles;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-21T20:03:02")
@StaticMetamodel(SempicUser.class)
public class SempicUser_ { 

    public static volatile SingularAttribute<SempicUser, String> firstname;
    public static volatile SingularAttribute<SempicUser, String> salt;
    public static volatile SetAttribute<SempicUser, Roles> roles;
    public static volatile SingularAttribute<SempicUser, Long> id;
    public static volatile SingularAttribute<SempicUser, String> email;
    public static volatile SingularAttribute<SempicUser, String> passwordHash;
    public static volatile SingularAttribute<SempicUser, String> lastname;

}
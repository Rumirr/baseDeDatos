package com.mycompany.hibernatetest.Model.entities;

import com.mycompany.hibernatetest.Model.entities.Movie;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-22T19:35:20")
@StaticMetamodel(Actor.class)
public class Actor_ { 

    public static volatile CollectionAttribute<Actor, Movie> movieCollection;
    public static volatile SingularAttribute<Actor, String> name;
    public static volatile SingularAttribute<Actor, Integer> idActor;

}
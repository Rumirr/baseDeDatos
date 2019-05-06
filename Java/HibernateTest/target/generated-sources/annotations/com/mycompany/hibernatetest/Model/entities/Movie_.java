package com.mycompany.hibernatetest.Model.entities;

import com.mycompany.hibernatetest.Model.entities.Actor;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-22T19:35:20")
@StaticMetamodel(Movie.class)
public class Movie_ { 

    public static volatile SingularAttribute<Movie, Integer> idMovie;
    public static volatile SingularAttribute<Movie, String> title;
    public static volatile CollectionAttribute<Movie, Actor> actorCollection;

}
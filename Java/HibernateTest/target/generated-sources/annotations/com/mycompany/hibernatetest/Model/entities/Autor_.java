package com.mycompany.hibernatetest.Model.entities;

import com.mycompany.hibernatetest.Model.entities.Llibre;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-22T19:35:20")
@StaticMetamodel(Autor.class)
public class Autor_ { 

    public static volatile ListAttribute<Autor, Llibre> llibres;
    public static volatile SingularAttribute<Autor, Long> id;
    public static volatile SingularAttribute<Autor, String> nom;

}
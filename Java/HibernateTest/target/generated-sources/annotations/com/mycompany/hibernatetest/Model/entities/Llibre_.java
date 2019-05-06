package com.mycompany.hibernatetest.Model.entities;

import com.mycompany.hibernatetest.Model.entities.Autor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-22T19:35:20")
@StaticMetamodel(Llibre.class)
public class Llibre_ { 

    public static volatile SingularAttribute<Llibre, String> titol;
    public static volatile SingularAttribute<Llibre, Long> id;
    public static volatile SingularAttribute<Llibre, Autor> autor;

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernatetest.Model;

import com.mycompany.hibernatetest.Model.entities.Actor;
import java.util.ArrayList;
import javassist.bytecode.Bytecode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author horabaixa
 */
public abstract class ActorCRUD {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public static Actor findById(Integer id) {
        Session session = SESSION_FACTORY.openSession();

        Query query = session.getNamedQuery("Actor.findByIdActor")
                .setParameter("idActor", id);

        Actor a = (Actor) query.uniqueResult();

        session.close();
        return a;
        //solo si se utiliza un arrayList
        //return (a != null) ? a : null;
    }

    public static void insert(Actor actor) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(actor);
        session.getTransaction().commit();
        session.close();

    }

    public static void update(Actor actor) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(actor);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Actor actor) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(actor);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer idActor) {
        Actor actor = ActorCRUD.findById(idActor);
        ActorCRUD.delete(actor);
    }

    public static ArrayList<Actor> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Actor.findAll");
        ArrayList<Actor> actores = (ArrayList<Actor>) query.list();
        session.close();

        return actores;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class CRUD {
    protected static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    /**
     * Método que guarda un fila en la base de datos.
     *
     * @param o {@link Object},
     */
    protected static void insert(Object o) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método que elimina un fila de la base de datos.
     *
     * @param o {@link Object}.
     */
    protected static void delete(Object o) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Método que actualiza un fila en la base de datos.
     *
     * @param o
     */
    protected static void update(Object o) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }
}

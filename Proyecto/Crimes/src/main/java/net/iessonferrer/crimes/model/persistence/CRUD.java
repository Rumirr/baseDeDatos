/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.persistence;

import com.mycompany.hibernatetest.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public abstract class CRUD {

    protected static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    protected static void insert(Object o) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

}

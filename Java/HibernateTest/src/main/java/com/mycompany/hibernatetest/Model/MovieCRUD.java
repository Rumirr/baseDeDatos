/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hibernatetest.Model;

import com.mycompany.hibernatetest.Model.entities.Movie;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author horabaixa
 */
public class MovieCRUD {

    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();

    public static void insert(Movie movie) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.save(movie);
        session.getTransaction().commit();;
        session.close();
    }

    public static void update(Movie movie) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.update(movie);
        session.getTransaction().commit();;
        session.close();
    }

    public static void delete(Movie movie) {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();
        session.delete(movie);
        session.getTransaction().commit();;
        session.close();
    }
    public static void delete(Integer idMovie){
        Movie movie = MovieCRUD.findById(idMovie);
        MovieCRUD.delete(movie);
    }

    public static ArrayList<Movie> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Movie.findAll");
        ArrayList<Movie> movies = (ArrayList<Movie>) query.list();
        session.close();
        return movies;
    }
    
    public static Movie findById(Integer idMovie){
        Session session = SESSION_FACTORY.openSession();

        Query query = session.getNamedQuery("Movie.findByIdMovie")
                .setParameter("idMovie", idMovie);

        Movie movie = (Movie) query.uniqueResult();
        session.close();
        return movie;
    
    }
}

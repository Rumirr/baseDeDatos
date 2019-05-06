
import com.mycompany.hibernatetest.Model.ActorCRUD;
import com.mycompany.hibernatetest.Model.HibernateUtil;
import com.mycompany.hibernatetest.Model.MovieCRUD;
import com.mycompany.hibernatetest.Model.entities.Actor;
import com.mycompany.hibernatetest.Model.entities.Movie;
import java.util.ArrayList;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author horabaixa
 */
public class TestMovie {

    public static void main(String[] args) {

//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        
//        Actor a1 = new Actor();
//        a1.setName("Actor1");
//        
//        Actor a2 = new Actor();
//        a2.setName("Actor2");
//        
//        Movie m = new Movie();
//        m.setTitle("Movie1");
//        
//        ArrayList<Actor> actors = new ArrayList<Actor>();
//        actors.add(a1);
//        actors.add(a2);
//        
//        m.setActorCollection(actors);
//        
//        ArrayList<Movie> pelis = new ArrayList<Movie>();
//        pelis.add(m);
//        a1.setMovieCollection(pelis);
//        a2.setMovieCollection(pelis);
//        
//        session.save(m);
//        session.save(a1);
//        session.save(a2);
//        
//        session.getTransaction().commit();
//        session.close();

//        Actor a1 = ActorCRUD.findById(1);
//        System.out.println(a1.toString());
//        
//        Actor a3 = new Actor();
//        a3.setName("Actor3");
//        ActorCRUD.insert(a3);

        Movie m = new Movie();
        m.setTitle("Movie2");
        MovieCRUD.insert(m);
        
        Movie m2 = new Movie();
        m2.setTitle("Movie3");
        MovieCRUD.insert(m2);
        
        System.out.println("Se ha añadido Movie 2 y 3");
        ArrayList<Movie> movies = MovieCRUD.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
        
        System.out.println("Se ha borrado movie 1 ");
        MovieCRUD.delete(4);
        movies = MovieCRUD.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
        
        System.out.println("Se ha cambiado el nombre de movie2 a movieUpdate");
        Movie movieUpdate = MovieCRUD.findById(2);
        movieUpdate.setTitle("movieUpdate");
        
        MovieCRUD.update(movieUpdate);
        movies = MovieCRUD.findAll();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }

    }

}

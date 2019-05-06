
import com.mycompany.hibernatetest.Model.HibernateUtil;
import com.mycompany.hibernatetest.Model.entities.Autor;
import com.mycompany.hibernatetest.Model.entities.Llibre;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author horabaixa
 */
public class Main {
    
    public static void main(String[] args) {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
//        Autor a = new Autor();
//        a.setNom("Hibernate");
//        session.save(a);
        

//        String hql = "FROM Autor";
//        
//        Query query = session.createQuery(hql);
//        
//        List<Autor> autors = query.list();
//        
//        for (Autor autor : autors) {
//            System.out.println(autor.toString());
//        }
        
        // Query amb Join
        String hql = "SELECT l FROM Autor AS a INNER JOIN a.llibres AS l WHERE a.id = 1";

        Query query = session.createQuery(hql);
        List<Llibre> llibres = query.list();
        
        session.getTransaction().commit();
        session.close();
    }
}

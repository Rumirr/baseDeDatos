/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.persistence;

import java.util.ArrayList;
import java.util.Hashtable;
import net.iessonferrer.crimes.model.entities.Location;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class LocationCRUD {

    public static void insert(Location l) {
        CRUD.insert(l);
    }

    public static void insert(ArrayList<Location> locations) {
        Hashtable<String, Location> hashtable = new Hashtable<String, Location>();
        for (Location location : locations) {

            if (LocationCRUD.findByName(location) == null) {
                hashtable.put(location.getName(), location);
            }

        }

        hashtable.forEach((id, location) -> {
            insert(location);
        });
    }

    public static Location findByName(Location l) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Location.findByName")
                .setParameter("name", l.getName());
        Location location = (Location) query.list();
        session.close();
        return location;
    }

    public static ArrayList<Location> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Location.findAll");
        ArrayList<Location> locations = (ArrayList<Location>) query.list();
        session.close();
        return locations;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.persistence;

import java.util.ArrayList;
import java.util.Hashtable;
import net.iessonferrer.crimes.model.entities.CrimeType;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class CrimeTypeCRUD extends CRUD {

    public static void insert(CrimeType crimeType) {
        CRUD.insert(crimeType);
    }

    public static void insert(ArrayList<CrimeType> crimeTypes) {
        Hashtable<String, CrimeType> hashtable = new Hashtable<String, CrimeType>();
        for (CrimeType crimeType : crimeTypes) {
            hashtable.put(crimeType.getId(), crimeType);
        }

        hashtable.forEach((id, crimeType) -> {
            insert(crimeType);
        });
    }

    public static ArrayList<CrimeType> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("CrimeType.findAll");
        ArrayList<CrimeType> crimeTypes = (ArrayList<CrimeType>) query.list();
        session.close();
        return crimeTypes;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.persistence;

import java.util.ArrayList;
import java.util.Hashtable;
import net.iessonferrer.crimes.model.entities.Case;
import net.iessonferrer.crimes.model.entities.Location;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class CaseCRUD extends CRUD {

    public static void insert(Case c) {
        CRUD.insert(c);
    }

    public static void insert(ArrayList<Case> cases) {
        Hashtable<String, Case> hashtable = new Hashtable<String, Case>();
        for (Case aCase : cases) {
            hashtable.put(aCase.getCaseNumber(), aCase);
        }

        hashtable.forEach((id, aCase) -> {
            insert(aCase);
        });
    }

    public ArrayList<Case> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Case.findAll");
        ArrayList<Case> cases = (ArrayList<Case>) query.list();
        session.close();
        return cases;
    }

}

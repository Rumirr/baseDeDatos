/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import AEMET.model.entities.Estacion;
import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Hashtable;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class EstacionCRUD extends CRUD {

    /**
     * Método que inserta una
     * {@linkplain AEMET.Model.entities.Estacion Estación} en la base de datos.
     *
     * @param e {@linkplain AEMET.Model.entities.Estacion Estación} a insertar.
     */
    public static void insert(Estacion e) {
        CRUD.insert(e);
    }

    /**
     * Método que guarda un {@link ArrayList} en la base de datos.
     *
     * @param estaciones Datos provenientes de la API.
     */
    public static void insert(ArrayList<Estacion> estaciones) {
        Hashtable<String, Estacion> estacionesHashtable = new Hashtable<String, Estacion>();
        for (Estacion estacion : estaciones) {
            if (EstacionCRUD.findById(estacion.getIdEstacion()) == null) {
                estacionesHashtable.put(estacion.getIdEstacion(), estacion);
            }
        }
        estacionesHashtable.forEach((idEstacion, estacion) -> {
            estacion.setProvincia(ProvinciaCRUD.findByName(estacion.getProvincia().getProvincia()));
            EstacionCRUD.insert(estacion);
        });
    }

    /**
     * Método que borra una {@linkplain AEMET.Model.entities.Estacion Estación}
     * de la base de datos.
     *
     * @param e {@linkplain AEMET.Model.entities.Estacion Estación} a borrar.
     */
    public static void delete(Estacion e) {
        CRUD.delete(e);
    }

    /**
     * Método que borra una {@linkplain AEMET.Model.entities.Estacion Estación}
     * de la base de datos dado su id.
     *
     * @param id Id de la estación.
     */
    public static void delete(String id) {
        delete(findById(id));

    }

    /**
     * Método que actualiza una
     * {@linkplain AEMET.Model.entities.Estacion Estación} de la base de datos.
     *
     * @param e {@linkplain AEMET.Model.entities.Estacion Estación} a actualiza.
     */
    public static void update(Estacion e) {
        CRUD.update(e);
    }

    //QUERIES
    /**
     * Método que devuelve todas las estaciones.
     *
     * @return {@link ArrayList}
     */
    public static ArrayList<Estacion> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Estacion.findAll");
        ArrayList<Estacion> estaciones = (ArrayList<Estacion>) query.list();
        session.close();
        return estaciones;
    }

    /**
     * Método que devuelve una
     * {@linkplain AEMET.model.entities.Estacion Estacion} dado si id.
     *
     * @param id Id de la estación.
     * @return {@linkplain AEMET.Model.entities.Estacion Estación}.
     */
    public static Estacion findById(String id) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Estacion.findByIdEstacion")
                .setParameter("idEstacion", id);
        Estacion e = (Estacion) query.uniqueResult();
        session.close();
        return e;
    }

}

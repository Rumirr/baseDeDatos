/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Hashtable;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class ProvinciaCRUD extends CRUD {

    /**
     * Método que inserta una
     * {@linkplain AEMET.Model.entities.Provincia Provincia} a la base de datos.
     *
     * @param p Provincia a insertar.
     */
    public static void insert(Provincia p) {
        CRUD.insert(p);
    }

    /**
     * Método que guarda un {@link ArrayList} en la base de datos.
     *
     * @param provinciasApi Datos provenientes de la API.
     */
    public static void insert(ArrayList<Provincia> provinciasApi) {
        Hashtable<String, Provincia> provinciasHashTable = new Hashtable<String, Provincia>();

        // añadiendo las provincias a la HashTable
        for (Provincia provincia : provinciasApi) {
            if (ProvinciaCRUD.findByName(provincia.getProvincia()) == null) {
                provinciasHashTable.put(provincia.getProvincia(), provincia);
            }
        }

        // guardando las provincias en la base de datos
        provinciasHashTable.forEach((id, provincia) -> {
            ProvinciaCRUD.insert(provincia);
        });
    }

    /**
     * Método que borra de la base de datos una provincia dado un objeto tipo
     * {@linkplain AEMET.Model.entities.Provincia Provincia}.
     *
     * @param p Provincia a borrar.
     */
    public static void delete(Provincia p) {
        CRUD.delete(p);
    }

    /**
     * Método que borra de la base de datos una provincia dado un id.
     *
     * @param id Id de la provincia borrar.
     */
    public static void delete(Integer id) {

        ProvinciaCRUD.delete(findById(id));

    }

    /**
     * Método que actualiza en la base de datos una provincia dado un objeto
     * tipo {@linkplain AEMET.Model.entities.Provincia Provincia}.
     *
     * @param p Provincia a actualizar.
     */
    public static void update(Provincia p) {
        CRUD.update(p);
    }

    //Queries
    /**
     * Método que devuelve todas las provincias.
     *
     * @return {@link ArrayList}.
     */
    public static ArrayList<Provincia> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Provincia.findAll");
        ArrayList<Provincia> provincias = (ArrayList<Provincia>) query.list();
        session.close();
        return provincias;
    }

    /**
     * Método que devuelve una
     * {@linkplain AEMET.Model.entities.Provincia Provincia} dado un id.
     *
     * @param id Identificado de la
     * {@linkplain AEMET.Model.entities.Provincia Provincia}.
     * @return {@linkplain AEMET.Model.entities.Provincia Provincia} encontrada.
     */
    public static Provincia findById(Integer id) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Provincia.findByIdProvincia")
                .setParameter("idProvincia", id);
        Provincia p = (Provincia) query.uniqueResult();
        session.close();
        return p;
    }

    /**
     * Método que devuelve una
     * {@linkplain AEMET.Model.entities.Provincia Provincia} dado su nombre.
     *
     * @param provincia Nombre de la
     * {@linkplain AEMET.Model.entities.Provincia Provincia} a buscar.
     * @return {@linkplain AEMET.Model.entities.Provincia Provincia} encontrada.
     */
    public static Provincia findByName(String provincia) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("Provincia.findByProvincia")
                .setParameter("provincia", provincia);
        Provincia p = (Provincia) query.uniqueResult();
        session.close();
        return p;
    }

}

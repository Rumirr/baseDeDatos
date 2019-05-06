/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import AEMET.model.entities.Estacion;
import AEMET.model.entities.ObservacionTiempo;
import AEMET.model.entities.ObservacionTiempoPK;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class ObservacionTiempoCRUD extends CRUD {

    /**
     * Método que guarda un elemento en la base de datos.
     *
     * @param ot Elemento a guardar.
     */
    public static void insert(ObservacionTiempo ot) {
        CRUD.insert(ot);

    }

    /**
     * Método que guarda un {@link ArrayList} en la base de datos.
     *
     * @param prediccionesApi Datos provenientes de la API.
     */
    public static void insert(ArrayList<ObservacionTiempo> prediccionesApi) {
        ArrayList<ObservacionTiempo> prediccionTiempos = new ArrayList<ObservacionTiempo>();

        for (ObservacionTiempo prediccionTiempo : prediccionesApi) {
            if (ObservacionTiempoCRUD.findbyId(prediccionTiempo.getObservacionTiempoPK()) == null) {
                prediccionTiempos.add(prediccionTiempo);
            }
        }

        for (ObservacionTiempo prediccionTiempo : prediccionTiempos) {
            insert(prediccionTiempo);
        }

    }

    /**
     * Método que actualiza un elemento en la base de datos.
     *
     * @param ot Elemento a actualizar.
     */
    public static void update(ObservacionTiempo ot) {
        CRUD.update(ot);
    }

    /**
     * Método para eliminar un elemento de la base de datos.
     *
     * @param ot Elemento a eliminar.
     */
    public static void delete(ObservacionTiempo ot) {
        CRUD.delete(ot);
    }

    /**
     * Método que devuelve todas las predicciones de tiempo.
     *
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findAll() {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("ObservacionTiempo.findAll");
        ArrayList<ObservacionTiempo> predicciones = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return predicciones;
    }

    /**
     * Método que devuelve una
     * {@link AEMET.model.entities.ObservacionTiempo Observacion de Tiempo}
     * dado una
     * {@link AEMET.model.entities.ObservacionTiempoPK Primary Key}.
     *
     * @param id{@link AEMET.model.entities.ObservacionTiempoPK Primary Key}.
     * @return
     * {@link AEMET.model.entities.ObservacionTiempo Observacion de Tiempo}
     */
    public static ObservacionTiempo findbyId(ObservacionTiempoPK id) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("ObservacionTiempo.findById")
                .setParameter("id", id);
        ObservacionTiempo ot = (ObservacionTiempo) query.uniqueResult();
        session.close();
        return ot;

    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} que
     * contiene un id igual al dado.
     *
     * @param id Id de la {@linkplain AEMET.model.entities.Estacion Estacion} de
     * la cual queremos obtener la
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo}.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByEstacion(String id) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.getNamedQuery("ObservacionTiempo.findByIdEstacion")
                .setParameter("idEstacion", id);
        ArrayList<ObservacionTiempo> predicciones = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return predicciones;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} dada
     * una {@link GregorianCalendar Fecha}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByDate(GregorianCalendar date) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) = :fecha")
                .setParameter("fecha", date.getTime());
        ArrayList<ObservacionTiempo> prediccion = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return prediccion;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} dada
     * una {@link GregorianCalendar Fecha} y una
     * {@linkplain AEMET.model.entities.Estacion Estación}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @param estacion
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByDate(GregorianCalendar date, Estacion estacion) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) = :fecha AND p.observacionTiempoPK.idEstacion = :id")
                .setParameter("fecha", date.getTime())
                .setParameter("id", estacion.getIdEstacion());
        ArrayList<ObservacionTiempo> prediccion = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return prediccion;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} dada
     * una {@link GregorianCalendar Fecha}, icluyendo una hora.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByDateAndHour(GregorianCalendar date) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE p.observacionTiempoPK.fecha = :fecha")
                .setParameter("fecha", date.getTime());
        ArrayList<ObservacionTiempo> prediccion = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return prediccion;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} dada
     * una {@link GregorianCalendar Fecha}, icluyendo una hora, y dada una
     * {@linkplain AEMET.model.entities.Estacion Estacion}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @param estacion {@linkplain AEMET.model.entities.Estacion Estacion}.
     * @return {@link ArrayList}.
     */
    public static ObservacionTiempo findByDateAndHour(GregorianCalendar date, Estacion estacion) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE p.observacionTiempoPK.fecha = :fecha AND p.observacionTiempoPK.idEstacion = :id")
                .setParameter("fecha", date.getTime())
                .setParameter("id", estacion.getIdEstacion());
        ObservacionTiempo prediccion = (ObservacionTiempo) query.uniqueResult();
        session.close();
        return prediccion;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo}
     * anteriores a una {@link GregorianCalendar Fecha}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByBeforeDate(GregorianCalendar date) {
        // TODO: probar
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) < :d")
                .setParameter("d", date.getTime());
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo}
     * anteriores a una {@link GregorianCalendar Fecha} y dada una
     * {@linkplain AEMET.model.entities.Estacion Estacion}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @param estacion {@linkplain AEMET.model.entities.Estacion Estacion}.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByBeforeDate(GregorianCalendar date, Estacion estacion) {
        // TODO: probar
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) < :d AND p.observacionTiempoPK.idEstacion = :id")
                .setParameter("d", date.getTime())
                .setParameter("id", estacion.getIdEstacion());
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo}
     * posteriores a una {@link GregorianCalendar Fecha}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByAfterDate(GregorianCalendar date) {
        // TODO: probar
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) > :d")
                .setParameter("d", date.getTime());
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo}
     * posteriores a una {@link GregorianCalendar Fecha} y dada una
     * {@linkplain AEMET.model.entities.Estacion Estacion}.
     *
     * @param date {@link GregorianCalendar Fecha} de la observación del tiempo.
     * @param estacion {@linkplain AEMET.model.entities.Estacion Estacion}.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findByAfterDate(GregorianCalendar date, Estacion estacion) {
        // TODO: probar
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) > :d AND p.observacionTiempoPK.idEstacion = :id")
                .setParameter("d", date.getTime())
                .setParameter("id", estacion.getIdEstacion());
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} entre
     * dos {@link GregorianCalendar Fechas}.
     *
     * @param start {@link GregorianCalendar Fecha} de inicio.
     * @param end {@link GregorianCalendar Fecha} de fin.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findBetweenDates(GregorianCalendar start, GregorianCalendar end) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) > :start AND date(p.observacionTiempoPK.fecha) < :end")
                .setParameter("start", start)
                .setParameter("end", end);
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }

    /**
     * Método que devuelve un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} entre
     * dos {@link GregorianCalendar Fechas} y dada una
     * {@linkplain AEMET.model.entities.Estacion Estacion}.
     *
     * @param start {@link GregorianCalendar Fecha} de inicio.
     * @param end {@link GregorianCalendar Fecha} de fin.
     * @param estacion {@linkplain AEMET.model.entities.Estacion Estacion}.
     * @return {@link ArrayList}.
     */
    public static ArrayList<ObservacionTiempo> findBetweenDates(GregorianCalendar start, GregorianCalendar end, Estacion estacion) {
        Session session = CRUD.SESSION_FACTORY.openSession();
        Query query = session.createQuery("SELECT p FROM ObservacionTiempo p WHERE date(p.observacionTiempoPK.fecha) > :start AND date(p.observacionTiempoPK.fecha) < :end AND p.observacionTiempoPK.idEstacion = :id")
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("id", estacion.getIdEstacion());
        ArrayList<ObservacionTiempo> ot = (ArrayList<ObservacionTiempo>) query.list();
        session.close();
        return ot;
    }
}

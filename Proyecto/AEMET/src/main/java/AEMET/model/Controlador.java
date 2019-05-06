/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import AEMET.model.entities.Estacion;
import AEMET.model.entities.ObservacionTiempo;
import AEMET.model.entities.Provincia;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class Controlador {

    public enum timeObservacion {
        BEFORE, AFTER
    };

    public enum hourYesNo {
        YES, NO
    };

    public static void main(String[] args) throws Exception {
        int[] fecha = {
            2019,
            4 - 1,
            12,
            9,
            0,
            0
        };

        GregorianCalendar calendar = new GregorianCalendar(fecha[0], fecha[1], fecha[2], fecha[3], fecha[4], fecha[5]);

        ArrayList<ObservacionTiempo> p = ObservacionTiempoCRUD.findByDate(calendar);
        for (ObservacionTiempo prediccionTiempo : p) {
            System.out.println(prediccionTiempo.toString());
        }

//        saveObservacionTiempo("0016A");
    }

    /**
     * Método que obtiene un {@link ArrayList} de
     * {@linkplain AEMET.model.entities.Provincia Provincias} por parte de la
     * API de la AEMET y pasa el {@link ArrayList} de
     * {@linkplain AEMET.model.entities.Provincia Provincias} al método
     * {@linkplain AEMET.model.ProvinciaCRUD#insert(java.util.ArrayList) ProvinciaCRUD.insert()}.
     *
     * @throws Exception
     */
    public static void saveProvincias() throws Exception {

        ProvinciaCRUD.insert(Integracion.getProvincias());

    }
    public static ArrayList<Provincia> getProvincias(){
        return ProvinciaCRUD.findAll();
    }

    /**
     * Método que obtiene un {@link ArrayList} de
     * {@linkplain AEMET.model.entities.Estacion Estaciones} por parte de la API
     * de la AEMET y pasa el {@link ArrayList} de
     * {@linkplain AEMET.model.entities.Estacion Estaciones} al método
     * {@linkplain AEMET.model.EstacionCRUD#insert(java.util.ArrayList) EstacionCRUD.insert()}.
     *
     * @throws Exception
     */
    public static void saveEstaciones() throws Exception {
        EstacionCRUD.insert(Integracion.getEstaciones());

    }
    public static ArrayList<Estacion> getEstaciones(){
        return EstacionCRUD.findAll();
    }

    /**
     * Método que obtiene un {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} por
     * parte de la API de la AEMET y pasa el {@link ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo ObservacionTiempo} al
     * método
     * {@link AEMET.model.ObservacionTiempoCRUD#insert(java.util.ArrayList) ObservacionTiempoCRUD.insert()}.
     *
     * @throws Exception
     */
    public static void saveObservacionTiempo(Estacion estacion) throws Exception {
        ObservacionTiempoCRUD.insert(Integracion.getPrediccionTiempo(estacion.getIdEstacion()));
    }

    public static ArrayList<ObservacionTiempo> getObservacionBeforeOrAfterDate(GregorianCalendar date, Estacion estacion, timeObservacion t) throws Exception {
        if (t == timeObservacion.AFTER) {
            saveObservacionTiempo(estacion);
            return ObservacionTiempoCRUD.findByAfterDate(date, estacion);
        } else {
            return ObservacionTiempoCRUD.findByBeforeDate(date, estacion);
        }
    }

    public static ArrayList<ObservacionTiempo> getObservacionBetweenDates(GregorianCalendar start, GregorianCalendar end, Estacion estacion) throws Exception {
        saveObservacionTiempo(estacion);
        return ObservacionTiempoCRUD.findBetweenDates(start, end, estacion);
    }

    public static ArrayList<ObservacionTiempo> getObservacionTiempo(GregorianCalendar date, Estacion estacion, hourYesNo x) throws Exception {
        saveObservacionTiempo(estacion);
        if (x == hourYesNo.YES) {
            ArrayList<ObservacionTiempo> ot = new ArrayList<ObservacionTiempo>();
            ot.add(ObservacionTiempoCRUD.findByDateAndHour(date, estacion));
            return ot;
        }else{
            return ObservacionTiempoCRUD.findByDate(date, estacion);
        }
    }

}

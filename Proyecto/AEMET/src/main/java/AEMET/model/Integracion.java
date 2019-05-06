/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model;

import AEMET.API.APIUtils;
import AEMET.API.ObservacionEstacion;
import AEMET.model.entities.Estacion;
import AEMET.model.entities.ObservacionTiempo;
import AEMET.model.entities.ObservacionTiempoPK;
import AEMET.model.entities.Provincia;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class Integracion {
    // Entidad Estacion

    /**
     * Método que consigue todas las estaciones por parte de la API y las
     * canviete en etidades para ser guardadas en la base de datos.
     *
     * @return {@link ArrayList} de entidad estacion.
     * @throws Exception
     */
    public static ArrayList<AEMET.model.entities.Estacion> getEstaciones() throws Exception {
        ArrayList<AEMET.API.Estacion> stations = APIUtils.getStations();
        ArrayList<AEMET.model.entities.Estacion> estaciones = new ArrayList<Estacion>();
        
        
        for (AEMET.API.Estacion station : stations) {
            estaciones.add(convertStation(station));

        }
        return estaciones;
    }

    /**
     * Método que convierte los atributos de una estación recibida por parte de
     * la API a los tipos correcto de los atributos utilizado por la entidad
     * estación.
     *
     * @param station{@link AEMET.API.Estacion} recibida por parte de la API.
     * @return {@linkplain AEMET.model.entities.Estacion} tipo entidad.
     */
    private static Estacion convertStation(AEMET.API.Estacion station) {
        Estacion e = new Estacion();
        e.setIdEstacion(station.getIndicativo());
        e.setNombre(station.getNombre());
        e.setAltitud(Short.parseShort(station.getAltitud()));
        e.setLatitud(toGradosDecimal(station.getLatitud()));
        e.setLongitud(toGradosDecimal(station.getLongitud()));
        e.setProvincia(new Provincia(station.getProvincia()));
        return e;
    }

    /**
     * Método que conviete los grados sexagesimales a grados decimales.
     *
     * @param grados Grados sexagesimales.
     * @return Grados decimales.
     */
    private static BigDecimal toGradosDecimal(String grados) {
        //Decimal Degrees = degrees + (minutes/60) + (seconds/3600)
        //554433
        grados = grados.replaceAll("\\D", "");
        int gradosSexagesimales = Integer.parseInt(grados);
        double segundos = gradosSexagesimales % 100;
        double minutos = (gradosSexagesimales / 100) % 100;
        double horas = (gradosSexagesimales / 10000) % 100;

        BigDecimal gradosDecimal = BigDecimal.ZERO;
        gradosDecimal = gradosDecimal.add(new BigDecimal(horas));
        gradosDecimal = gradosDecimal.add(new BigDecimal(minutos / 60));
        gradosDecimal = gradosDecimal.add(new BigDecimal(segundos / 3600));

        gradosDecimal = gradosDecimal.setScale(10, RoundingMode.FLOOR);

        return gradosDecimal;
    }

    // Entidad Provincia
    /**
     * Método que obtiene las provincias a partir de las estaciones provenientes
     * por parte de la api.
     *
     * @return {@link  ArrayList} de las provincias donde se encuentra la
     * estación
     * @throws Exception
     */
    public static ArrayList<AEMET.model.entities.Provincia> getProvincias() throws Exception {
        ArrayList<AEMET.API.Estacion> station = APIUtils.getStations();
        ArrayList<AEMET.model.entities.Provincia> provincias = new ArrayList<AEMET.model.entities.Provincia>();
        for (AEMET.API.Estacion estacion : station) {
            provincias.add(convertProvincia(estacion));
        }

        return provincias;
    }

    /**
     * Método que obtine el nombre de la provincia donde se encuentra la
     * estación.
     *
     * @param s{@link AEMET.API.Estacion} que viene de la API
     * @return {@linkplain AEMET.model.entities.Provincia} con el nombre
     * asignado
     */
    private static AEMET.model.entities.Provincia convertProvincia(AEMET.API.Estacion s) {
        AEMET.model.entities.Provincia p = new Provincia();
        p.setProvincia(s.getProvincia());
        return p;
    }

    // Entidad PredicciónTiempo
    /**
     * Métode que conviete un conjunto de observaciones del tiempo
     * ({@link AEMET.API.ObservacionEstacion}) a otro conjunto de observaciones
     * tipo {@link AEMET.model.entities.ObservacionTiempo}
     *
     * @param id Estación a obtener las observaciones.
     * @return {@linkplain ArrayList} de
     * {@link AEMET.model.entities.ObservacionTiempo}
     * @throws Exception
     */
    public static ArrayList<AEMET.model.entities.ObservacionTiempo> getPrediccionTiempo(String id) throws Exception {
        ArrayList<AEMET.API.ObservacionEstacion> observacion = APIUtils.getObservacionEstacion(id);
        ArrayList<AEMET.model.entities.ObservacionTiempo> prediccionTiempo = new ArrayList<ObservacionTiempo>();

        for (ObservacionEstacion observacionEstacion : observacion) {
            prediccionTiempo.add(convertPrediccionTiempo(observacionEstacion));
        }
        return prediccionTiempo;
    }

    /**
     * Método que recibe un String con formato de fecha y devuelve un objeto
     * Date.
     *
     * @param fecha {@link String} conformato YYY-MM-DDTHH:MM:SS Y se leañade
     * una z para conplir con los requisitos para pasar una objeto {@link Date}.
     * @return {@link Date}
     */
    private static Date toDate(String fecha) {
        if (fecha == null) {
            return Date.from(Instant.now());
        }
        fecha = fecha + "z";

        return Date.from(Instant.parse(fecha));

    }

    /**
     * Método que devuelve un nuevo {@link BigDecimal} a partir de un {@link Float}.
     * @param value Posible valor nulo.
     * @return null o {@link BigDecimal}
     */
    private static BigDecimal getDefaultValue(Float value) {
        return (value != null) ? new BigDecimal(value) : null;
    }

    /**
     * Método que conviete el objeto {@link AEMET.API.ObservacionEstacion} a un
     * objeto {@link AEMET.model.entities.ObservacionTiempo}
     *
     * @param o{@link AEMET.API.ObservacionEstacion}
     * @return {@link AEMET.model.entities.ObservacionTiempo}
     */
    private static AEMET.model.entities.ObservacionTiempo convertPrediccionTiempo(AEMET.API.ObservacionEstacion o) {
        AEMET.model.entities.ObservacionTiempo p = new ObservacionTiempo();

        p.setObservacionTiempoPK(new ObservacionTiempoPK(o.getIdema(), toDate(o.getFint())));
        p.setUbicacion(o.getUbi());
        p.setPrecipitacion(getDefaultValue(o.getPrec()));
        p.setVientoVeloMax(getDefaultValue(o.getVmax()));
        p.setVientoVeloMedia(getDefaultValue(o.getVv()));
        p.setVientoDirecMedia(getDefaultValue(o.getDv()));
        p.setHumedad(getDefaultValue(o.getHr()));
        p.setInsolacion(getDefaultValue(o.getInso()));
        p.setPresionAire(getDefaultValue(o.getPres()));
        p.setTemp(getDefaultValue(o.getTa()));
        p.setTempMin(getDefaultValue(o.getTamin()));
        p.setTempMax(getDefaultValue(o.getTamax()));
        p.setVisibilidad(getDefaultValue(o.getVis()));
        p.setEspesorNieve(getDefaultValue(o.getNieve()));
        // TODO: Añadir objeto estacion a cada prediccionTiempo si es necesario ¿preguntar?

        return p;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.integration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import net.iessonferrer.crimes.API.CrimeData;
import net.iessonferrer.crimes.API.CrimeRecord;
import net.iessonferrer.crimes.model.entities.Case;
import net.iessonferrer.crimes.model.entities.CrimeType;
import net.iessonferrer.crimes.model.entities.Location;
import net.iessonferrer.crimes.model.entities.LocationPK;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class Integracion {

    public static ArrayList<Case> getCases() {
        ArrayList<CrimeRecord> records = CrimeData.getCriminalRecords();
        ArrayList<Case> cases = new ArrayList<Case>();

        for (CrimeRecord record : records) {
            cases.add(convertToCase(record));
        }
        return cases;
    }

    private static Case convertToCase(CrimeRecord record) {
        Case c = new Case();
        c.setCaseNumber(record.getCaseNumber());
        c.setDate(toGregorianCalendar(record.getCrimeDate()).getTime());

        //
        CrimeType crimeType = new CrimeType();
        crimeType.setId(record.getCrimeTypeCode());
        c.setCrimeType(crimeType);
        //

        //
        double[] coordenadas = getLatitudeLongitude(record.getGpsLocation());
        LocationPK locationPK = new LocationPK();
        locationPK.setLongitude(new BigDecimal(coordenadas[0]));
        locationPK.setLatitude(new BigDecimal(coordenadas[1]));
        c.setLocation(new Location(locationPK));

        c.setSuspectArrested(toBoolean(record.getSuspectArrested()));
        c.setDomesticCrime(toBoolean(record.getDomesticCrime()));
        return c;
    }

    private static GregorianCalendar toGregorianCalendar(String dateString) {
        //        01/22/2019 11:45:00 AM
        String[] fechaHora = dateString.split(" ");
        String[] fecha = fechaHora[0].split("/");
        String[] hora = fechaHora[1].split(":");
        return new GregorianCalendar(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(hora[0]), Integer.parseInt(hora[1]), Integer.parseInt(hora[2]));

    }

    public static double[] getLatitudeLongitude(String gps) {
        //longitud, latitud

        gps = gps.replaceAll("\\(", "");
        gps = gps.replaceAll("\\)", "");

//        boolean[] negativo = {false, false};
//
        String[] coordenadas = gps.split(",");
        coordenadas[0].replaceAll(".", ",");
        coordenadas[1].replaceAll(".", ",");
//        if (coordenadas[0].contains("-")) {
//            negativo[0] = true;
//        }
//        if (coordenadas[1].contains("-")) {
//            negativo[1] = true;
//        }
        double longitud = Double.parseDouble(coordenadas[0]);
        double latitud = Double.parseDouble(coordenadas[1]);
        
        double[] c = new double[2];
        c[0] = longitud;
        c[1] = latitud;
                
return c;
        
    }

    private static boolean toBoolean(String value) {
        value = value.toLowerCase();
        return value.equals("n") ? false : true;
    }

    public static ArrayList<CrimeType> getCrimeTypes() {
        ArrayList<CrimeRecord> crimeRecords = CrimeData.getCriminalRecords();
        ArrayList<CrimeType> crimeTypes = new ArrayList<CrimeType>();
        for (CrimeRecord crimeRecord : crimeRecords) {
            CrimeType crimeType = new CrimeType();
            crimeType.setId(crimeRecord.getCrimeTypeCode());
            crimeType.setDescription(crimeRecord.getCrimeTypePrimaryDescription());
            crimeType.setAlternativeDescription(crimeRecord.getCrimeTypeSecondaryDescription());

            crimeTypes.add(crimeType);

        }
        return crimeTypes;
    }

    public static ArrayList<Location> getLocations() {
        ArrayList<CrimeRecord> crimeRecords = CrimeData.getCriminalRecords();
        ArrayList<Location> locations = new ArrayList<Location>();
        for (CrimeRecord crimeRecord : crimeRecords) {
            locations.add(convertToLocation(crimeRecord));
        }
        return locations;
    }

    private static Location convertToLocation(CrimeRecord crimeRecord) {
        Location l = new Location();
        double[] coordenadas = getLatitudeLongitude(crimeRecord.getGpsLocation());
        LocationPK locationPK = new LocationPK();
        locationPK.setLongitude(new BigDecimal(coordenadas[0]));
        locationPK.setLatitude(new BigDecimal(coordenadas[1]));

        l.setLocationPK(locationPK);
        l.setAddress(crimeRecord.getAddress());
        l.setName(crimeRecord.getLocationType());

        return l;

    }

}

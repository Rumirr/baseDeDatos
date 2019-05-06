/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET;

import AEMET.API.APIUtils;
import AEMET.API.Estacion;
import AEMET.API.ObservacionEstacion;
import java.util.ArrayList;

/**
 *
 * @author david
 */
public class MainIllesBalears {

    public static void main(String[] args) throws Exception {
        ArrayList<Estacion> stations = APIUtils.getStations();
        for (Estacion e : stations) {
            System.out.println(e.toString());
            if ("ILLES BALEARS".equals(e.getProvincia())) {
                try {
                    ArrayList<ObservacionEstacion> observaciones = APIUtils.getObservacionEstacion(e.getIndicativo());
                    for (ObservacionEstacion o : observaciones) {
                        System.out.println("\t" + o.toString());
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET;

import AEMET.model.Controlador;
import AEMET.model.EstacionCRUD;
import AEMET.model.entities.Estacion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Estacion> estaciones = EstacionCRUD.findAll();

        for (Estacion estacion : estaciones) {
            try {
                Controlador.saveObservacionTiempo(estacion);

            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println((char) 27 + "[31m" + "Terminado");

    }
}

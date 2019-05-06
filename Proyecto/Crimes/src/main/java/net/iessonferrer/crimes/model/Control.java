/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model;

import net.iessonferrer.crimes.model.integration.Integracion;
import net.iessonferrer.crimes.model.persistence.CaseCRUD;
import net.iessonferrer.crimes.model.persistence.CrimeTypeCRUD;
import net.iessonferrer.crimes.model.persistence.LocationCRUD;

/**
 *
 * @author david
 */
public class Control {

    public static void saveLocations() {
        LocationCRUD.insert(Integracion.getLocations());
    }

    public static void saveCrimeTypes() {
        CrimeTypeCRUD.insert(Integracion.getCrimeTypes());
    }

    public static void saveCases() {
        CaseCRUD.insert(Integracion.getCases());
    }

    public static void loadAll() {
        saveCases();
        saveLocations();
        saveCrimeTypes();
    }
}

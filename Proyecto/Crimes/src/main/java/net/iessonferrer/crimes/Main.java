/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.iessonferrer.crimes.API.CrimeRecord;
import net.iessonferrer.crimes.API.CrimeData;
import net.iessonferrer.crimes.model.Control;
import net.iessonferrer.crimes.model.integration.Integracion;

/**
 *
 * @author david
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*Final code for the main method will contain only this line*/
        Control.loadAll();
        
//        ArrayList<BigDecimal> b = Integracion.getLatitudeLongitude("(41.891620575,-87.63148948)");
//        System.out.println(b.get(0));
//        System.out.println(b.get(1));

        /*Delete the following code in the final version*/
//        ArrayList<CrimeRecord> records = CrimeData.getCriminalRecords();
//        01/22/2019 11:45:00 AM

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;


import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Rumirr
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String d = "2019-03-30T07:00:00";
        d = d + "z";
        CharSequence c = d;
        //d = d.replaceAll("T", " ");
        //String[] fechaHora = d.split(" ");
        Date date = Date.from(Instant.parse(d));

        System.out.println(date);
    }

}

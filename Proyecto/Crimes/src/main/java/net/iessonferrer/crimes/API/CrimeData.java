/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.API;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author david
 */
public class CrimeData {

    private static String DELIMITADOR = ",";

    private static ArrayList<List<String>> getRawCriminalRecords() throws FileNotFoundException, IOException {
        ArrayList<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Crimes-Chicago-Jan2019.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String otherThanQuote = " [^\"] ";
                String quotedString = String.format(" \" %s* \" ", otherThanQuote);
                String regex = String.format("(?x) "
                        + // enable comments, ignore white spaces
                        ",                         "
                        + // match a comma
                        "(?=                       "
                        + // start positive look ahead
                        "  (?:                     "
                        + //   start non-capturing group 1
                        "    %s*                   "
                        + //     match 'otherThanQuote' zero or more times
                        "    %s                    "
                        + //     match 'quotedString'
                        "  )*                      "
                        + //   end group 1 and repeat it zero or more times
                        "  %s*                     "
                        + //   match 'otherThanQuote'
                        "  $                       "
                        + // match the end of the string
                        ")                         ", // stop positive look ahead
                        otherThanQuote, quotedString, otherThanQuote);


                String[] values = line.split(regex, -1);
                records.add(Arrays.asList(values));
            }
        }
        return records;
    }

    private static CrimeRecord rawRecordToCrimeRecord(List<String> rawRecord) {
        CrimeRecord crime = new CrimeRecord();
        crime.setCaseNumber(rawRecord.get(0));
        crime.setCrimeDate(rawRecord.get(1));
        crime.setAddress(rawRecord.get(2));
        crime.setCrimeTypeCode(rawRecord.get(3));
        crime.setCrimeTypePrimaryDescription(rawRecord.get(4));
        crime.setCrimeTypeSecondaryDescription(rawRecord.get(5));
        crime.setLocationType(rawRecord.get(6));
        crime.setSuspectArrested(rawRecord.get(7));
        crime.setDomesticCrime(rawRecord.get(8));
        if (rawRecord.size() >= 17) {
            /*No tenim coordenades gps*/
            crime.setGpsLocation("(" + rawRecord.get(14) + "," + rawRecord.get(15) + ")");
        }
        return crime;

    }

    public static ArrayList<CrimeRecord> getCriminalRecords() {
        ArrayList<CrimeRecord> criminalRecords = null;
        try {
            ArrayList<List<String>> rawCriminalRecords = CrimeData.getRawCriminalRecords();
            criminalRecords = new ArrayList<>();
            for (List<String> rawRecord : rawCriminalRecords) {
                criminalRecords.add(CrimeData.rawRecordToCrimeRecord(rawRecord));
            }
        } catch (IOException ex) {
            Logger.getLogger(CrimeData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return criminalRecords;
    }

}

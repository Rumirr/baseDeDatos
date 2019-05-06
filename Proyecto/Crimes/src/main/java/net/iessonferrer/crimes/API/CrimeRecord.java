/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.API;

/**
 *
 * @author david
 */
public class CrimeRecord {
    private String caseNumber;
    private String crimeDate;
    private String address;
    private String crimeTypeCode;
    private String crimeTypePrimaryDescription;
    private String crimeTypeSecondaryDescription;
    private String locationType;
    private String suspectArrested;
    private String domesticCrime;
    private String gpsLocation;   

    @Override
    public String toString() {
        return "CrimeRecord{" + "caseNumber=" + caseNumber + ", crimeDate=" + crimeDate + ", address=" + address + ", crimeTypeCode=" + crimeTypeCode + ", crimeTypePrimaryDescription=" + crimeTypePrimaryDescription + ", crimeTypeSecondaryDescription=" + crimeTypeSecondaryDescription + ", locationType=" + locationType + ", suspectArrested=" + suspectArrested + ", domesticCrime=" + domesticCrime + ", gpsLocation=" + gpsLocation + '}';
    }

    
    public CrimeRecord() {
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(String crimeDate) {
        this.crimeDate = crimeDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrimeTypeCode() {
        return crimeTypeCode;
    }

    public void setCrimeTypeCode(String crimeTypeCode) {
        this.crimeTypeCode = crimeTypeCode;
    }

    public String getCrimeTypePrimaryDescription() {
        return crimeTypePrimaryDescription;
    }

    public void setCrimeTypePrimaryDescription(String crimeTypePrimaryDescription) {
        this.crimeTypePrimaryDescription = crimeTypePrimaryDescription;
    }

    public String getCrimeTypeSecondaryDescription() {
        return crimeTypeSecondaryDescription;
    }

    public void setCrimeTypeSecondaryDescription(String crimeTypeSecondaryDescription) {
        this.crimeTypeSecondaryDescription = crimeTypeSecondaryDescription;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getSuspectArrested() {
        return suspectArrested;
    }

    public void setSuspectArrested(String suspectArrested) {
        this.suspectArrested = suspectArrested;
    }

    public String getDomesticCrime() {
        return domesticCrime;
    }

    public void setDomesticCrime(String domesticCrime) {
        this.domesticCrime = domesticCrime;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }
    
    
    
    
}

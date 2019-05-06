/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
@Entity
@Table(name = "CASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Case.findAll", query = "SELECT c FROM Case c"),
    @NamedQuery(name = "Case.findByCaseNumber", query = "SELECT c FROM Case c WHERE c.caseNumber = :caseNumber"),
    @NamedQuery(name = "Case.findByDate", query = "SELECT c FROM Case c WHERE c.date = :date"),
    @NamedQuery(name = "Case.findBySuspectArrested", query = "SELECT c FROM Case c WHERE c.suspectArrested = :suspectArrested"),
    @NamedQuery(name = "Case.findByDomesticCrime", query = "SELECT c FROM Case c WHERE c.domesticCrime = :domesticCrime")})
public class Case implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CASE_NUMBER")
    private String caseNumber;
    @Basic(optional = false)
    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "SUSPECT_ARRESTED")
    private boolean suspectArrested;
    @Basic(optional = false)
    @Column(name = "DOMESTIC_CRIME")
    private boolean domesticCrime;
    @JoinColumn(name = "CRIME_TYPE", referencedColumnName = "ID")
    @ManyToOne
    private CrimeType crimeType;
    @JoinColumns({
        @JoinColumn(name = "LATITUDE", referencedColumnName = "LATITUDE"),
        @JoinColumn(name = "LONGITUDE", referencedColumnName = "LONGITUDE")})
    @ManyToOne
    private Location location;

    public Case() {
    }

    public Case(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Case(String caseNumber, Date date, boolean suspectArrested, boolean domesticCrime) {
        this.caseNumber = caseNumber;
        this.date = date;
        this.suspectArrested = suspectArrested;
        this.domesticCrime = domesticCrime;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getSuspectArrested() {
        return suspectArrested;
    }

    public void setSuspectArrested(boolean suspectArrested) {
        this.suspectArrested = suspectArrested;
    }

    public boolean getDomesticCrime() {
        return domesticCrime;
    }

    public void setDomesticCrime(boolean domesticCrime) {
        this.domesticCrime = domesticCrime;
    }

    public CrimeType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(CrimeType crimeType) {
        this.crimeType = crimeType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (caseNumber != null ? caseNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Case)) {
            return false;
        }
        Case other = (Case) object;
        if ((this.caseNumber == null && other.caseNumber != null) || (this.caseNumber != null && !this.caseNumber.equals(other.caseNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.iessonferrer.crimes.model.entities.Case[ caseNumber=" + caseNumber + " ]";
    }
    
}

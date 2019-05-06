/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
@Entity
@Table(name = "LOCATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByName", query = "SELECT l FROM Location l WHERE l.name = :name"),
    @NamedQuery(name = "Location.findByLatitude", query = "SELECT l FROM Location l WHERE l.locationPK.latitude = :latitude"),
    @NamedQuery(name = "Location.findByLongitude", query = "SELECT l FROM Location l WHERE l.locationPK.longitude = :longitude"),
    @NamedQuery(name = "Location.findByAddress", query = "SELECT l FROM Location l WHERE l.address = :address")})
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LocationPK locationPK;
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;
    @OneToMany(mappedBy = "location")
    private Collection<Case> caseCollection;

    public Location() {
    }

    public Location(LocationPK locationPK) {
        this.locationPK = locationPK;
    }

    public Location(LocationPK locationPK, String address) {
        this.locationPK = locationPK;
        this.address = address;
    }

    public Location(BigDecimal latitude, BigDecimal longitude) {
        this.locationPK = new LocationPK(latitude, longitude);
    }

    public LocationPK getLocationPK() {
        return locationPK;
    }

    public void setLocationPK(LocationPK locationPK) {
        this.locationPK = locationPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<Case> getCaseCollection() {
        return caseCollection;
    }

    public void setCaseCollection(Collection<Case> caseCollection) {
        this.caseCollection = caseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationPK != null ? locationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationPK == null && other.locationPK != null) || (this.locationPK != null && !this.locationPK.equals(other.locationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.iessonferrer.crimes.model.entities.Location[ locationPK=" + locationPK + " ]";
    }
    
}

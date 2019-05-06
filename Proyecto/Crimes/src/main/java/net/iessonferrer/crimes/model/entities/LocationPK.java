/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Gustavo Pereira Kurpel
 */
@Embeddable
public class LocationPK implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "LATITUDE")
    private BigDecimal latitude;
    @Basic(optional = false)
    @Column(name = "LONGITUDE")
    private BigDecimal longitude;

    public LocationPK() {
    }

    public LocationPK(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (latitude != null ? latitude.hashCode() : 0);
        hash += (longitude != null ? longitude.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationPK)) {
            return false;
        }
        LocationPK other = (LocationPK) object;
        if ((this.latitude == null && other.latitude != null) || (this.latitude != null && !this.latitude.equals(other.latitude))) {
            return false;
        }
        if ((this.longitude == null && other.longitude != null) || (this.longitude != null && !this.longitude.equals(other.longitude))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.iessonferrer.crimes.model.entities.LocationPK[ latitude=" + latitude + ", longitude=" + longitude + " ]";
    }
    
}

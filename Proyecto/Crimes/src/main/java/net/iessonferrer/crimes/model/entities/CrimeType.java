/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.iessonferrer.crimes.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "CRIME_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CrimeType.findAll", query = "SELECT c FROM CrimeType c"),
    @NamedQuery(name = "CrimeType.findById", query = "SELECT c FROM CrimeType c WHERE c.id = :id"),
    @NamedQuery(name = "CrimeType.findByDescription", query = "SELECT c FROM CrimeType c WHERE c.description = :description"),
    @NamedQuery(name = "CrimeType.findByAlternativeDescription", query = "SELECT c FROM CrimeType c WHERE c.alternativeDescription = :alternativeDescription")})
public class CrimeType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "ALTERNATIVE_DESCRIPTION")
    private String alternativeDescription;
    @OneToMany(mappedBy = "crimeType")
    private Collection<Case> caseCollection;

    public CrimeType() {
    }

    public CrimeType(String id) {
        this.id = id;
    }

    public CrimeType(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlternativeDescription() {
        return alternativeDescription;
    }

    public void setAlternativeDescription(String alternativeDescription) {
        this.alternativeDescription = alternativeDescription;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CrimeType)) {
            return false;
        }
        CrimeType other = (CrimeType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.iessonferrer.crimes.model.entities.CrimeType[ id=" + id + " ]";
    }
    
}

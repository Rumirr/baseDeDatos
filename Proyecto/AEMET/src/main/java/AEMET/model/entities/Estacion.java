/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author horabaixa
 */
@Entity
@Table(name = "ESTACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estacion.findAll", query = "SELECT e FROM Estacion e"),
    @NamedQuery(name = "Estacion.findByIdEstacion", query = "SELECT e FROM Estacion e WHERE e.idEstacion = :idEstacion"),
    @NamedQuery(name = "Estacion.findByNombre", query = "SELECT e FROM Estacion e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estacion.findByAltitud", query = "SELECT e FROM Estacion e WHERE e.altitud = :altitud"),
    @NamedQuery(name = "Estacion.findByLatitud", query = "SELECT e FROM Estacion e WHERE e.latitud = :latitud"),
    @NamedQuery(name = "Estacion.findByLongitud", query = "SELECT e FROM Estacion e WHERE e.longitud = :longitud")})
public class Estacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "ID_ESTACION")
    private String idEstacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ALTITUD")
    private short altitud;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "LATITUD")
    private BigDecimal latitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LONGITUD")
    private BigDecimal longitud;
    @JoinColumn(name = "PROVINCIA", referencedColumnName = "ID_PROVINCIA")
    @ManyToOne(optional = false)
    private Provincia provincia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estacion")
    private Collection<ObservacionTiempo> prediccionTiempoCollection;

    public Estacion() {
    }

    public Estacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    public Estacion(String idEstacion, String nombre, short altitud, BigDecimal latitud, BigDecimal longitud) {
        this.idEstacion = idEstacion;
        this.nombre = nombre;
        this.altitud = altitud;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(String idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getAltitud() {
        return altitud;
    }

    public void setAltitud(short altitud) {
        this.altitud = altitud;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ObservacionTiempo> getPrediccionTiempoCollection() {
        return prediccionTiempoCollection;
    }

    public void setPrediccionTiempoCollection(Collection<ObservacionTiempo> prediccionTiempoCollection) {
        this.prediccionTiempoCollection = prediccionTiempoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstacion != null ? idEstacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacion)) {
            return false;
        }
        Estacion other = (Estacion) object;
        if ((this.idEstacion == null && other.idEstacion != null) || (this.idEstacion != null && !this.idEstacion.equals(other.idEstacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AEMET.model.entities.Estacion[ idEstacion=" + idEstacion + " ]";
    }
    
}

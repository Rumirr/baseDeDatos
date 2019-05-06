/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AEMET.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author horabaixa
 */
@Entity
@Table(name = "OBSERVACION_TIEMPO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObservacionTiempo.findAll", query = "SELECT p FROM ObservacionTiempo p"),
    @NamedQuery(name = "ObservacionTiempo.findById", query = "SELECT p FROM ObservacionTiempo p WHERE p.observacionTiempoPK = :id"),
    @NamedQuery(name = "ObservacionTiempo.findByIdEstacion", query = "SELECT p FROM ObservacionTiempo p WHERE p.observacionTiempoPK = :idEstacion"),
    @NamedQuery(name = "ObservacionTiempo.findByFecha", query = "SELECT p FROM ObservacionTiempo p WHERE p.observacionTiempoPK = :fecha"),
    @NamedQuery(name = "ObservacionTiempo.findByUbicacion", query = "SELECT p FROM ObservacionTiempo p WHERE p.ubicacion = :ubicacion"),
    @NamedQuery(name = "ObservacionTiempo.findByPrecipitacion", query = "SELECT p FROM ObservacionTiempo p WHERE p.precipitacion = :precipitacion"),
    @NamedQuery(name = "ObservacionTiempo.findByVientoVeloMax", query = "SELECT p FROM ObservacionTiempo p WHERE p.vientoVeloMax = :vientoVeloMax"),
    @NamedQuery(name = "ObservacionTiempo.findByVientoVeloMedia", query = "SELECT p FROM ObservacionTiempo p WHERE p.vientoVeloMedia = :vientoVeloMedia"),
    @NamedQuery(name = "ObservacionTiempo.findByVientoDirecMedia", query = "SELECT p FROM ObservacionTiempo p WHERE p.vientoDirecMedia = :vientoDirecMedia"),
    @NamedQuery(name = "ObservacionTiempo.findByHumedad", query = "SELECT p FROM ObservacionTiempo p WHERE p.humedad = :humedad"),
    @NamedQuery(name = "ObservacionTiempo.findByInsolacion", query = "SELECT p FROM ObservacionTiempo p WHERE p.insolacion = :insolacion"),
    @NamedQuery(name = "ObservacionTiempo.findByPresionAire", query = "SELECT p FROM ObservacionTiempo p WHERE p.presionAire = :presionAire"),
    @NamedQuery(name = "ObservacionTiempo.findByTemp", query = "SELECT p FROM ObservacionTiempo p WHERE p.temp = :temp"),
    @NamedQuery(name = "ObservacionTiempo.findByTempMin", query = "SELECT p FROM ObservacionTiempo p WHERE p.tempMin = :tempMin"),
    @NamedQuery(name = "ObservacionTiempo.findByTempMax", query = "SELECT p FROM ObservacionTiempo p WHERE p.tempMax = :tempMax"),
    @NamedQuery(name = "ObservacionTiempo.findByVisibilidad", query = "SELECT p FROM ObservacionTiempo p WHERE p.visibilidad = :visibilidad"),
    @NamedQuery(name = "ObservacionTiempo.findByEspesorNieve", query = "SELECT p FROM ObservacionTiempo p WHERE p.espesorNieve = :espesorNieve")})
public class ObservacionTiempo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ObservacionTiempoPK observacionTiempoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UBICACION")
    private String ubicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIPITACION")
    private BigDecimal precipitacion;
    @Column(name = "VIENTO_VELO_MAX")
    private BigDecimal vientoVeloMax;
    @Column(name = "VIENTO_VELO_MEDIA")
    private BigDecimal vientoVeloMedia;
    @Column(name = "VIENTO_DIREC_MEDIA")
    private BigDecimal vientoDirecMedia;
    @Column(name = "HUMEDAD")
    private BigDecimal humedad;
    @Column(name = "INSOLACION")
    private BigDecimal insolacion;
    @Column(name = "PRESION_AIRE")
    private BigDecimal presionAire;
    @Column(name = "TEMP")
    private BigDecimal temp;
    @Column(name = "TEMP_MIN")
    private BigDecimal tempMin;
    @Column(name = "TEMP_MAX")
    private BigDecimal tempMax;
    @Column(name = "VISIBILIDAD")
    private BigDecimal visibilidad;
    @Column(name = "ESPESOR_NIEVE")
    private BigDecimal espesorNieve;
    @JoinColumn(name = "ID_ESTACION", referencedColumnName = "ID_ESTACION", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Estacion estacion;

    public ObservacionTiempo() {
    }

    public ObservacionTiempo(ObservacionTiempoPK observacionnTiempoPK) {
        this.observacionTiempoPK = observacionnTiempoPK;
    }

    public ObservacionTiempo(ObservacionTiempoPK observacionnTiempoPK, String ubicacion) {
        this.observacionTiempoPK = observacionnTiempoPK;
        this.ubicacion = ubicacion;
    }

    public ObservacionTiempo(String idEstacion, Date fecha) {
        this.observacionTiempoPK = new ObservacionTiempoPK(idEstacion, fecha);
    }

    public ObservacionTiempoPK getObservacionTiempoPK() {
        return observacionTiempoPK;
    }

    public void setObservacionTiempoPK(ObservacionTiempoPK observacionTiempoPK) {
        this.observacionTiempoPK = observacionTiempoPK;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public BigDecimal getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(BigDecimal precipitacion) {
        this.precipitacion = precipitacion;
    }

    public BigDecimal getVientoVeloMax() {
        return vientoVeloMax;
    }

    public void setVientoVeloMax(BigDecimal vientoVeloMax) {
        this.vientoVeloMax = vientoVeloMax;
    }

    public BigDecimal getVientoVeloMedia() {
        return vientoVeloMedia;
    }

    public void setVientoVeloMedia(BigDecimal vientoVeloMedia) {
        this.vientoVeloMedia = vientoVeloMedia;
    }

    public BigDecimal getVientoDirecMedia() {
        return vientoDirecMedia;
    }

    public void setVientoDirecMedia(BigDecimal vientoDirecMedia) {
        this.vientoDirecMedia = vientoDirecMedia;
    }

    public BigDecimal getHumedad() {
        return humedad;
    }

    public void setHumedad(BigDecimal humedad) {
        this.humedad = humedad;
    }

    public BigDecimal getInsolacion() {
        return insolacion;
    }

    public void setInsolacion(BigDecimal insolacion) {
        this.insolacion = insolacion;
    }

    public BigDecimal getPresionAire() {
        return presionAire;
    }

    public void setPresionAire(BigDecimal presionAire) {
        this.presionAire = presionAire;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getTempMin() {
        return tempMin;
    }

    public void setTempMin(BigDecimal tempMin) {
        this.tempMin = tempMin;
    }

    public BigDecimal getTempMax() {
        return tempMax;
    }

    public void setTempMax(BigDecimal tempMax) {
        this.tempMax = tempMax;
    }

    public BigDecimal getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(BigDecimal visibilidad) {
        this.visibilidad = visibilidad;
    }

    public BigDecimal getEspesorNieve() {
        return espesorNieve;
    }

    public void setEspesorNieve(BigDecimal espesorNieve) {
        this.espesorNieve = espesorNieve;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observacionTiempoPK != null ? observacionTiempoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionTiempo)) {
            return false;
        }
        ObservacionTiempo other = (ObservacionTiempo) object;
        if ((this.observacionTiempoPK == null && other.observacionTiempoPK != null) || (this.observacionTiempoPK != null && !this.observacionTiempoPK.equals(other.observacionTiempoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AEMET.model.entities.ObservacionTiempo[ ObservacionTiempoPK=" + observacionTiempoPK + " ]";
    }
    
}

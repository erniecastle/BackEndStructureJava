/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author daniel
 */
@Entity
public class Despensa implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.DATE)
    @Column(unique = true)
    private Date vigencia;
    private Integer retencionISR;
    private Integer periodicidadPago;  
    private Integer diasMes;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<BaseNomina> basesNomina;
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Incidencias> incidencias;       
    private Integer pagoDias;
    private Float importeDias;
    private Integer pagoHoras;
    private Float importeHoras;
    @Column(length = 255)
    private String condicionesPagos;
    
    public List<BaseNomina> getBasesNomina() {
        return basesNomina;
    }

    public void setBasesNomina(List<BaseNomina> basesNomina) {
        this.basesNomina = basesNomina;
    }

    public List<Incidencias> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencias> incidencias) {
        this.incidencias = incidencias;
    }

    public Float getImporteDias() {
        return importeDias;
    }

    public void setImporteDias(Float importeDias) {
        this.importeDias = importeDias;
    }

    public Float getImporteHoras() {
        return importeHoras;
    }

    public void setImporteHoras(Float importeHoras) {
        this.importeHoras = importeHoras;
    }

    public Integer getPagoDias() {
        return pagoDias;
    }

    public void setPagoDias(Integer pagoDias) {
        this.pagoDias = pagoDias;
    }

    public Integer getPagoHoras() {
        return pagoHoras;
    }

    public void setPagoHoras(Integer pagoHoras) {
        this.pagoHoras = pagoHoras;
    }

    public String getCondicionesPagos() {
        return condicionesPagos;
    }

    public void setCondicionesPagos(String condicionesPagos) {
        this.condicionesPagos = condicionesPagos;
    }

    public Integer getDiasMes() {
        return diasMes;
    }

    public void setDiasMes(Integer diasMes) {
        this.diasMes = diasMes;
    }

    public Integer getPeriodicidadPago() {
        return periodicidadPago;
    }

    public void setPeriodicidadPago(Integer periodicidadPago) {
        this.periodicidadPago = periodicidadPago;
    }

    public Integer getRetencionISR() {
        return retencionISR;
    }

    public void setRetencionISR(Integer retencionISR) {
        this.retencionISR = retencionISR;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}

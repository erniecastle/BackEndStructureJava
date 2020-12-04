/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 14/06/2011 Compañía:
 * Exito Software. Descripción del programa: Entidad de tipos de nómina con
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:19-10-2011 Descripción:Se agrego la
 * propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class TipoNomina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)//JSA01 
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @ManyToOne
    private Periodicidad periodicidad;
    private String folio;
    private String serie;
    private String detalleConceptoRecibo;
    @ManyToOne
    private RegistroPatronal registroPatronal;
//    private boolean asimiladosAsalarios;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the periodicidad
     */
    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    /**
     * @param periodicidad the periodicidad to set
     */
    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getDetalleConceptoRecibo() {
        return detalleConceptoRecibo;
    }

    public void setDetalleConceptoRecibo(String detalleConceptoRecibo) {
        this.detalleConceptoRecibo = detalleConceptoRecibo;
    }
    
//
//    public boolean isAsimiladosAsalarios() {
//        return asimiladosAsalarios;
//    }
//
//    public void setAsimiladosAsalarios(boolean asimiladosAsalarios) {
//        this.asimiladosAsalarios = asimiladosAsalarios;
//    }

    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

}

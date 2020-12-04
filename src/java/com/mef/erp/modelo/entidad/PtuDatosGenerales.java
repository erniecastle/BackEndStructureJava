/**
 * @author: Jose Ernesto Valenzuela Castillo Fecha de Creación: 02/06/2016
 * Compañía: Exito Software. Descripción del programa: Entidad de
 * PtuDatosGenerales para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ernesto
 */
@Entity
public class PtuDatosGenerales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @JoinColumn(nullable = false)
    private Integer ejercicio;
    private Double ptuArepartir;
    @Temporal(TemporalType.DATE)
    private Date fechaCalculo;
    private Double topeSalario;
    private Double totalDias;
    private Double totalPercepciones;
    private Double totalDiasptu;
    private Double totalPercepcionesptu;
    private String status;
    private Boolean nuevosCalculos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Double getPtuArepartir() {
        return ptuArepartir;
    }

    public void setPtuArepartir(Double ptuArepartir) {
        this.ptuArepartir = ptuArepartir;
    }

    public Date getFechaCalculo() {
        return fechaCalculo;
    }

    public void setFechaCalculo(Date fechaCalculo) {
        this.fechaCalculo = fechaCalculo;
    }

    public Double getTopeSalario() {
        return topeSalario;
    }

    public void setTopeSalario(Double topeSalario) {
        this.topeSalario = topeSalario;
    }

    public Double getTotalDias() {
        return totalDias;
    }

    public void setTotalDias(Double totalDias) {
        this.totalDias = totalDias;
    }

    public Double getTotalPercepciones() {
        return totalPercepciones;
    }

    public void setTotalPercepciones(Double totalPercepciones) {
        this.totalPercepciones = totalPercepciones;
    }

    public Double getTotalDiasptu() {
        return totalDiasptu;
    }

    public void setTotalDiasptu(Double totalDiasptu) {
        this.totalDiasptu = totalDiasptu;
    }

    public Double getTotalPercepcionesptu() {
        return totalPercepcionesptu;
    }

    public void setTotalPercepcionesptu(Double totalPercepcionesptu) {
        this.totalPercepcionesptu = totalPercepcionesptu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean isNuevosCalculos() {
        return nuevosCalculos;
    }

    public void setNuevosCalculos(Boolean nuevosCalculos) {
        this.nuevosCalculos = nuevosCalculos;
    }

}

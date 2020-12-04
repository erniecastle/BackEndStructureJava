/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ConfigFoliacion",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tabla"})})
public class ConfigFoliacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 50, nullable = false)
    private String tabla;
    @Column(length = 255, nullable = true)
    private String campoClave;
    private Boolean activarFoliacion;
    private Integer lgnFolio;
    private Boolean activarPrefijo;
    private Integer lngPrefijo;
    private String prefijosValidos;
    private Boolean saltarFolios;
    private Boolean mostrarMsjFolioAjustado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCampoClave() {
        return campoClave;
    }

    public void setCampoClave(String campoClave) {
        this.campoClave = campoClave;
    }

    public Boolean getActivarFoliacion() {
        return activarFoliacion;
    }

    public void setActivarFoliacion(Boolean activarFoliacion) {
        this.activarFoliacion = activarFoliacion;
    }

    public Integer getLgnFolio() {
        return lgnFolio;
    }

    public void setLgnFolio(Integer lgnFolio) {
        this.lgnFolio = lgnFolio;
    }

    public Boolean getActivarPrefijo() {
        return activarPrefijo;
    }

    public void setActivarPrefijo(Boolean activarPrefijo) {
        this.activarPrefijo = activarPrefijo;
    }

    public Integer getLngPrefijo() {
        return lngPrefijo;
    }

    public void setLngPrefijo(Integer lngPrefijo) {
        this.lngPrefijo = lngPrefijo;
    }

    public String getPrefijosValidos() {
        return prefijosValidos;
    }

    public void setPrefijosValidos(String prefijosValidos) {
        this.prefijosValidos = prefijosValidos;
    }

    public Boolean getSaltarFolios() {
        return saltarFolios;
    }

    public void setSaltarFolios(Boolean saltarFolios) {
        this.saltarFolios = saltarFolios;
    }

    public Boolean getMostrarMsjFolioAjustado() {
        return mostrarMsjFolioAjustado;
    }

    public void setMostrarMsjFolioAjustado(Boolean mostrarMsjFolioAjustado) {
        this.mostrarMsjFolioAjustado = mostrarMsjFolioAjustado;
    }

}

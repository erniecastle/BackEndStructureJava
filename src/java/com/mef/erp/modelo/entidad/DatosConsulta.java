/**
 * @author: Victor Lopez Fecha de Creación: 13/09/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de DIM con Hibernate
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DatosConsulta")
public class DatosConsulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombreMostrar;
    private String nombreBD;
    @Transient
    private int columna;
    private String tipoDato;
    private String nombreEtiqueta;
    private String formato;
    private String camposCombinar;
    @Transient
    private CamposNodoConsulta camposNodoConsulta;

    public DatosConsulta() {
    }

    public DatosConsulta(String nombreMostrar, String nombreBD, int columna) {
        this.nombreMostrar = nombreMostrar;
        this.nombreBD = nombreBD;
        this.columna = columna;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public void setNombreMostrar(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public void setNombreBD(String nombreBD) {
        this.nombreBD = nombreBD;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getCamposCombinar() {
        return camposCombinar;
    }

    public void setCamposCombinar(String camposCombinar) {
        this.camposCombinar = camposCombinar;
    }

    public CamposNodoConsulta getCamposNodoConsulta() {
        return camposNodoConsulta;
    }

    public void setCamposNodoConsulta(CamposNodoConsulta camposNodoConsulta) {
        this.camposNodoConsulta = camposNodoConsulta;
    }
}

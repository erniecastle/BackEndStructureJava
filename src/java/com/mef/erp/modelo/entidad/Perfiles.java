/**
 * @author: Victor Lopez
 * Fecha de Creación: 15/06/2011
 * Compañía: MacroPro.
 * Descripción del programa: Entidad de Perfiles para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: 
 * Autor: 
 * Fecha: 
 * Descripción: 
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Perfiles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(nullable = false)
    private String nombre;
    private String skin;
    private Boolean reporte;
    private Byte nivelAccesoSistema;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getReporte() {
        return reporte;
    }

    public void setReporte(Boolean reporte) {
        this.reporte = reporte;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public Byte getNivelAccesoSistema() {
        return nivelAccesoSistema;
    }

    public void setNivelAccesoSistema(Byte nivelAccesoSistema) {
        this.nivelAccesoSistema = nivelAccesoSistema;
    }
}

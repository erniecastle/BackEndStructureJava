/**
 * @author: Armando Fecha de Creación: 20/09/2011 Compañía: MacroPro.
 * Descripción del programa: Se creo esta entidad para relacionar los usuarios
 * con las razones sociales que existen en las MEF's existentes.
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

@Entity
public class RazonSocial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false)
    private String claveRazonSocial;
    @Column(length = 255, nullable = false)
    private String nombreRazonSocial;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClaveRazonSocial() {
        return claveRazonSocial;
    }

    public void setClaveRazonSocial(String claveRazonSocial) {
        this.claveRazonSocial = claveRazonSocial;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }
}

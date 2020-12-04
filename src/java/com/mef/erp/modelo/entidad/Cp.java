/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de Codigos postales para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String, se agregaron tamaños de columnas
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Column;

@Entity
public class Cp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 30, nullable = false)//AAP01
    private String descripcion;
    @ManyToOne
    private Ciudades ciudades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {//AAP01
        return clave;
    }

    public void setClave(String clave) {//AAP01
        this.clave = clave;
    }

    public String  getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String  descripcion) {
        this.descripcion = descripcion;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }
}

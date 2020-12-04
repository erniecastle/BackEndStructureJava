/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 01/041/2011
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de TipoHerramienta
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JEVC01
 * Autor:Ernesto Castillo
 * Fecha:24/04/2012
 * Descripción: Se le quito el ID Autogenerable
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
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class TipoHerramienta implements Serializable {

    @Id
    private Integer id;
    @Column(length = 255, nullable = true)
    private String nombre;

    public TipoHerramienta() {
    }

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
}

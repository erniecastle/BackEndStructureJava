/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 21/05/2011
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de elementos de aplicacion para Hibernate
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
import javax.persistence.Id;

@Entity
public class ElementosAplicacion implements Serializable {

    @Id
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String nombre;
    private Class entidad;
    private Long parentId;
    private Long ordenId;

    public ElementosAplicacion() {
        super();
    }

    public ElementosAplicacion(Long id, String nombre, Long parentId, Long orderId, Class entidad) {
        this.id = id;
        this.nombre = nombre;
        this.parentId = parentId;
        this.ordenId = orderId;
        this.entidad = entidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Long ordenId) {
        this.ordenId = ordenId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Class getEntidad() {
        return entidad;
    }

    public void setEntidad(Class entidad) {
        this.entidad = entidad;
    }
}

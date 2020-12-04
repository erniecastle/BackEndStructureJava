/**
 * @author: Jose Armando 
 * Fecha de Creación: 13/06/2012
 * Compañía: Macropro
 * Descripción del programa: Entidad de Tipo de contrato que agrego para añadir los posibles contratos.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
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
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TipoContrato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    private Boolean esSindicalizado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEsSindicalizado() {
        return esSindicalizado;
    }

    public void setEsSindicalizado(Boolean esSindicalizado) {
        this.esSindicalizado = esSindicalizado;
    }
    
}

/**
 * @author: Abraham Daniel Arjona Peraza
 * Fecha de Creación: 16/06/2011
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de Periodicidad para tipos de nomina con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
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
public class Periodicidad implements Serializable{
    
    @Id    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (length=30, nullable=false, unique = true)//JSA01
    private String clave;
    @Column (length=255, nullable=false) 
    private String descripcion;
    @Column (nullable=false) 
    private Long dias;

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
     * @return the dias
     */
    public Long getDias() {
        return dias;
    }

    /**
     * @param dias the dias to set
     */
    public void setDias(Long dias) {
        this.dias = dias;
    }
}

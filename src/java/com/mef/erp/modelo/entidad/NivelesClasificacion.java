/**
 * @author: Daniel
 * Fecha de Creación: 21/05/2011
 * Compañía: Finesoft.
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author daniel
 */
@Entity
public class NivelesClasificacion implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)//JSA01)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;

    public NivelesClasificacion(){
        
    }

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
}

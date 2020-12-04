/**
 * @author: Ernesto Castillo
 * Fecha de Creación: 15/03/2011
 * Compañía: Exito Software.
 * Descripción del programa: Entidad de Estados para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01
 * Autor: Abraham Daniel Arjona Peraza
 * Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String, se agregaron tamaños de columnas
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Estados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)//JSA01//AAP01
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @ManyToOne
    private Paises paises;

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
   
    public String getDescripcion() {
        return descripcion;
    }
   
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public Paises getPaises() {
        return paises;
    }
    
    public void setPaises(Paises paises) {
        this.paises = paises;
    }
}

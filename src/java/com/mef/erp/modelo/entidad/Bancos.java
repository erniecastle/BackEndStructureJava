/**
 * @author: Daniel
 * Fecha de Creación: 21/05/2011
 * Compañía: Finesoft
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 * Clave:JSA02
 * Autor:Jose Armando   
 * Fecha:09/05/2013
 * Descripción:Se quito la propiedad de null
 * -----------------------------------------------------------------------------
 */ 

package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
/**
 *
 * @author daniel
 */
@Entity
public class Bancos implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, unique = true)//JSA01
    private String clave;
    @Column(length = 255)//JSA02
    private String descripcion;
    @Column(length = 15)//JSA02
    private String RFC;
    @Column(length = 255)//JSA02
    private String domicilio;
    @Column(length = 255)//JSA02
    private String paginaweb;
    @Column(length = 255)//JSA02
    private String notas;

    public Bancos() {
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

    public String getRFC() {
        return RFC;
    }
    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPaginaweb() {
        return paginaweb;
    }
    public void setPaginaweb(String paginaweb) {
        this.paginaweb = paginaweb;
    }

    public String getNotas() {
        return notas;
    }
    public void setNotas(String notas) {
        this.notas = notas;
    }
}

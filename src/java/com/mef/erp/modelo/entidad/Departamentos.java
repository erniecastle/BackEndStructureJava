/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 21/06/2011 Compañía:
 * Exito Software. Descripción del programa: Entidad de Departamentos para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 26/07/2011
 * Descripción: Se cambio clave y subcuenta a String y se agregó variable
 * version
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 02-12-2011
 * Descripción: Se agrego el campo de Razon social ya que esta informacion es
 * por empresa.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Departamentos",
uniqueConstraints = {
    @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})//JSA01
public class Departamentos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false)
    private String clave;//AAP01
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 30, nullable = false)
    private String subCuenta;//AAP01
////    @Version
////    private int version;//AAP01
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JSA01

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
    public String getClave() {//AAP01
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {//AAP01
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
     * @return the subCuenta
     */
    public String getSubCuenta() {//AAP01
        return subCuenta;
    }

    /**
     * @param subCuenta the subCuenta to set
     */
    public void setSubCuenta(String subCuenta) {//AAP01
        this.subCuenta = subCuenta;
    }

////    /**
////     * @return the version
////     */
////    public int getVersion() {//AAP01
////        return version;
////    }
////
////    /**
////     * @param version the version to set
////     */
////    public void setVersion(int version) {//AAP01
////        this.version = version;
////    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }
}

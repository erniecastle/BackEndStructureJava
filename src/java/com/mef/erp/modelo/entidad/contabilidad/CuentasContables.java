/**
 * @author: Ernesto Castillo Fecha de Creación: 23/11/2015 Compañía: Exito
 * Software. Descripción del programa: Entidad de CuentasContables para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad.contabilidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class CuentasContables implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255)
    private String descripcion;
    @Column(length = 255)
    private String descripcionBreve;
//    @ManyToOne
//    private FormatosCnxContaDet formatosCnxContaDet;
    @Column(length = 100)
    private String cuentaContable;
    @Column(length = 20)
    private Integer agregarSubcuentasPor;

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

    public String getDescripcionBreve() {
        return descripcionBreve;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        this.descripcionBreve = descripcionBreve;
    }

    public Integer getAgregarSubcuentasPor() {
        return agregarSubcuentasPor;
    }

    public void setAgregarSubcuentasPor(Integer agregarSubcuentasPor) {
        this.agregarSubcuentasPor = agregarSubcuentasPor;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

}

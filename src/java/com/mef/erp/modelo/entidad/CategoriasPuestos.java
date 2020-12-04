/**
 * @author: Daniel
 * Fecha de Creación: --/--/--
 * Compañía: FineSoft
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:05/06/2012
 * Descripción:Se agregaron los campos horas y tabladatos.
 * -----------------------------------------------------------------------------
 * Clave:JSA02
 * Autor:Jose Armando Sanchez Acosta
 * Fecha:09/07/2012
 * Descripción:Se modifico el campo horas por Pagar por horas ya que este sirve para indicar si se pagan por horas o no
 * y va depender del parametro claveParametroPagosPorHora para saber si se muestra o no.
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
public class CategoriasPuestos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    private Boolean estado;
    private Boolean pagarPorHoras;//JSA02
    private TablaBase tablaBase;//JSA01

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
     * @return the estado
     */
    public Boolean getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getPagarPorHoras() {
        return pagarPorHoras;
    }

    public void setPagarPorHoras(Boolean pagarPorHoras) {
        this.pagarPorHoras = pagarPorHoras;
    }

    public TablaBase getTablaBase() {
        return tablaBase;
    }

    public void setTablaBase(TablaBase tablaBase) {
        this.tablaBase = tablaBase;
    }
    
}

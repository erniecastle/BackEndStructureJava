/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 07-11-2014 Descripción: se agrego el campo
 * imagen para el tipo de configuracion 7 que es el del logo.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Ernesto Castillo
 */
@Entity
@Table(name = "Cruce",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"id", "parametros", "elementosaplicacion"})})
public class Cruce implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id", nullable = false, insertable = true)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "parametros", nullable = false, insertable = true)
    private Parametros parametros;
    @ManyToOne
    @JoinColumn(name = "elementosaplicacion", nullable = false, insertable = true)
    private ElementosAplicacion elementosaplicacion;
    @Column(length = 255, nullable = false)
    private String claveElemento;
    @Column(length = 255, nullable = false)
    private String valor;
    @Column(length = 255, nullable = false)
    private Integer ordenId;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;//JSA01

    public Cruce() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Parametros getParametros() {
        return parametros;
    }

    public void setParametros(Parametros parametros) {
        this.parametros = parametros;
    }

    public ElementosAplicacion getElementosaplicacion() {
        return elementosaplicacion;
    }

    public void setElementosaplicacion(ElementosAplicacion elementosaplicacion) {
        this.elementosaplicacion = elementosaplicacion;
    }

    public String getClaveElemento() {
        return claveElemento;
    }

    public void setClaveElemento(String claveElemento) {
        this.claveElemento = claveElemento;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}

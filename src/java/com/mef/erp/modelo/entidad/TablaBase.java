/**
 * @author: Victor Lopez Fecha de Creación: 15/05/2011 Compañía: MacroPro.
 * Descripción del programa: Entidad de TablaBase para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class TablaBase implements Serializable {

    public TablaBase() {
    }

    public TablaBase(String clave, String nombreTipoTabla, String controladores) {
        tipoTabla = new TipoTabla();
        tipoTabla.setNombre(nombreTipoTabla);
        this.clave = clave;
        this.controladores = controladores;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false, unique = true)
    private String clave;
    @ManyToOne
    private TipoTabla tipoTabla;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 255, nullable = false)
    private String descripcionAbreviada;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileXml;
    private Boolean renglonSeleccionado;
    private String controladores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDescripcionAbreviada() {
        return descripcionAbreviada;
    }

    public void setDescripcionAbreviada(String descripcionAbreviada) {
        this.descripcionAbreviada = descripcionAbreviada;
    }

    public byte[] getFileXml() {
        return fileXml;
    }

    public void setFileXml(byte[] fileXml) {
        this.fileXml = fileXml;
    }

    public TipoTabla getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(TipoTabla tipoTabla) {
        this.tipoTabla = tipoTabla;
    }

    public Boolean isRenglonSeleccionado() {
        return renglonSeleccionado;
    }

    public void setRenglonSeleccionado(Boolean renglonSeleccionado) {
        this.renglonSeleccionado = renglonSeleccionado;
    }

    public String getControladores() {
        return controladores;
    }

    public void setControladores(String controladores) {
        this.controladores = controladores;
    }

}

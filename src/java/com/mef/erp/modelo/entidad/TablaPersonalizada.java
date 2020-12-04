/**
 * @author: Victor Lopez Fecha de Creación: 15/07/2011 Compañía: MacroPro.
 * Descripción del programa: Entidad de TablaPersonalizada para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 09/05/2013 Descripción: Se agrego una
 * relacion con tipoTabla
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TablaPersonalizada implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 30, nullable = false)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(length = 255, nullable = false)
    private String descripcionAbreviada;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileXml;
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
    private TablaBase tablaBase;
    @ManyToOne
    private TipoTabla tipoTabla;//JSA01
    private Boolean renglonSeleccionado;

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

    public TablaBase getTablaGenerada() {
        return tablaBase;
    }

    public void setTablaGenerada(TablaBase tablaBase) {
        this.tablaBase = tablaBase;
    }

    public Boolean isRenglonSeleccionado() {
        return renglonSeleccionado;
    }

    public void setRenglonSeleccionado(Boolean renglonSeleccionado) {
        this.renglonSeleccionado = renglonSeleccionado;
    }

    public TipoTabla getTipoTabla() {
        return tipoTabla;
    }

    public void setTipoTabla(TipoTabla tipoTabla) {
        this.tipoTabla = tipoTabla;
    }
}

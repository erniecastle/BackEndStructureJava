/**
 * @author: Victor Lopez Fecha de Creación: 15/06/2011 Compañía: MacroPro.
 * Descripción del programa: Entidad de TablaDatos para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 09/05/2013 Descripción: Se quito el
 * campo de clave y descripcion.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TablaDatos",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"tablaBase_id", "tablaPersonalizada_id", "controlPorFecha", "controlPorAnio", "controladores"})})
public class TablaDatos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descripcion;
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
    @JoinColumn(name = "tablaPersonalizada_id", nullable = true, insertable = true)
    private TablaPersonalizada tablaPersonalizada;
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
    @JoinColumn(name = "tablaBase_id", nullable = true, insertable = true)
    private TablaBase tablaBase;
//    @Column(length = 255, nullable = false)//JSA01
//    private String descripcion;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] fileXml;
    private Boolean renglonSeleccionado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date controlPorFecha;
    private Integer controlPorAnio;
    private String controladores;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFileXml() {
        return fileXml;
    }

    public void setFileXml(byte[] fileXml) {
        this.fileXml = fileXml;
    }

    public TablaPersonalizada getTablaPersonalizada() {
        return tablaPersonalizada;
    }

    public void setTablaPersonalizada(TablaPersonalizada tablaPersonalizada) {
        this.tablaPersonalizada = tablaPersonalizada;
    }

    public TablaBase getTablaBase() {
        return tablaBase;
    }

    public void setTablaBase(TablaBase tablaBase) {
        this.tablaBase = tablaBase;
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

    public Integer getControlPorAnio() {
        return controlPorAnio;
    }

    public void setControlPorAnio(Integer controlPorAnio) {
        this.controlPorAnio = controlPorAnio;
    }

    public Date getControlPorFecha() {
        return controlPorFecha;
    }

    public void setControlPorFecha(Date controlPorFecha) {
        this.controlPorFecha = controlPorFecha;
    }
}

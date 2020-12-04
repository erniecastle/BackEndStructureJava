/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 18-05-2012 Descripción: se agregaron los
 * parametros de los conceptos, tambien se le hicieron cambios a los parametros
 * ya que se les añadio el campo de opcionesParametros
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Armando Fecha: 07-11-2014 Descripción: se agrego el campo
 * imagen para el tipo de configuracion 7 que es el del logo.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Parametros implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private Long clave;
    @ManyToOne
    private Modulo modulo;
    @Column(length = 255, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String valor;
    @Column(length = 350, nullable = true)//JSA01
    private String opcionesParametros;
    @Column(nullable = false)
    private Clasificacion clasificacion;
    @Column(nullable = false)
    private Integer tipoConfiguracion;//JEVC
    @Column(length = 350, nullable = false)
    private String propiedadConfig;//JEVC
    private Integer ordenId;
    @ManyToMany(cascade = {CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ElementosAplicacion> elementosAplicacion;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] imagen;//JSA02

    public Parametros() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClave() {
        return clave;
    }

    public void setClave(Long clave) {
        this.clave = clave;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getOpcionesParametros() {
        return opcionesParametros;
    }

    public void setOpcionesParametros(String opcionesParametros) {
        this.opcionesParametros = opcionesParametros;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Integer getTipoConfiguracion() {
        return tipoConfiguracion;
    }

    public void setTipoConfiguracion(Integer tipoConfiguracion) {
        this.tipoConfiguracion = tipoConfiguracion;
    }

    public String getPropiedadConfig() {
        return propiedadConfig;
    }

    public void setPropiedadConfig(String propiedadConfig) {
        this.propiedadConfig = propiedadConfig;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public List<ElementosAplicacion> getElementosAplicacion() {
        return elementosAplicacion;
    }

    public void setElementosAplicacion(List<ElementosAplicacion> elementosAplicacion) {
        this.elementosAplicacion = elementosAplicacion;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

}

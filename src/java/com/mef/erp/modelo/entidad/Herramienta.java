/**
 * @author: 
 * Fecha de Creación:
 * Compañía: 
 * Descripción del programa: Entidad que lleva el control de las herramientas creadas
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01
 * Autor: José Armando SAnchez Acosta
 * Fecha: 01-11-2011
 * Descripción: Se le agrego el campo de Compartir para saber si la herramienta sera para todos los
 * usuario o solo para el sistema con el fin de que al administrador no le aparescan otras opciones visibles.
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Herramienta implements Serializable {

    @Id
    private Integer id;
    @Column(length = 255, nullable = false)
    private String nombre;
    @ManyToOne
    private TipoHerramienta tipoHerramienta;
    private Boolean principal;
    private Boolean secundario;
    private Boolean habilitado;
    private Boolean visible;
    private Boolean compartir;//JSA01

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoHerramienta getTipoHerramienta() {
        return tipoHerramienta;
    }

    public void setTipoHerramienta(TipoHerramienta tipoHerramienta) {
        this.tipoHerramienta = tipoHerramienta;
    }

    /**
     * @return the principal
     */
    public Boolean isPrincipal() {
        return principal;
    }

    /**
     * @param principal the principal to set
     */
    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    /**
     * @return the secundario
     */
    public Boolean isSecundario() {
        return secundario;
    }

    /**
     * @param secundario the secundario to set
     */
    public void setSecundario(Boolean secundario) {
        this.secundario = secundario;
    }

    public Herramienta(String nombre, TipoHerramienta tipoHerramienta, Boolean principal,Boolean compartir) {
        this.nombre = nombre;
        this.tipoHerramienta = tipoHerramienta;
        this.principal = principal;
        this.compartir=compartir;//JSA01
    }

    public Herramienta() {
    }

    public Boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Herramienta(Integer id, String nombre, Boolean habilitado, Boolean visible, Boolean compartir) {
        this.id = id;
        this.nombre = nombre;
        this.habilitado = habilitado;
        this.visible = visible;
        this.compartir=compartir;//JSA01
    }

    public Herramienta(Integer id, String nombre, TipoHerramienta tipoHerramienta, Boolean habilitado, Boolean visible,Boolean compartir) {
        this.id = id;
        this.nombre = nombre;
        this.tipoHerramienta = tipoHerramienta;
        this.habilitado = habilitado;
        this.visible = visible;
        this.compartir=compartir;//JSA01
    }

    public Boolean getCompartir() {//JSA01
        return compartir;
    }

    public void setCompartir(Boolean compartir) {//JSA01
        this.compartir = compartir;
    }

}

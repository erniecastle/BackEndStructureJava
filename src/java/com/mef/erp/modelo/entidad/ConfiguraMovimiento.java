/**
 * @author: Victor Lopez Fecha de Creación: 09/12/2011 Compañía: Macropro.
 * Descripción del programa: Entidad de CardexMovimiento con Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ConfiguraMovimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @JoinColumn(name = "clave", nullable = false, insertable = true, updatable = true)
//    private String clave;
    private String nombre;
    private String filtro;
    private String movimiento;
    private String activadosFiltro;
    private String activadosMovimientos;
    private boolean movimientoExistente;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = true, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    private String contenedorPadre_ID;
    private String usuario_ID;
    private String perfiles_ID;
    private String keyCode;
    private String modifiers;
    private Integer ordenId;
    private String icono;
    private Boolean habilitado;
    private Boolean visible;
    private Boolean compartir;
    private boolean sistema;

    public String getActivadosFiltro() {
        return activadosFiltro;
    }

    public void setActivadosFiltro(String activadosFiltro) {
        this.activadosFiltro = activadosFiltro;
    }

    public String getActivadosMovimientos() {
        return activadosMovimientos;
    }

    public void setActivadosMovimientos(String activadosMovimientos) {
        this.activadosMovimientos = activadosMovimientos;
    }

//    public String getClave() {
//        return clave;
//    }
//
//    public void setClave(String clave) {
//        this.clave = clave;
//    }
    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isMovimientoExistente() {
        return movimientoExistente;
    }

    public void setMovimientoExistente(boolean movimientoExistente) {
        this.movimientoExistente = movimientoExistente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Boolean getCompartir() {
        return compartir;
    }

    public void setCompartir(Boolean compartir) {
        this.compartir = compartir;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getModifiers() {
        return modifiers;
    }

    public void setModifiers(String modifiers) {
        this.modifiers = modifiers;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getContenedorPadre_ID() {
        return contenedorPadre_ID;
    }

    public void setContenedorPadre_ID(String contenedorPadre_ID) {
        this.contenedorPadre_ID = contenedorPadre_ID;
    }

    public String getPerfiles_ID() {
        return perfiles_ID;
    }

    public void setPerfiles_ID(String perfiles_ID) {
        this.perfiles_ID = perfiles_ID;
    }

    public String getUsuario_ID() {
        return usuario_ID;
    }

    public void setUsuario_ID(String usuario_ID) {
        this.usuario_ID = usuario_ID;
    }

    public boolean isSistema() {
        return sistema;
    }

    public void setSistema(boolean sistema) {
        this.sistema = sistema;
    }

    public static ConfiguraMovimiento nuevaInstanciaConfiguraMovimiento(ConfiguraMovimiento configuraMovimiento) {
        ConfiguraMovimiento nuevaInstancia = new ConfiguraMovimiento();
        nuevaInstancia.setId(null);
        nuevaInstancia.setNombre(configuraMovimiento.getNombre());
        nuevaInstancia.setFiltro(configuraMovimiento.getFiltro());
        nuevaInstancia.setMovimiento(configuraMovimiento.getMovimiento());
        nuevaInstancia.setActivadosFiltro(configuraMovimiento.getActivadosFiltro());
        nuevaInstancia.setActivadosMovimientos(configuraMovimiento.getActivadosMovimientos());
        nuevaInstancia.setMovimientoExistente(configuraMovimiento.isMovimientoExistente());
        nuevaInstancia.setRazonesSociales(configuraMovimiento.getRazonesSociales());
        nuevaInstancia.setContenedorPadre_ID(configuraMovimiento.getContenedorPadre_ID());
        nuevaInstancia.setUsuario_ID(configuraMovimiento.getUsuario_ID());
        nuevaInstancia.setPerfiles_ID(configuraMovimiento.getPerfiles_ID());
        nuevaInstancia.setKeyCode(configuraMovimiento.getKeyCode());
        nuevaInstancia.setModifiers(configuraMovimiento.getModifiers());
        nuevaInstancia.setOrdenId(configuraMovimiento.getOrdenId());
        nuevaInstancia.setIcono(configuraMovimiento.getIcono());
        nuevaInstancia.setHabilitado(configuraMovimiento.getHabilitado());
        nuevaInstancia.setVisible(configuraMovimiento.getVisible());
        nuevaInstancia.setCompartir(configuraMovimiento.getCompartir());
        nuevaInstancia.setSistema(configuraMovimiento.isSistema());
        return nuevaInstancia;
    }

}

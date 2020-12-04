/**
 * @author: Ernesto Castillo Fecha de Creación: 30/11/2011 Compañía: Exito
 * Software. Descripción del programa: Entidad de Configuración de asistencia
 * para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor: Jose Armando Fecha:10/Nov/2012 Descripción: se agregaron
 * los campos para los permisos y contendores.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:01/07/2014 Descripción: se le agrego la
 * programacion para el filtrado del empleado, centro de costos o excepcion
 * tanto arriba como encabezado como abajo en el grid.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ConfigAsistencias",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"id", "razonesSociales"})})
public class ConfiguracionAsistencias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id", nullable = false, insertable = true, updatable = false)
    private Long id;
    @Column(length = 350, nullable = false)
    private String nombre;
    private String filtro;
    private String movimiento;
    private String activadosFiltro;
    private String activadosMovimientos;
    @Column(length = 30, nullable = false)
    private Integer ordenId;
    @ManyToOne
    @JoinColumn(name = "razonesSociales", nullable = true, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @OrderBy("orden")
    private List<Excepciones> excepciones;
    @Transient
    private boolean cambio = false;
    private String contenedorPadre_ID;//JSA01
    private String usuario_ID;//JSA01
    private String perfiles_ID;//JSA01
    private String keyCode;//JSA01
    private String modifiers;//JSA01
    private String icono;//JSA01
    private Boolean habilitado;//JSA01
    private Boolean visible;//JSA01
    private Boolean compartir;//JSA01
    private boolean sistema; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public List<Excepciones> getExcepciones() {
        return excepciones;
    }

    public void setExcepciones(List<Excepciones> excepciones) {
        this.excepciones = excepciones;
    }

    public boolean isCambio() {
        return cambio;
    }

    public void setCambio(boolean cambio) {
        this.cambio = cambio;
    }

    public Boolean getCompartir() {
        return compartir;
    }

    public void setCompartir(Boolean compartir) {
        this.compartir = compartir;
    }

    public String getContenedorPadre_ID() {
        return contenedorPadre_ID;
    }

    public void setContenedorPadre_ID(String contenedorPadre_ID) {
        this.contenedorPadre_ID = contenedorPadre_ID;
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

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

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

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public boolean isSistema() {
        return sistema;
    }

    public void setSistema(boolean sistema) {
        this.sistema = sistema;
    }

}

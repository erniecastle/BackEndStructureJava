/**
 * @author: Victor Lopez Fecha de Creación: 15/03/2011 Compañía: MacroPro.
 * Descripción del programa: Entidad de ContenedorPersonalizado para Hibernate
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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ContenedorPersonalizado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private Herramienta herramienta;
    @ManyToOne
    private Contenedor Contenedor;
    //private Integer idCotenedor;
    //private Integer idHerramienta;
    @Column(length = 255, nullable = false)
    private String nombre;
    private String keyCode;
    private String modifiers;
    @Column(length = 19, nullable = false)
    private Integer parentId;
    @Column(length = 19, nullable = false)
    private Integer ordenId;
    private String icono;
    private Boolean habilitado;
    private Boolean visible;
    @ManyToOne
    private Perfiles perfil;
    @ManyToOne
    private Usuario usuario;
    private TipoIcono tipoIcono;

    public ContenedorPersonalizado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public ContenedorPersonalizado(Integer id, String nombre, Integer parentId, Integer orderId,
            Herramienta herramienta, Boolean habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.parentId = parentId;
        this.ordenId = orderId;
        this.herramienta = herramienta;
        this.habilitado = habilitado;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Contenedor getContenedor() {
        return Contenedor;
    }

    public void setContenedor(Contenedor Contenedor) {
        this.Contenedor = Contenedor;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    public TipoIcono getTipoIcono() {
        return tipoIcono;
    }

    public void setTipoIcono(TipoIcono tipoIcono) {
        this.tipoIcono = tipoIcono;
    }

}

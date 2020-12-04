/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author Ernesto Castillo
 */
@Entity
public class Contenedor implements Serializable {

    @Id
    private Integer id;
    @ManyToOne
    private Herramienta herramienta;
    @Column(length = 255, nullable = false)
    private String nombre;
    @Column(length = 255, nullable = true)
    private String accion;
    private TipoAcciones tipoAcciones;
    private String keyCode;
    private String modifiers;
    @Column(length = 19, nullable = false)
    private Integer parentId;
    @Column(length = 19, nullable = false)
    private Integer ordenId;
    @ManyToOne
    private TipoElemento tipoElemento;
    @Column(length = 255)
    private String icono;
    private Boolean habilitado;
    private Boolean visible;
    private Boolean compartir;
    @ManyToOne
    private Ventana ventana;
    private Long idMultiUsos;
    @Transient
    private String externo;
    @ManyToOne
    private RazonSocial razonSocial;
    private TipoIcono tipoIcono;

    public Contenedor() {
    }

    public Contenedor(Integer id, Integer idHerramienta, boolean isPrincipalHerramienta, boolean isVisibleHerramienta,
            Integer idTipoHerramienta, String nombreTipoHerramienta, String nombre, String accion, TipoAcciones tipoAcciones,
            String keyCode, String modifiers, Integer parentId, Integer ordenId, TipoElemento tipoElemento, String icono,
            TipoIcono tipoIcono, Boolean habilitado, Boolean visible, Ventana ventana, Long idMultiUsos, String claveRazonSocial) {
        this.id = id;
//////////        herramienta.getCompartir();
////////////        herramienta.getId();
//////////        herramienta.getNombre();
//////////        herramienta.isHabilitado();
//////////        //herramienta.isPrincipal();
//////////        herramienta.isSecundario();
////////////        herramienta.isVisible();
//////////        herramienta.getTipoHerramienta();
////////////        herramienta.getTipoHerramienta().getId();
////////////        herramienta.getTipoHerramienta().getNombre();
        Herramienta herramienta1 = new Herramienta();
        herramienta1.setId(idHerramienta);
        herramienta1.setPrincipal(isPrincipalHerramienta);
        herramienta1.setVisible(isVisibleHerramienta);
        TipoHerramienta tipoHerramienta = new TipoHerramienta();
        tipoHerramienta.setId(idTipoHerramienta);
        tipoHerramienta.setNombre(nombreTipoHerramienta);
        herramienta1.setTipoHerramienta(tipoHerramienta);
        this.herramienta = herramienta1;
        this.nombre = nombre;
        this.accion = accion;
        this.tipoAcciones = tipoAcciones;
        this.keyCode = keyCode;
        this.modifiers = modifiers;
        this.parentId = parentId;
        this.ordenId = ordenId;
        this.tipoElemento = tipoElemento;
        this.icono = icono;
        this.tipoIcono = tipoIcono;
        this.habilitado = habilitado;
        this.visible = visible;
        this.ventana = ventana;
        this.idMultiUsos = idMultiUsos;
        if (claveRazonSocial != null) {
            RazonSocial razonSocial = new RazonSocial();
            razonSocial.setClaveRazonSocial(claveRazonSocial);
            this.razonSocial = razonSocial;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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

    public TipoElemento getTipoElemento() {
        return tipoElemento;
    }

    public void setTipoElemento(TipoElemento tipoElemento) {
        this.tipoElemento = tipoElemento;
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

    public Contenedor(Integer id, String nombre, Integer parentId, Integer orderId,
            Herramienta herramienta, TipoElemento tipoElemento, Boolean habilitado, Boolean compartir) {
        this.id = id;
        this.nombre = nombre;
        this.parentId = parentId;
        this.ordenId = orderId;
        this.herramienta = herramienta;
        this.tipoElemento = tipoElemento;
        this.habilitado = habilitado;
        this.compartir = compartir;
    }

    public Contenedor(Integer id, String nombre, Integer parentId, Integer orderId,
            Herramienta herramienta, TipoElemento tipoElemento, Boolean habilitado, Boolean compartir,
            String accion, String modifiers, String keyCode, String icono,TipoIcono tipoIcono, Boolean visible, TipoAcciones tipoAcciones, Ventana ventanas, Long idMultiUso) {
        this.id = id;
        this.nombre = nombre;
        this.parentId = parentId;
        this.ordenId = orderId;
        this.accion = accion;
        this.modifiers = modifiers;
        this.keyCode = keyCode;
        this.tipoElemento = tipoElemento;
        this.herramienta = herramienta;
        this.icono = icono;
        this.tipoIcono=tipoIcono;
        this.habilitado = habilitado;
        this.visible = visible;
        this.compartir = compartir;//JSA01
        this.tipoAcciones = tipoAcciones;
        this.ventana = ventanas;
        this.idMultiUsos = idMultiUso;
    }

    public Contenedor(ReporteDinamico reporteDinamico) {
        //this.id = id;
        this.nombre = reporteDinamico.getNombreAbreviado();
        //this.parentId = parentId;
        //this.ordenId = orderId;
        this.accion = "ReporteNominas";
        this.modifiers = "CTRL";
        this.keyCode = String.valueOf(reporteDinamico.getNombreAbreviado().charAt(0));
        //this.tipoElemento = tipoElemento;
        //this.herramienta = herramienta;
        this.icono = null;
        this.tipoIcono=null;
        this.habilitado = true;
        this.visible = true;
        this.compartir = true;//JSA01
        this.tipoAcciones = TipoAcciones.GRUPOREPORTE;
        this.ventana = null;
        this.idMultiUsos = reporteDinamico.getId();
    }

    public Contenedor(ParametrosConsulta reporteDinamico) {
        //this.id = id;
        this.nombre = reporteDinamico.getNombreAbreviado();
        //this.parentId = parentId;
        //this.ordenId = orderId;
        if (reporteDinamico.isModoVisualizarTabla()) {
            this.accion = "CriteriosConsultaTablaV2";
        } else {
            this.accion = "CriteriosConsultaTree";
        }
        this.modifiers = "CTRL";
        this.keyCode = String.valueOf(reporteDinamico.getNombreAbreviado().charAt(0));
        //this.tipoElemento = tipoElemento;
        //this.herramienta = herramienta;
        this.icono = null;
        this.tipoIcono=null;
        this.habilitado = true;
        this.visible = true;
        this.compartir = true;//JSA01
        this.tipoAcciones = TipoAcciones.GRUPOCONSULTA;
        this.ventana = null;
        this.idMultiUsos = reporteDinamico.getId();
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getCompartir() {
        return compartir;
    }

    public void setCompartir(Boolean compartir) {
        this.compartir = compartir;
    }

    public TipoAcciones getTipoAcciones() {
        return tipoAcciones;
    }

    public void setTipoAcciones(TipoAcciones tipoAcciones) {
        this.tipoAcciones = tipoAcciones;
    }

    public Ventana getVentana() {
        return ventana;
    }

    public void setVentana(Ventana ventana) {
        this.ventana = ventana;
    }

    public Long getIdMultiUsos() {
        return idMultiUsos;
    }

    public void setIdMultiUsos(Long idMultiUsos) {
        this.idMultiUsos = idMultiUsos;
    }

    public String getExterno() {
        return externo;
    }

    public void setExterno(String externo) {
        this.externo = externo;
    }

    public RazonSocial getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(RazonSocial razonSocial) {
        this.razonSocial = razonSocial;
    }

    public TipoIcono getTipoIcono() {
        return tipoIcono;
    }

    public void setTipoIcono(TipoIcono tipoIcono) {
        this.tipoIcono = tipoIcono;
    }

}

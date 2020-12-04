/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 02/05/2015 Descripción: se cambio el
 * tipo de dato de Integer a Long el ID
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "PeriodosNomina",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"clave", "año", "tipoNomina_ID", "tipoCorrida_ID"})})
public class PeriodosNomina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//JSA01
    @JoinColumn(nullable = false)
    private String clave;
    @JoinColumn(nullable = false)
    private Integer año;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = false, insertable = true, updatable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "tipoCorrida_ID", nullable = false, insertable = true, updatable = true)
    private TipoCorrida tipoCorrida;
    private String descripcion;
    private Integer tipoUso;
    private Integer diasPago;
    private Integer diasIMSS;
    private boolean descontarAhorro;
    private boolean descontarPrestamo;
    private boolean soloPercepciones;
    private boolean incluirBajas;
    private boolean status;
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    private boolean cierreMes;
    private boolean bloquear;
    @Temporal(TemporalType.DATE)
    private Date AcumularAMes;
    @Temporal(TemporalType.DATE)
    private Date fechaAsistenciInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaAsistenciaFinal;
    @Temporal(TemporalType.DATE)
    private Date fechaModificado;
    private String claveUsuario;
    private String detalleConceptoRecibo;
    private boolean origen;

    public PeriodosNomina() {
    }

    public Date getAcumularAMes() {
        return AcumularAMes;
    }

    public void setAcumularAMes(Date AcumularAMes) {
        this.AcumularAMes = AcumularAMes;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    public boolean isBloquear() {
        return bloquear;
    }

    public void setBloquear(boolean bloquear) {
        this.bloquear = bloquear;
    }

    public boolean isCierreMes() {
        return cierreMes;
    }

    public void setCierreMes(boolean cierreMes) {
        this.cierreMes = cierreMes;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public boolean isDescontarAhorro() {
        return descontarAhorro;
    }

    public void setDescontarAhorro(boolean descontarAhorro) {
        this.descontarAhorro = descontarAhorro;
    }

    public boolean isDescontarPrestamo() {
        return descontarPrestamo;
    }

    public void setDescontarPrestamo(boolean descontarPrestamo) {
        this.descontarPrestamo = descontarPrestamo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDiasIMSS() {
        return diasIMSS;
    }

    public void setDiasIMSS(Integer diasIMSS) {
        this.diasIMSS = diasIMSS;
    }

    public Integer getDiasPago() {
        return diasPago;
    }

    public void setDiasPago(Integer diasPago) {
        this.diasPago = diasPago;
    }

    public Date getFechaAsistenciInicial() {
        return fechaAsistenciInicial;
    }

    public void setFechaAsistenciInicial(Date fechaAsistenciInicial) {
        this.fechaAsistenciInicial = fechaAsistenciInicial;
    }

    public Date getFechaAsistenciaFinal() {
        return fechaAsistenciaFinal;
    }

    public void setFechaAsistenciaFinal(Date fechaAsistenciaFinal) {
        this.fechaAsistenciaFinal = fechaAsistenciaFinal;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaModificado() {
        return fechaModificado;
    }

    public void setFechaModificado(Date fechaModificado) {
        this.fechaModificado = fechaModificado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIncluirBajas() {
        return incluirBajas;
    }

    public void setIncluirBajas(boolean incluirBajas) {
        this.incluirBajas = incluirBajas;
    }

    public boolean isSoloPercepciones() {
        return soloPercepciones;
    }

    public void setSoloPercepciones(boolean soloPercepciones) {
        this.soloPercepciones = soloPercepciones;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public TipoCorrida getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(TipoCorrida tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public Integer getTipoUso() {
        return tipoUso;
    }

    public void setTipoUso(Integer tipoUso) {
        this.tipoUso = tipoUso;
    }

//    public PeriodosNomina(String clave, Integer diasPago, Date fechaInicial, Date fechaFinal, Date fechaPago) {
//        this.clave = clave;
//        this.diasPago = diasPago;
//        this.fechaInicial = fechaInicial;
//        this.fechaFinal = fechaFinal;
//        this.fechaPago = fechaPago;
//    }
//
//    public PeriodosNomina(String clave, Integer diasPago, Date fechaInicial, Date fechaFinal, Date fechaPago, Date AcumularAMes) {
//        this.clave = clave;
//        this.diasPago = diasPago;
//        this.fechaInicial = fechaInicial;
//        this.fechaFinal = fechaFinal;
//        this.fechaPago = fechaPago;
//        this.AcumularAMes = AcumularAMes;
//    }
//    
    public PeriodosNomina(String clave, Integer año, Integer diasPago, Integer diasIMSS, Date AcumularAMes) {
        this.clave = clave;
        this.año = año;
        this.diasPago = diasPago;
        this.diasIMSS = diasIMSS;
        this.AcumularAMes = AcumularAMes;
    }

    public String getDetalleConceptoRecibo() {
        return detalleConceptoRecibo;
    }

    public void setDetalleConceptoRecibo(String detalleConceptoRecibo) {
        this.detalleConceptoRecibo = detalleConceptoRecibo;
    }

    public boolean isOrigen() {
        return origen;
    }

    public void setOrigen(boolean origen) {
        this.origen = origen;
    }

}

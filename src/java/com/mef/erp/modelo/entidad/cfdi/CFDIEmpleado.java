/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.TipoNomina;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * @author User
 */
@Entity
@Table(name = "CFDIEmpleado",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "plazaPorEmpleadoMov_ID", "tipoNomina_ID",
                "periodosNomina_ID", "tipoCorrida_ID", "cfdiRecibo_ID"})})
public class CFDIEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "plazaPorEmpleadoMov_ID", nullable = false, insertable = true, updatable = true)
    private PlazasPorEmpleadosMov plazaPorEmpleadoMov;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = false, insertable = true, updatable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "periodosNomina_ID", nullable = false, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;
    @ManyToOne
    @JoinColumn(name = "tipoCorrida_ID", nullable = false, insertable = true, updatable = true)
    private TipoCorrida tipoCorrida;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cfdiRecibo_ID", nullable = false, insertable = true, updatable = true)
    private CFDIRecibo cfdiRecibo;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioPago;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinalPago;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Column(nullable = false)
    private Integer numeroDiasPago;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String noRegistroPatronal;
    private String departamento;
    private String puesto;
    @Column(nullable = false)
    private String RFC;
    @Column(length = 15)
    private String noSeguroSocial;
    @Column(nullable = false)
    private String formaPago;
    private String claveBancoSat;
    private String cuentaBancaria;
    @Column(length = 18)
    private String CLABE;
    @Column(length = 18, nullable = false)
    private String CURP;
    private String calle;
    private String noExterior;
    private String noInterior;
    private String colonia;
    private String codigoPostal;
    private String ciudad;
    private String municipio;
    private String estado;
    @Column(nullable = false)
    private String pais;
    private String tipoContrato;
    private String jornada;
    @Column(nullable = false)
    private String regimenContratacion;
    private String correoElectronico;
    private Double salBaseCotAport; //sueldoDiario;
    private Double salIntIMSS; //salarioDiarioIntegrado;
    @Temporal(TemporalType.DATE)
    private Date fechaInicioRelLaboral;
    private Integer antiguedad;  // en semanas
    private String antiguedadYMD;  // en Y M D
    @Column(nullable = false)
    private String periodiciadadPago;
    private String riesgoPuesto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public PlazasPorEmpleadosMov getPlazaPorEmpleadoMov() {
        return plazaPorEmpleadoMov;
    }

    public void setPlazaPorEmpleadoMov(PlazasPorEmpleadosMov plazaPorEmpleadoMov) {
        this.plazaPorEmpleadoMov = plazaPorEmpleadoMov;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public PeriodosNomina getPeriodosNomina() {
        return periodosNomina;
    }

    public void setPeriodosNomina(PeriodosNomina periodosNomina) {
        this.periodosNomina = periodosNomina;
    }

    public TipoCorrida getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(TipoCorrida tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public CFDIRecibo getCfdiRecibo() {
        return cfdiRecibo;
    }

    public void setCfdiRecibo(CFDIRecibo cfdiRecibo) {
        this.cfdiRecibo = cfdiRecibo;
    }

    public Date getFechaInicioPago() {
        return fechaInicioPago;
    }

    public void setFechaInicioPago(Date fechaInicioPago) {
        this.fechaInicioPago = fechaInicioPago;
    }

    public Date getFechaFinalPago() {
        return fechaFinalPago;
    }

    public void setFechaFinalPago(Date fechaFinalPago) {
        this.fechaFinalPago = fechaFinalPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Integer getNumeroDiasPago() {
        return numeroDiasPago;
    }

    public void setNumeroDiasPago(Integer numerioDiasPago) {
        this.numeroDiasPago = numerioDiasPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNoRegistroPatronal() {
        return noRegistroPatronal;
    }

    public void setNoRegistroPatronal(String noRegistroPatronal) {
        this.noRegistroPatronal = noRegistroPatronal;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNoSeguroSocial() {
        return noSeguroSocial;
    }

    public void setNoSeguroSocial(String noSeguroSocial) {
        this.noSeguroSocial = noSeguroSocial;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getClaveBancoSat() {
        return claveBancoSat;
    }

    public void setClaveBancoSat(String claveBancoSat) {
        this.claveBancoSat = claveBancoSat;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getCLABE() {
        return CLABE;
    }

    public void setCLABE(String CLABE) {
        this.CLABE = CLABE;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(String noExterior) {
        this.noExterior = noExterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getRegimenContratacion() {
        return regimenContratacion;
    }

    public void setRegimenContratacion(String regimenContratacion) {
        this.regimenContratacion = regimenContratacion;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Double getSalBaseCotAport() {
        return salBaseCotAport;
    }

    public void setSalBaseCotAport(Double salBaseCotAport) {
        this.salBaseCotAport = salBaseCotAport;
    }

    public Double getSalIntIMSS() {
        return salIntIMSS;
    }

    public void setSalIntIMSS(Double salIntIMSS) {
        this.salIntIMSS = salIntIMSS;
    }

    public Date getFechaInicioRelLaboral() {
        return fechaInicioRelLaboral;
    }

    public void setFechaInicioRelLaboral(Date fechaInicioRelLaboral) {
        this.fechaInicioRelLaboral = fechaInicioRelLaboral;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getAntiguedadYMD() {
        return antiguedadYMD;
    }

    public void setAntiguedadYMD(String antiguedadYMD) {
        this.antiguedadYMD = antiguedadYMD;
    }

    public String getPeriodiciadadPago() {
        return periodiciadadPago;
    }

    public void setPeriodiciadadPago(String periodiciadadPago) {
        this.periodiciadadPago = periodiciadadPago;
    }

    public String getRiesgoPuesto() {
        return riesgoPuesto;
    }

    public void setRiesgoPuesto(String riesgoPuesto) {
        this.riesgoPuesto = riesgoPuesto;
    }

    public String getNoInterior() {
        return noInterior;
    }

    public void setNoInterior(String noInterior) {
        this.noInterior = noInterior;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

}

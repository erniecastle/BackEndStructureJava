/**
 * @author: Daniel 
 * Fecha de Creación: 27/06/2012 
 * Compañía: Finesoft.
 * Descripción del programa: Entidad de Resultado
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01
 * Autor: Armando Sanchez	
 * Fecha: 02/nov/2012
 * Descripción: Se cambio el tipo int por Integer, por cuestion de a la hora de convertir su clase a
 * int no la encuentra.
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@Entity
public class Resultado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String claveEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombreAbreviado;
    private String domicilio;
    private String colonia;
    private String numeroExt;
    private String numeroInt;
    private String cp;
    private String rfc;
    private String curp;
    private String IMSS;
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    private Double salarioDiario;
    private Double salarioDiarioIntegrado;
    private String plazas;
    private String numeroConceptoNomina;
    private String descripcionConceptoNomina;
    private String descripcionAbreviadaConceptoNomina;
    private Integer naturaleza;//JSA01
    private String condicionConcepto;
    private String formulaConcepto;
    private String subCuentaConcepto;
    private String indicativoGravadoExento;
    private Double importe;
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    private String claveTipoNomina;
    private String nombreTipoNomina;
    private String clavePeriodosNomina;
    private String nombrePeriodosNomina;
    private Integer diasPago;
    private Integer diasIMSS;
    @Temporal(TemporalType.DATE)
    private Date fechaInicialPeriodo;
    @Temporal(TemporalType.DATE)
    private Date fechaFinalPeriodo;
    @Temporal(TemporalType.DATE)
    private Date fechaCierrePeriodo;
    private String claveCentroDeCosto;
    private String descripcionCentroDeCosto;
    private String descripcionPreviaCentroDeCosto;
    private String claveRegistrotPatronal;
    private String nombreRegistrotPatronal;
    private String registroPatronal;
    private String claveTurno;
    private String descripcionTurno;
    private String claveDepartamento;
    private String descripcionDepartamento;
    private String subCuentaDepartamento;
    private String clavePuesto;
    private String descripcionPuesto;
    private String descripcionPreviaPuesto;
    private String claveBanco;
    private String descripcionBanco;
    private String RFCBanco;
    private String formaDePago;
    @Temporal(TemporalType.DATE)
    private Date fechaIMSS;
    private String cuentaBancaria;
    private Integer salarioPor;
    private Float importePlazaPorEmplMov;
    private Integer horas;
    private Integer tipoRelacionLaboral;
    private String claveTipoContrato;
    private String descripcionTipoContrato;
    private Boolean sindicalizado;
    private String razonsocial;
    private String RFCRazonSocial;
    private String calleRazonSocial;
    private String numeroExRazonSocial;
    private String numeroInRazonSocial;
    private String coloniaRazonSocial;
    private String cpRazonSocial;
    private String telefono;

    public Resultado() {
    }

    public Resultado(Long id) {
        this.id = id;
    }

    public Resultado(MovNomConcep mnd, IngresosReingresosBajas irb, SalariosIntegrados si, PlazasPorEmpleadosMov pem, String indicativo) {
        this.id = mnd.getId();
        this.claveEmpleado = mnd.getEmpleado().getClave();
        this.nombre = mnd.getEmpleado().getNombre();
        this.apellidoPaterno = mnd.getEmpleado().getApellidoPaterno();
        this.apellidoMaterno = mnd.getEmpleado().getApellidoMaterno();
        this.nombreAbreviado = mnd.getEmpleado().getNombreAbreviado();
        this.domicilio = mnd.getEmpleado().getDomicilio();
        this.colonia = mnd.getEmpleado().getColonia();
        this.numeroExt = mnd.getEmpleado().getNumeroExt();
        this.numeroInt = mnd.getEmpleado().getNumeroInt();
        this.cp = mnd.getEmpleado().getCp().getClave();
        this.rfc = mnd.getEmpleado().getRFC();
        this.curp = mnd.getEmpleado().getCURP();
        if (mnd.getEmpleado().getIMSS() != null) {
            this.IMSS = mnd.getEmpleado().getIMSS();
        }
        this.fechaIngreso = irb.getFechaIngreso();
        this.salarioDiario = si.getSalarioDiarioFijo();
        this.salarioDiarioIntegrado = si.getSalarioDiarioIntegrado();
        if (pem.getPlazas() != null) {
            this.plazas = pem.getPlazas().getClave();
        }
        ConcepNomDefi cnd = mnd.getConcepNomDefi();
        this.numeroConceptoNomina = cnd.getClave();
        this.descripcionConceptoNomina = cnd.getDescripcion();
        this.descripcionAbreviadaConceptoNomina = cnd.getDescripcionAbreviada();
        this.naturaleza = cnd.getNaturaleza().getNaturaleza();
        this.condicionConcepto = cnd.getCondicionConcepto();
        this.formulaConcepto = cnd.getFormulaConcepto();
        this.subCuentaConcepto = cnd.getSubCuenta();
        this.indicativoGravadoExento = indicativo;
        this.importe = mnd.getResultado();
        this.fechaInicial = mnd.getFechaIni();
        this.fechaCierre = mnd.getFechaCierr();
        TipoNomina tn = mnd.getTipoNomina();
        this.claveTipoNomina = tn.getClave();
        this.nombreTipoNomina = tn.getDescripcion();
        PeriodosNomina pn = mnd.getPeriodosNomina();
        this.clavePeriodosNomina = pn.getClave();
        this.nombrePeriodosNomina = pn.getDescripcion();
        this.diasPago = pn.getDiasPago();
        this.diasIMSS = pn.getDiasIMSS();
        this.fechaInicialPeriodo = pn.getFechaInicial();
        this.fechaFinalPeriodo = pn.getFechaFinal();
        this.fechaCierrePeriodo = pn.getFechaCierre();
        if (mnd.getCentroDeCosto() != null) {
            CentroDeCosto cc = mnd.getCentroDeCosto();
            this.claveCentroDeCosto = cc.getClave();
            this.descripcionCentroDeCosto = cc.getDescripcion();
            this.descripcionPreviaCentroDeCosto = cc.getDescripcionPrevia();
        } else {
            this.claveCentroDeCosto = "";
            this.descripcionCentroDeCosto = "";
            this.descripcionPreviaCentroDeCosto = "";
        }
        PlazasPorEmpleado pe = mnd.getPlazasPorEmpleado();
        this.claveRegistrotPatronal = pe.getRegistroPatronal().getClave();
        this.nombreRegistrotPatronal = pe.getRegistroPatronal().getNombreregtpatronal();
        this.registroPatronal = pe.getRegistroPatronal().getRegistropatronal();
        this.claveTurno = pem.getTurnos().getClave();
        this.descripcionTurno = pem.getTurnos().getDescripcion();
        this.claveDepartamento = pem.getDepartamentos().getClave();
        this.descripcionDepartamento = pem.getDepartamentos().getDescripcion();
        this.subCuentaDepartamento = pem.getDepartamentos().getSubCuenta();
        this.clavePuesto = pem.getPuestos().getClave();
        this.descripcionPuesto = pem.getPuestos().getDescripcion();
        this.descripcionPreviaPuesto = pem.getPuestos().getDescripcionPrevia();
        this.claveBanco = pem.getBancos().getClave();
        this.descripcionBanco = pem.getBancos().getDescripcion();
        this.RFCBanco = pem.getBancos().getRFC();
        this.formaDePago = pem.getFormasDePago().getDescripcion();
        this.fechaIMSS = pem.getFechaIMSS();
        this.cuentaBancaria = pem.getCuentaBancaria();
        this.salarioPor = pem.getSalarioPor();
        this.importePlazaPorEmplMov = pem.getImporte();
        this.horas = pem.getHoras();
        this.tipoRelacionLaboral = pem.getTipoRelacionLaboral();
        this.claveTipoContrato = pem.getTipoContrato().getClave();
        this.descripcionTipoContrato = pem.getTipoContrato().getDescripcion();
        this.sindicalizado = pem.getTipoContrato().getEsSindicalizado();
        this.razonsocial = mnd.getRazonesSociales().getRazonsocial();
        this.RFCRazonSocial = mnd.getRazonesSociales().getRfc();
        this.calleRazonSocial = mnd.getRazonesSociales().getCalle();
        this.numeroExRazonSocial = mnd.getRazonesSociales().getNumeroex();
        this.numeroInRazonSocial = mnd.getRazonesSociales().getNumeroin();
        this.coloniaRazonSocial = mnd.getRazonesSociales().getColonia();
        this.cpRazonSocial = mnd.getRazonesSociales().getCp().getClave();
        this.telefono = mnd.getRazonesSociales().getTelefono();
    }

    public Resultado(MovNomConcep mnd, IngresosReingresosBajas irb, SalariosIntegrados si, PlazasPorEmpleadosMov pem) {
        this.id = mnd.getId();
        this.claveEmpleado = mnd.getEmpleado().getClave();
        this.nombre = mnd.getEmpleado().getNombre();
        this.apellidoPaterno = mnd.getEmpleado().getApellidoPaterno();
        this.apellidoMaterno = mnd.getEmpleado().getApellidoMaterno();
        this.nombreAbreviado = mnd.getEmpleado().getNombreAbreviado();
        this.domicilio = mnd.getEmpleado().getDomicilio();
        this.colonia = mnd.getEmpleado().getColonia();
        this.numeroExt = mnd.getEmpleado().getNumeroExt();
        this.numeroInt = mnd.getEmpleado().getNumeroInt();
        this.cp = mnd.getEmpleado().getCp().getClave();
        this.rfc = mnd.getEmpleado().getRFC();
        this.curp = mnd.getEmpleado().getCURP();
        if (mnd.getEmpleado().getIMSS() != null) {
            this.IMSS = mnd.getEmpleado().getIMSS();
        }
        this.fechaIngreso = irb.getFechaIngreso();
        this.salarioDiario = si.getSalarioDiarioFijo();
        this.salarioDiarioIntegrado = si.getSalarioDiarioIntegrado();
        if (pem.getPlazas() != null) {
            this.plazas = pem.getPlazas().getClave();
        }
        ConcepNomDefi cnd = mnd.getConcepNomDefi();
        this.numeroConceptoNomina = cnd.getClave();
        this.descripcionConceptoNomina = cnd.getDescripcion();
        this.descripcionAbreviadaConceptoNomina = cnd.getDescripcionAbreviada();
        this.naturaleza = cnd.getNaturaleza().getNaturaleza();
        this.condicionConcepto = cnd.getCondicionConcepto();
        this.formulaConcepto = cnd.getFormulaConcepto();
        this.subCuentaConcepto = cnd.getSubCuenta();
        this.indicativoGravadoExento = "";
        this.importe = mnd.getResultado();
        this.fechaInicial = mnd.getFechaIni();
        this.fechaCierre = mnd.getFechaCierr();
        TipoNomina tn = mnd.getTipoNomina();
        this.claveTipoNomina = tn.getClave();
        this.nombreTipoNomina = tn.getDescripcion();
        PeriodosNomina pn = mnd.getPeriodosNomina();
        this.clavePeriodosNomina = pn.getClave();
        this.nombrePeriodosNomina = pn.getDescripcion();
        this.diasPago = pn.getDiasPago();
        this.diasIMSS = pn.getDiasIMSS();
        this.fechaInicialPeriodo = pn.getFechaInicial();
        this.fechaFinalPeriodo = pn.getFechaFinal();
        this.fechaCierrePeriodo = pn.getFechaCierre();
        if (mnd.getCentroDeCosto() != null) {
            CentroDeCosto cc = mnd.getCentroDeCosto();
            this.claveCentroDeCosto = cc.getClave();
            this.descripcionCentroDeCosto = cc.getDescripcion();
            this.descripcionPreviaCentroDeCosto = cc.getDescripcionPrevia();
        }
        PlazasPorEmpleado pe = mnd.getPlazasPorEmpleado();
        this.claveRegistrotPatronal = pe.getRegistroPatronal().getClave();
        this.nombreRegistrotPatronal = pe.getRegistroPatronal().getNombreregtpatronal();
        this.registroPatronal = pe.getRegistroPatronal().getRegistropatronal();
        this.claveTurno = pem.getTurnos().getClave();
        this.descripcionTurno = pem.getTurnos().getDescripcion();
        this.claveDepartamento = pem.getDepartamentos().getClave();
        this.descripcionDepartamento = pem.getDepartamentos().getDescripcion();
        this.subCuentaDepartamento = pem.getDepartamentos().getSubCuenta();
        this.clavePuesto = pem.getPuestos().getClave();
        this.descripcionPuesto = pem.getPuestos().getDescripcion();
        this.descripcionPreviaPuesto = pem.getPuestos().getDescripcionPrevia();
        this.claveBanco = pem.getBancos().getClave();
        this.descripcionBanco = pem.getBancos().getDescripcion();
        this.RFCBanco = pem.getBancos().getRFC();
        this.formaDePago = pem.getFormasDePago().getDescripcion();
        this.fechaIMSS = pem.getFechaIMSS();
        this.cuentaBancaria = pem.getCuentaBancaria();
        this.salarioPor = pem.getSalarioPor();
        this.importePlazaPorEmplMov = pem.getImporte();
        this.horas = pem.getHoras();
        this.tipoRelacionLaboral = pem.getTipoRelacionLaboral();
        this.claveTipoContrato = pem.getTipoContrato().getClave();
        this.descripcionTipoContrato = pem.getTipoContrato().getDescripcion();
        this.sindicalizado = pem.getTipoContrato().getEsSindicalizado();
        this.razonsocial = mnd.getRazonesSociales().getRazonsocial();
        this.RFCRazonSocial = mnd.getRazonesSociales().getRfc();
        this.calleRazonSocial = mnd.getRazonesSociales().getCalle();
        this.numeroExRazonSocial = mnd.getRazonesSociales().getNumeroex();
        this.numeroInRazonSocial = mnd.getRazonesSociales().getNumeroin();
        this.coloniaRazonSocial = mnd.getRazonesSociales().getColonia();
        this.cpRazonSocial = mnd.getRazonesSociales().getCp().getClave();
        this.telefono = mnd.getRazonesSociales().getTelefono();
    }

    public String getIMSS() {
        return IMSS;
    }

    public void setIMSS(String IMSS) {
        this.IMSS = IMSS;
    }

    public String getRFCBanco() {
        return RFCBanco;
    }

    public void setRFCBanco(String RFCBanco) {
        this.RFCBanco = RFCBanco;
    }

    public String getRFCRazonSocial() {
        return RFCRazonSocial;
    }

    public void setRFCRazonSocial(String RFCRazonSocial) {
        this.RFCRazonSocial = RFCRazonSocial;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombreAbreviado() {
        return nombreAbreviado;
    }

    public void setNombreAbreviado(String nombreAbreviado) {
        this.nombreAbreviado = nombreAbreviado;
    }

    public String getCalleRazonSocial() {
        return calleRazonSocial;
    }

    public void setCalleRazonSocial(String calleRazonSocial) {
        this.calleRazonSocial = calleRazonSocial;
    }

    public String getClaveBanco() {
        return claveBanco;
    }

    public void setClaveBanco(String claveBanco) {
        this.claveBanco = claveBanco;
    }

    public String getClaveCentroDeCosto() {
        return claveCentroDeCosto;
    }

    public void setClaveCentroDeCosto(String claveCentroDeCosto) {
        this.claveCentroDeCosto = claveCentroDeCosto;
    }

    public String getClaveDepartamento() {
        return claveDepartamento;
    }

    public void setClaveDepartamento(String claveDepartamento) {
        this.claveDepartamento = claveDepartamento;
    }

    public String getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String getClavePeriodosNomina() {
        return clavePeriodosNomina;
    }

    public void setClavePeriodosNomina(String clavePeriodosNomina) {
        this.clavePeriodosNomina = clavePeriodosNomina;
    }

    public String getClavePuesto() {
        return clavePuesto;
    }

    public void setClavePuesto(String clavePuesto) {
        this.clavePuesto = clavePuesto;
    }

    public String getClaveRegistrotPatronal() {
        return claveRegistrotPatronal;
    }

    public void setClaveRegistrotPatronal(String claveRegistrotPatronal) {
        this.claveRegistrotPatronal = claveRegistrotPatronal;
    }

    public String getClaveTipoContrato() {
        return claveTipoContrato;
    }

    public void setClaveTipoContrato(String claveTipoContrato) {
        this.claveTipoContrato = claveTipoContrato;
    }

    public String getClaveTipoNomina() {
        return claveTipoNomina;
    }

    public void setClaveTipoNomina(String claveTipoNomina) {
        this.claveTipoNomina = claveTipoNomina;
    }

    public String getClaveTurno() {
        return claveTurno;
    }

    public void setClaveTurno(String claveTurno) {
        this.claveTurno = claveTurno;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getColoniaRazonSocial() {
        return coloniaRazonSocial;
    }

    public void setColoniaRazonSocial(String coloniaRazonSocial) {
        this.coloniaRazonSocial = coloniaRazonSocial;
    }

    public String getCondicionConcepto() {
        return condicionConcepto;
    }

    public void setCondicionConcepto(String condicionConcepto) {
        this.condicionConcepto = condicionConcepto;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCpRazonSocial() {
        return cpRazonSocial;
    }

    public void setCpRazonSocial(String cpRazonSocial) {
        this.cpRazonSocial = cpRazonSocial;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getDescripcionAbreviadaConceptoNomina() {
        return descripcionAbreviadaConceptoNomina;
    }

    public void setDescripcionAbreviadaConceptoNomina(String descripcionAbreviadaConceptoNomina) {
        this.descripcionAbreviadaConceptoNomina = descripcionAbreviadaConceptoNomina;
    }

    public String getDescripcionBanco() {
        return descripcionBanco;
    }

    public void setDescripcionBanco(String descripcionBanco) {
        this.descripcionBanco = descripcionBanco;
    }

    public String getDescripcionCentroDeCosto() {
        return descripcionCentroDeCosto;
    }

    public void setDescripcionCentroDeCosto(String descripcionCentroDeCosto) {
        this.descripcionCentroDeCosto = descripcionCentroDeCosto;
    }

    public String getDescripcionConceptoNomina() {
        return descripcionConceptoNomina;
    }

    public void setDescripcionConceptoNomina(String descripcionConceptoNomina) {
        this.descripcionConceptoNomina = descripcionConceptoNomina;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }

    public String getDescripcionPreviaCentroDeCosto() {
        return descripcionPreviaCentroDeCosto;
    }

    public void setDescripcionPreviaCentroDeCosto(String descripcionPreviaCentroDeCosto) {
        this.descripcionPreviaCentroDeCosto = descripcionPreviaCentroDeCosto;
    }

    public String getDescripcionPreviaPuesto() {
        return descripcionPreviaPuesto;
    }

    public void setDescripcionPreviaPuesto(String descripcionPreviaPuesto) {
        this.descripcionPreviaPuesto = descripcionPreviaPuesto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public String getDescripcionTipoContrato() {
        return descripcionTipoContrato;
    }

    public void setDescripcionTipoContrato(String descripcionTipoContrato) {
        this.descripcionTipoContrato = descripcionTipoContrato;
    }

    public String getDescripcionTurno() {
        return descripcionTurno;
    }

    public void setDescripcionTurno(String descripcionTurno) {
        this.descripcionTurno = descripcionTurno;
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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaCierrePeriodo() {
        return fechaCierrePeriodo;
    }

    public void setFechaCierrePeriodo(Date fechaCierrePeriodo) {
        this.fechaCierrePeriodo = fechaCierrePeriodo;
    }

    public Date getFechaFinalPeriodo() {
        return fechaFinalPeriodo;
    }

    public void setFechaFinalPeriodo(Date fechaFinalPeriodo) {
        this.fechaFinalPeriodo = fechaFinalPeriodo;
    }

    public Date getFechaIMSS() {
        return fechaIMSS;
    }

    public void setFechaIMSS(Date fechaIMSS) {
        this.fechaIMSS = fechaIMSS;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaInicialPeriodo() {
        return fechaInicialPeriodo;
    }

    public void setFechaInicialPeriodo(Date fechaInicialPeriodo) {
        this.fechaInicialPeriodo = fechaInicialPeriodo;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }

    public String getFormulaConcepto() {
        return formulaConcepto;
    }

    public void setFormulaConcepto(String formulaConcepto) {
        this.formulaConcepto = formulaConcepto;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Float getImportePlazaPorEmplMov() {
        return importePlazaPorEmplMov;
    }

    public void setImportePlazaPorEmplMov(Float importePlazaPorEmplMov) {
        this.importePlazaPorEmplMov = importePlazaPorEmplMov;
    }

    public String getIndicativoGravadoExento() {
        return indicativoGravadoExento;
    }

    public void setIndicativoGravadoExento(String indicativoGravadoExento) {
        this.indicativoGravadoExento = indicativoGravadoExento;
    }

    public int getNaturaleza() {
        return naturaleza;
    }

    public void setNaturaleza(int naturaleza) {
        this.naturaleza = naturaleza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombrePeriodosNomina() {
        return nombrePeriodosNomina;
    }

    public void setNombrePeriodosNomina(String nombrePeriodosNomina) {
        this.nombrePeriodosNomina = nombrePeriodosNomina;
    }

    public String getNombreRegistrotPatronal() {
        return nombreRegistrotPatronal;
    }

    public void setNombreRegistrotPatronal(String nombreRegistrotPatronal) {
        this.nombreRegistrotPatronal = nombreRegistrotPatronal;
    }

    public String getNombreTipoNomina() {
        return nombreTipoNomina;
    }

    public void setNombreTipoNomina(String nombreTipoNomina) {
        this.nombreTipoNomina = nombreTipoNomina;
    }

    public String getNumeroConceptoNomina() {
        return numeroConceptoNomina;
    }

    public void setNumeroConceptoNomina(String numeroConceptoNomina) {
        this.numeroConceptoNomina = numeroConceptoNomina;
    }

    public String getNumeroExRazonSocial() {
        return numeroExRazonSocial;
    }

    public void setNumeroExRazonSocial(String numeroExRazonSocial) {
        this.numeroExRazonSocial = numeroExRazonSocial;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInRazonSocial() {
        return numeroInRazonSocial;
    }

    public void setNumeroInRazonSocial(String numeroInRazonSocial) {
        this.numeroInRazonSocial = numeroInRazonSocial;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getPlazas() {
        return plazas;
    }

    public void setPlazas(String plazas) {
        this.plazas = plazas;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public String getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(String registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Double getSalarioDiario() {
        return salarioDiario;
    }

    public void setSalarioDiario(Double salarioDiario) {
        this.salarioDiario = salarioDiario;
    }

    public Double getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(Double salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public Integer getSalarioPor() {
        return salarioPor;
    }

    public void setSalarioPor(Integer salarioPor) {
        this.salarioPor = salarioPor;
    }

    public Boolean getSindicalizado() {
        return sindicalizado;
    }

    public void setSindicalizado(Boolean sindicalizado) {
        this.sindicalizado = sindicalizado;
    }

    public String getSubCuentaConcepto() {
        return subCuentaConcepto;
    }

    public void setSubCuentaConcepto(String subCuentaConcepto) {
        this.subCuentaConcepto = subCuentaConcepto;
    }

    public String getSubCuentaDepartamento() {
        return subCuentaDepartamento;
    }

    public void setSubCuentaDepartamento(String subCuentaDepartamento) {
        this.subCuentaDepartamento = subCuentaDepartamento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getTipoRelacionLaboral() {
        return tipoRelacionLaboral;
    }

    public void setTipoRelacionLaboral(Integer tipoRelacionLaboral) {
        this.tipoRelacionLaboral = tipoRelacionLaboral;
    }
}
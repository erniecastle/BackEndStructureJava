/**
 * @author: Jose Ernesto Valenzuela Castillo Fecha de Creación: 12/06/2012
 * Compañía: Exito Software. Descripción del programa: Entidad de Plazas Por
 * Empleado Movimientos para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor: Armando Fecha: 29-Jun-2012 Descripción: se agregaron los
 * campos de tiporelacionlaboral y cambiotiporelacionlaboral
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor: Armando Fecha: 23-Jul-2012 Descripción: Se quito el campo
 * tipoSalario y salarioDiarioIntegrado ya que estos se se encuentra en la
 * entidad SalariosIntegrados
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor: Armando Fecha: 21/01/2016 Descripción: Se agrego la zona
 * geografica.
 * -----------------------------------------------------------------------------
 * Clave:JSA04 Autor: Armando Fecha: 17/02/2014 Descripción: Se agrego el campo
 * regimen de contratacion para el timbrado de la nomina
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "PlazasPorEmpleadosMov",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"plazasPorEmpleado_id", "fechaInicial"})})
public class PlazasPorEmpleadosMov implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private PlazasPorEmpleado plazasPorEmpleado;
    @ManyToOne
    private Plazas plazas;
    private Boolean cambioPlazasPosOrganigrama;
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaIMSS;
    @ManyToOne
    private TipoNomina tipoNomina;
    private Boolean cambioTipoDeNomina;
    @ManyToOne
    private Turnos turnos;
    private Boolean cambioTurno;
    @ManyToOne
    private CentroDeCosto centroDeCosto;
    private Boolean cambioCentroDeCostos;
    @ManyToOne
    private Departamentos departamentos;
    private Boolean cambioDepartamento;
    @ManyToOne
    private Puestos puestos;
    private Boolean cambioPuestos;
    @ManyToOne
    private Bancos bancos;
    private Boolean cambioBanco;
    @ManyToOne
    private FormasDePago formasDePago;
    private Boolean cambioFormaDePago;
    private String cuentaBancaria;
    private Boolean cambioCuentaBancaria;
    @Column(length = 1, nullable = true)
    private Integer salarioPor;//El salario por tabular su importe en el puesto se guarda diario asi como el salario por libre tambien.
    private Boolean cambioSalarioPor;
    private Float importe;
    private Integer horas;
    private Boolean cambioHoras;
    private Integer incrementoHoras;
    @ManyToOne
    private TipoContrato tipoContrato;
    private Boolean cambioTipoContrato;
    @Column(length = 1, nullable = true)
    private Integer tipoRelacionLaboral;//JSA01
    private Boolean cambioTipoRelacionLaboral;//JSA01
    private ZonaGeografica zonaGeografica;//JSA03
    private Boolean cambioZonaGeografica;//JSA03
    private String regimenContratacion;//JSA04
    private Boolean cambioRegimenContratacion;//JSA04
    private String clabe;
    private Boolean cambioClabe;
    /**
     * **valores usados para calcular sueldo diario**
     */
    private transient double sueldoDiario;

    /**
     * termina valores usados para calcular sueldo diario
     *
     * @return sueldoDiario*
     */
    public double getSueldoDiario() {
        return sueldoDiario;
    }

    public void setSueldoDiario(double sueldoDiario) {
        this.sueldoDiario = sueldoDiario;
    }

    public PlazasPorEmpleadosMov() {
    }

    public PlazasPorEmpleadosMov(String claveEmpleado, String claveTipoNomina) {
        PlazasPorEmpleado plazaEmpleado = new PlazasPorEmpleado();
        Empleados empleado = new Empleados();
        empleado.setClave(claveEmpleado);
        plazaEmpleado.setEmpleados(empleado);
        this.plazasPorEmpleado = plazaEmpleado;
        TipoNomina n = new TipoNomina();
        n.setClave(claveTipoNomina);
        this.tipoNomina = n;
        System.out.println("");
    }

    public Bancos getBancos() {
        return bancos;
    }

    public void setBancos(Bancos bancos) {
        this.bancos = bancos;
    }

    public Boolean getCambioBanco() {
        return cambioBanco;
    }

    public void setCambioBanco(Boolean cambioBanco) {
        this.cambioBanco = cambioBanco;
    }

    public Boolean getCambioCentroDeCostos() {
        return cambioCentroDeCostos;
    }

    public void setCambioCentroDeCostos(Boolean cambioCentroDeCostos) {
        this.cambioCentroDeCostos = cambioCentroDeCostos;
    }

    public Boolean getCambioCuentaBancaria() {
        return cambioCuentaBancaria;
    }

    public void setCambioCuentaBancaria(Boolean cambioCuentaBancaria) {
        this.cambioCuentaBancaria = cambioCuentaBancaria;
    }

    public Boolean getCambioDepartamento() {
        return cambioDepartamento;
    }

    public void setCambioDepartamento(Boolean cambioDepartamento) {
        this.cambioDepartamento = cambioDepartamento;
    }

    public Boolean getCambioFormaDePago() {
        return cambioFormaDePago;
    }

    public void setCambioFormaDePago(Boolean cambioFormaDePago) {
        this.cambioFormaDePago = cambioFormaDePago;
    }

    public Boolean getCambioHoras() {
        return cambioHoras;
    }

    public void setCambioHoras(Boolean cambioHoras) {
        this.cambioHoras = cambioHoras;
    }

    public Boolean getCambioPlazasPosOrganigrama() {
        return cambioPlazasPosOrganigrama;
    }

    public void setCambioPlazasPosOrganigrama(Boolean cambioPlazasPosOrganigrama) {
        this.cambioPlazasPosOrganigrama = cambioPlazasPosOrganigrama;
    }

    public Boolean getCambioPuestos() {
        return cambioPuestos;
    }

    public void setCambioPuestos(Boolean cambioPuestos) {
        this.cambioPuestos = cambioPuestos;
    }

    public Boolean getCambioSalarioPor() {
        return cambioSalarioPor;
    }

    public void setCambioSalarioPor(Boolean cambioSalarioPor) {
        this.cambioSalarioPor = cambioSalarioPor;
    }

    public Boolean getCambioTipoContrato() {
        return cambioTipoContrato;
    }

    public void setCambioTipoContrato(Boolean cambioTipoContrato) {
        this.cambioTipoContrato = cambioTipoContrato;
    }

    public Boolean getCambioTipoDeNomina() {
        return cambioTipoDeNomina;
    }

    public void setCambioTipoDeNomina(Boolean cambioTipoDeNomina) {
        this.cambioTipoDeNomina = cambioTipoDeNomina;
    }

    public Boolean getCambioTurno() {
        return cambioTurno;
    }

    public void setCambioTurno(Boolean cambioTurno) {
        this.cambioTurno = cambioTurno;
    }

    public CentroDeCosto getCentroDeCosto() {
        return centroDeCosto;
    }

    public void setCentroDeCosto(CentroDeCosto centroDeCosto) {
        this.centroDeCosto = centroDeCosto;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public Date getFechaIMSS() {
        return fechaIMSS;
    }

    public void setFechaIMSS(Date fechaIMSS) {
        this.fechaIMSS = fechaIMSS;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public FormasDePago getFormasDePago() {
        return formasDePago;
    }

    public void setFormasDePago(FormasDePago formasDePago) {
        this.formasDePago = formasDePago;
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

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public Integer getIncrementoHoras() {
        return incrementoHoras;
    }

    public void setIncrementoHoras(Integer incrementoHoras) {
        this.incrementoHoras = incrementoHoras;
    }

    public Plazas getPlazas() {
        return plazas;
    }

    public void setPlazas(Plazas plazas) {
        this.plazas = plazas;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public Puestos getPuestos() {
        return puestos;
    }

    public void setPuestos(Puestos puestos) {
        this.puestos = puestos;
    }

    public Integer getSalarioPor() {
        return salarioPor;
    }

    public void setSalarioPor(Integer salarioPor) {
        this.salarioPor = salarioPor;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }

    public Boolean getCambioTipoRelacionLaboral() {
        return cambioTipoRelacionLaboral;
    }

    public void setCambioTipoRelacionLaboral(Boolean cambioTipoRelacionLaboral) {
        this.cambioTipoRelacionLaboral = cambioTipoRelacionLaboral;
    }

    public Integer getTipoRelacionLaboral() {
        return tipoRelacionLaboral;
    }

    public void setTipoRelacionLaboral(Integer tipoRelacionLaboral) {
        this.tipoRelacionLaboral = tipoRelacionLaboral;
    }

    public Boolean getCambioZonaGeografica() {
        return cambioZonaGeografica;
    }

    public void setCambioZonaGeografica(Boolean cambioZonaGeografica) {
        this.cambioZonaGeografica = cambioZonaGeografica;
    }

    public ZonaGeografica getZonaGeografica() {
        return zonaGeografica;
    }

    public void setZonaGeografica(ZonaGeografica zonaGeografica) {
        this.zonaGeografica = zonaGeografica;
    }

    public String getRegimenContratacion() {
        return regimenContratacion;
    }

    public void setRegimenContratacion(String regimenContratacion) {
        this.regimenContratacion = regimenContratacion;
    }

    public Boolean isCambioRegimenContratacion() {
        return cambioRegimenContratacion;
    }

    public void setCambioRegimenContratacion(Boolean cambioRegimenContratacion) {
        this.cambioRegimenContratacion = cambioRegimenContratacion;
    }

    public String getClabe() {
        return clabe;
    }

    public void setClabe(String clabe) {
        this.clabe = clabe;
    }

    public Boolean isCambioClabe() {
        return cambioClabe;
    }

    public void setCambioClabe(Boolean cambioClabe) {
        this.cambioClabe = cambioClabe;
    }

}

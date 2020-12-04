/**
 * @author: Ernesto valenzuela Fecha de Creación: 28/08/2013 Compañía: Exito
 * Software. Descripción del programa: Entidad de CreditoPorEmpleado para
 * catalogo
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Descripcion: Se cambiaron los tipo datos Float
 * a Double
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Formula;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "CreditoPorEmpleado",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "creditoAhorro_ID", "numeroCredito"})})
public class CreditoPorEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "creditoAhorro_ID", nullable = false, insertable = true, updatable = true)
    private CreditoAhorro creditoAhorro;
    @ManyToOne
    @JoinColumn(name = "empleados_ID", nullable = false, insertable = true, updatable = true)
    private Empleados empleados;
    @Column(nullable = false, length = 30)
    private String numeroCredito;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCredito;
    @Column(nullable = false)
    private Double totalCredito;//JSA01
    private Integer modoDescuentoCredito;
    private Integer numeroParcialidades;
    @Column(nullable = false)
    private Double montoDescuento;//JSA01
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date inicioDescuento;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaVence;
    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date fechaAutorizacion;
    @Formula("(select CASE WHEN (cr.tipoConfiguracion = '1') then (totalCredito + (select CASE WHEN (COUNT(*) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (c.importe is null) then 0.0 ELSE c.importe END)) END FROM CreditoMovimientos c where c.creditoPorEmpleado_ID = id AND c.tiposMovimiento = 2)) - (select CASE WHEN (COUNT(*) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (c.importe is null) then 0.0 ELSE c.importe END)) END FROM CreditoMovimientos c where c.creditoPorEmpleado_ID = id AND (c.tiposMovimiento = 0 or c.tiposMovimiento = 4)) else ((select CASE WHEN (COUNT(*) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (c.importe is null) then 0.0 ELSE c.importe END)) END FROM CreditoMovimientos c where c.creditoPorEmpleado_ID = id AND (c.tiposMovimiento = 0 or c.tiposMovimiento = 4))) - (select CASE WHEN (COUNT(*) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (c.importe is null) then 0.0 ELSE c.importe END)) END FROM CreditoMovimientos c where c.creditoPorEmpleado_ID = id AND c.tiposMovimiento = 2) end FROM CreditoPorEmpleado  c, CreditoAhorro cr where c.creditoAhorro_id=cr.id and c.id = id)")
    private Double saldo;
    @ManyToOne
    @JoinColumn(name = "periodosNomina_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;
    @Column(nullable = true)
    private String cuentaContable;
    @Column(nullable = true, length = 255)
    private String numeroEmpleadoExtra;
    @Transient
    private Integer informer;
    @Transient
    private String claveConfiguracion;

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

    public CreditoAhorro getCreditoAhorro() {
        return creditoAhorro;
    }

    public void setCreditoAhorro(CreditoAhorro creditoAhorro) {
        this.creditoAhorro = creditoAhorro;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public String getNumeroCredito() {
        return numeroCredito;
    }

    public void setNumeroCredito(String numeroCredito) {
        this.numeroCredito = numeroCredito;
    }

    public Date getFechaCredito() {
        return fechaCredito;
    }

    public void setFechaCredito(Date fechaCredito) {
        this.fechaCredito = fechaCredito;
    }

    public Double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(Double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public Integer getModoDescuentoCredito() {
        return modoDescuentoCredito;
    }

    public void setModoDescuentoCredito(Integer modoDescuentoCredito) {
        this.modoDescuentoCredito = modoDescuentoCredito;
    }

    public Integer getNumeroParcialidades() {
        return numeroParcialidades;
    }

    public void setNumeroParcialidades(Integer numeroParcialidades) {
        this.numeroParcialidades = numeroParcialidades;
    }

    public Double getMontoDescuento() {
        return montoDescuento;
    }

    public void setMontoDescuento(Double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    public Date getInicioDescuento() {
        return inicioDescuento;
    }

    public void setInicioDescuento(Date inicioDescuento) {
        this.inicioDescuento = inicioDescuento;
    }

    public Date getFechaVence() {
        return fechaVence;
    }

    public void setFechaVence(Date fechaVence) {
        this.fechaVence = fechaVence;
    }

    public Date getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(Date fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public PeriodosNomina getPeriodosNomina() {
        return periodosNomina;
    }

    public void setPeriodosNomina(PeriodosNomina periodosNomina) {
        this.periodosNomina = periodosNomina;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getNumeroEmpleadoExtra() {
        return numeroEmpleadoExtra;
    }

    public void setNumeroEmpleadoExtra(String numeroEmpleadoExtra) {
        this.numeroEmpleadoExtra = numeroEmpleadoExtra;
    }

    public Integer getInformer() {
        return informer;
    }

    public void setInformer(Integer informer) {
        this.informer = informer;
    }

    public String getClaveConfiguracion() {
        return claveConfiguracion;
    }

    public void setClaveConfiguracion(String claveConfiguracion) {
        this.claveConfiguracion = claveConfiguracion;
    }

}

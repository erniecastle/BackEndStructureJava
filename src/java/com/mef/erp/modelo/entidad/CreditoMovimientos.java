/**
 * @author: Ernesto valenzuela Fecha de Creacion: 06/09/2013 Companía: Exito
 * Software. Descripción del programa: Entidad de CreditoMovimientos para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Descripcion: Se quito el campo numero y se
 * cambio el tipo de dato de importe de Float a Double.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor: Jose Armando Fecha:24/04/2015 Descripcion: Se cambio el
 * tipo de campo de un MovNomConcep a un listado de MovNomConcep ya que aveces
 * pueden generarse 2 movimientos por culpa del periodo de nomina cuando abarca
 * 2 meses.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "CreditoMovimientos",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"creditoPorEmpleado_ID", "tiposMovimiento", "fecha"})})
public class CreditoMovimientos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @Cascade(value = {org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "creditoPorEmpleado_ID", nullable = false, insertable = true, updatable = true)
    private CreditoPorEmpleado creditoPorEmpleado;
    private TiposMovimiento tiposMovimiento;
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(nullable = true)
    private Double importe;//JSA01
    @ManyToOne
    @JoinColumn(name = "initPeriodNom_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;
    private Integer numeroPeriodosBloquear;
    @Column(length = 255, nullable = true)
    private String observaciones;
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "creditoMovimientos_ID", insertable = true, updatable = true)
    @OneToMany(cascade = CascadeType.ALL)
    private List<MovNomConcep> movNomConceps;//JSA01
    @Transient
    private Integer informer;
    @Transient
    private Double importeOriginal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreditoPorEmpleado getCreditoPorEmpleado() {
        return creditoPorEmpleado;
    }

    public void setCreditoPorEmpleado(CreditoPorEmpleado creditoPorEmpleado) {
        this.creditoPorEmpleado = creditoPorEmpleado;
    }

    public TiposMovimiento getTiposMovimiento() {
        return tiposMovimiento;
    }

    public void setTiposMovimiento(TiposMovimiento tiposMovimiento) {
        this.tiposMovimiento = tiposMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public PeriodosNomina getPeriodosNomina() {
        return periodosNomina;
    }

    public void setPeriodosNomina(PeriodosNomina periodosNomina) {
        this.periodosNomina = periodosNomina;
    }

    public Integer getNumeroPeriodosBloquear() {
        return numeroPeriodosBloquear;
    }

    public void setNumeroPeriodosBloquear(Integer numeroPeriodosBloquear) {
        this.numeroPeriodosBloquear = numeroPeriodosBloquear;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getInformer() {
        return informer;
    }

    public void setInformer(Integer informer) {
        this.informer = informer;
    }

    public Double getImporteOriginal() {
        return importeOriginal;
    }

    public void setImporteOriginal(Double importeOriginal) {
        this.importeOriginal = importeOriginal;
    }

    public List<MovNomConcep> getMovNomConceps() {
        return movNomConceps;
    }

    public void setMovNomConceps(List<MovNomConcep> MovNomConceps) {
        this.movNomConceps = MovNomConceps;
    }
}

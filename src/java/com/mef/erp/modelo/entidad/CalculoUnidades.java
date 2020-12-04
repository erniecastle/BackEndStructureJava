/**
 * @author: Jose Armando Fecha de Creación: 12/05/2015 Compañía: Macropro
 * Descripción del programa: Se agrego esta tabla para guardar las variables del
 * calculo
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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Formula;

@Entity
@Table(name = "CalculoUnidades",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "empleado_ID", "tipoNomina_ID", "periodosNomina_ID", "tipoCorrida_ID", "numero", "ejercicio", "mes"})})
public class CalculoUnidades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = true, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "empleado_ID", nullable = true, insertable = true, updatable = true)
    private Empleados empleado;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = true, insertable = true, updatable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "periodosNomina_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;
    @ManyToOne
    @JoinColumn(name = "tipoCorrida_ID", nullable = true, insertable = true, updatable = true)
    private TipoCorrida tipoCorrida;
    @JoinColumn(name = "numero", nullable = true, insertable = true, updatable = true)
    private Integer numero;
    @JoinColumn(name = "ejercicio", nullable = true, insertable = true, updatable = true)
    private int ejercicio;
    @JoinColumn(name = "mes", nullable = true, insertable = true, updatable = true)
    private Integer mes;
    @ManyToOne
    @JoinColumn(name = "plazasPorEmpleado_ID", nullable = true, insertable = true, updatable = true)
    private PlazasPorEmpleado plazasPorEmpleado;
    private int uso;
    private int numMovParticion;
    private Double diasTrabajados;
    private Double diasRetardo;
    private Double diasFalta;
    private Integer diasAusentismo;
    private Double diasPermisoconsueldo;
    private Double diasPermisosinsueldo;
    private Integer diasIncapacidadEnfermedad;
    private Integer diasIncapacidadAccidente;
    private Integer diasIncapacidadMaternidad;
    private Integer diasOtrasIncapacidades;
    private Double diasDescansoLaborado;
    private Double diasFestivoLaborado;
    private Double diasDomingoLaborado;
    @Formula("hrsExtraDoble + hrsExtraTriple")
    private Double hrsTiempoExtra;
    private Double hrsExtraDoble;
    private Double hrsExtraTriple;
    private Integer diasDescanso;
    private Integer diasFestivo;
    private Integer diasVacaciones;
    private Double diasPrimaVacacional;
    @ManyToOne
    private TiposVacaciones tiposVacaciones;

    public CalculoUnidades() {
    }

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

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public int getNumMovParticion() {
        return numMovParticion;
    }

    public void setNumMovParticion(int numMovParticion) {
        this.numMovParticion = numMovParticion;
    }

    public Double getDiasTrabajados() {
        return diasTrabajados;
    }

    public void setDiasTrabajados(Double diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }

    public Double getDiasRetardo() {
        return diasRetardo;
    }

    public void setDiasRetardo(Double diasRetardo) {
        this.diasRetardo = diasRetardo;
    }

    public Double getDiasFalta() {
        return diasFalta;
    }

    public void setDiasFalta(Double diasFalta) {
        this.diasFalta = diasFalta;
    }

    public Double getDiasPermisoconsueldo() {
        return diasPermisoconsueldo;
    }

    public void setDiasPermisoconsueldo(Double diasPermisoconsueldo) {
        this.diasPermisoconsueldo = diasPermisoconsueldo;
    }

    public Double getDiasPermisosinsueldo() {
        return diasPermisosinsueldo;
    }

    public void setDiasPermisosinsueldo(Double diasPermisosinsueldo) {
        this.diasPermisosinsueldo = diasPermisosinsueldo;
    }

    public Integer getDiasIncapacidadEnfermedad() {
        return diasIncapacidadEnfermedad;
    }

    public void setDiasIncapacidadEnfermedad(Integer diasIncapacidadEnfermedad) {
        this.diasIncapacidadEnfermedad = diasIncapacidadEnfermedad;
    }

    public Integer getDiasIncapacidadAccidente() {
        return diasIncapacidadAccidente;
    }

    public void setDiasIncapacidadAccidente(Integer diasIncapacidadAccidente) {
        this.diasIncapacidadAccidente = diasIncapacidadAccidente;
    }

    public Integer getDiasIncapacidadMaternidad() {
        return diasIncapacidadMaternidad;
    }

    public void setDiasIncapacidadMaternidad(Integer diasIncapacidadMaternidad) {
        this.diasIncapacidadMaternidad = diasIncapacidadMaternidad;
    }

    public Integer getDiasOtrasIncapacidades() {
        return diasOtrasIncapacidades;
    }

    public void setDiasOtrasIncapacidades(Integer diasOtrasIncapacidades) {
        this.diasOtrasIncapacidades = diasOtrasIncapacidades;
    }

    public Double getDiasDescansoLaborado() {
        return diasDescansoLaborado;
    }

    public void setDiasDescansoLaborado(Double diasDescansoLaborado) {
        this.diasDescansoLaborado = diasDescansoLaborado;
    }

    public Double getDiasFestivoLaborado() {
        return diasFestivoLaborado;
    }

    public void setDiasFestivoLaborado(Double diasFestivoLaborado) {
        this.diasFestivoLaborado = diasFestivoLaborado;
    }

    public Double getDiasDomingoLaborado() {
        return diasDomingoLaborado;
    }

    public void setDiasDomingoLaborado(Double diasDomingoLaborado) {
        this.diasDomingoLaborado = diasDomingoLaborado;
    }

    public Double getHrsTiempoExtra() {
        return hrsTiempoExtra;
    }

    public void setHrsTiempoExtra(Double hrsTiempoExtra) {
        this.hrsTiempoExtra = hrsTiempoExtra;
    }

    public Double getHrsExtraDoble() {
        return hrsExtraDoble;
    }

    public void setHrsExtraDoble(Double hrsExtraDoble) {
        this.hrsExtraDoble = hrsExtraDoble;
    }

    public Double getHrsExtraTriple() {
        return hrsExtraTriple;
    }

    public void setHrsExtraTriple(Double hrsExtraTriple) {
        this.hrsExtraTriple = hrsExtraTriple;
    }

    public Integer getDiasDescanso() {
        return diasDescanso;
    }

    public void setDiasDescanso(Integer diasDescanso) {
        this.diasDescanso = diasDescanso;
    }

    public Integer getDiasFestivo() {
        return diasFestivo;
    }

    public void setDiasFestivo(Integer diasFestivo) {
        this.diasFestivo = diasFestivo;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public Integer getDiasAusentismo() {
        return diasAusentismo;
    }

    public void setDiasAusentismo(Integer diasAusentismo) {
        this.diasAusentismo = diasAusentismo;
    }

    public Integer getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(Integer diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public Double getDiasPrimaVacacional() {
        return diasPrimaVacacional;
    }

    public void setDiasPrimaVacacional(Double diasPrimaVacacional) {
        this.diasPrimaVacacional = diasPrimaVacacional;
    }

    public TiposVacaciones getTiposVacaciones() {
        return tiposVacaciones;
    }

    public void setTiposVacaciones(TiposVacaciones tiposVacaciones) {
        this.tiposVacaciones = tiposVacaciones;
    }

    public CalculoUnidades(CalculoUnidades nuevoCalculoUnidad) {
        this.id = null;
        this.razonesSociales = nuevoCalculoUnidad.getRazonesSociales();
        this.empleado = nuevoCalculoUnidad.getEmpleado();
        this.tipoNomina = nuevoCalculoUnidad.getTipoNomina();
        this.periodosNomina = nuevoCalculoUnidad.getPeriodosNomina();
        this.tipoCorrida = nuevoCalculoUnidad.getTipoCorrida();
        this.numero = nuevoCalculoUnidad.getNumero();
        this.ejercicio = nuevoCalculoUnidad.getEjercicio();
        this.mes = nuevoCalculoUnidad.getMes();
        this.plazasPorEmpleado = nuevoCalculoUnidad.getPlazasPorEmpleado();
        this.uso = nuevoCalculoUnidad.getUso();
        this.numMovParticion = nuevoCalculoUnidad.getNumMovParticion();
        this.diasTrabajados = nuevoCalculoUnidad.getDiasTrabajados();
        this.diasRetardo = nuevoCalculoUnidad.getDiasRetardo();
        this.diasFalta = nuevoCalculoUnidad.getDiasFalta();
        this.diasAusentismo = nuevoCalculoUnidad.getDiasAusentismo();
        this.diasPermisoconsueldo = nuevoCalculoUnidad.getDiasPermisoconsueldo();
        this.diasPermisosinsueldo = nuevoCalculoUnidad.getDiasPermisosinsueldo();
        this.diasIncapacidadEnfermedad = nuevoCalculoUnidad.getDiasIncapacidadEnfermedad();
        this.diasIncapacidadAccidente = nuevoCalculoUnidad.getDiasIncapacidadAccidente();
        this.diasIncapacidadMaternidad = nuevoCalculoUnidad.getDiasIncapacidadMaternidad();
        this.diasOtrasIncapacidades = nuevoCalculoUnidad.getDiasOtrasIncapacidades();
        this.diasDescansoLaborado = nuevoCalculoUnidad.getDiasDescansoLaborado();
        this.diasFestivoLaborado = nuevoCalculoUnidad.getDiasFestivoLaborado();
        this.diasDomingoLaborado = nuevoCalculoUnidad.getDiasDomingoLaborado();
        this.hrsTiempoExtra = nuevoCalculoUnidad.getHrsTiempoExtra();
        this.hrsExtraDoble = nuevoCalculoUnidad.getHrsExtraDoble();
        this.hrsExtraTriple = nuevoCalculoUnidad.getHrsExtraTriple();
        this.diasDescanso = nuevoCalculoUnidad.getDiasDescanso();
        this.diasFestivo = nuevoCalculoUnidad.getDiasFestivo();
        this.diasVacaciones = nuevoCalculoUnidad.getDiasVacaciones();
        this.diasPrimaVacacional = nuevoCalculoUnidad.getDiasPrimaVacacional();
        this.tiposVacaciones = nuevoCalculoUnidad.getTiposVacaciones();
    }

}

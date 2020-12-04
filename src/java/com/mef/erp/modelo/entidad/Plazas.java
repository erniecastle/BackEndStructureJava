/**
 * @author: daniel Fecha de Creación: --/--/---- Compañía: Finesoft. Descripción
 * del programa: Esta entidad tambien es llamada Posicion en el organigrama
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 10-04-2012
 * Descripción: Se agrego el campo de Razon social ya que esta informacion es
 * por empresa.
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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "Plazas",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})//JSA01
public class Plazas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 30)
    private String clave;
    @ManyToOne
    private Plazas plazasSubordinadoA;
    @Column(length = 1)
    private Integer tipoRelacionLaboral;
    @ManyToOne
    private TipoNomina tipoNomina;
    @ManyToOne
    private RegistroPatronal registroPatronal;
    @ManyToOne
    private CentroDeCosto centroDeCosto;
    @ManyToOne
    private Departamentos departamentos;
    @ManyToOne
    private CategoriasPuestos categoriasPuestos;
    @ManyToOne
    private Puestos puestos;
    @ManyToOne
    private Turnos turnos;
    @Temporal(TemporalType.TIME)
    private Date salida;
    @Column(length = 1, nullable = true)
    private Integer tipoSalario;
    @Column(length = 1, nullable = true)
    private Integer salarioPor;
    private float importe;
    @Column(length = 255)
    private String funciones;
    @Column(length = 255)
    private String perfil;
    @Column(length = 255)
    private String habilidades;
    @Column(length = 255)
    private String observaciones;
    @Column(length = 255)
    private String datosAdicionales;
    @ManyToOne
    private TipoContrato tipoContrato;
    private Double horas;
    private Integer cantidadPlazas;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JSA01

    public Integer getCantidadPlazas() {
        return cantidadPlazas;
    }

    public void setCantidadPlazas(Integer cantidadPlazas) {
        this.cantidadPlazas = cantidadPlazas;
    }

    public CategoriasPuestos getCategoriasPuestos() {
        return categoriasPuestos;
    }

    public void setCategoriasPuestos(CategoriasPuestos categoriasPuestos) {
        this.categoriasPuestos = categoriasPuestos;
    }

    public CentroDeCosto getCentroDeCosto() {
        return centroDeCosto;
    }

    public void setCentroDeCosto(CentroDeCosto centroDeCosto) {
        this.centroDeCosto = centroDeCosto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDatosAdicionales() {
        return datosAdicionales;
    }

    public void setDatosAdicionales(String datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    public Departamentos getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Departamentos departamentos) {
        this.departamentos = departamentos;
    }

    public String getFunciones() {
        return funciones;
    }

    public void setFunciones(String funciones) {
        this.funciones = funciones;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Puestos getPuestos() {
        return puestos;
    }

    public void setPuestos(Puestos puestos) {
        this.puestos = puestos;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public RegistroPatronal getRegistroPatronal() {
        return registroPatronal;
    }

    public void setRegistroPatronal(RegistroPatronal registroPatronal) {
        this.registroPatronal = registroPatronal;
    }

    public Integer getSalarioPor() {
        return salarioPor;
    }

    public void setSalarioPor(Integer salarioPor) {
        this.salarioPor = salarioPor;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
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

    public Integer getTipoRelacionLaboral() {
        return tipoRelacionLaboral;
    }

    public void setTipoRelacionLaboral(Integer tipoRelacionLaboral) {
        this.tipoRelacionLaboral = tipoRelacionLaboral;
    }

    public Integer getTipoSalario() {
        return tipoSalario;
    }

    public void setTipoSalario(Integer tipoSalario) {
        this.tipoSalario = tipoSalario;
    }

    public Turnos getTurnos() {
        return turnos;
    }

    public void setTurnos(Turnos turnos) {
        this.turnos = turnos;
    }

    public Plazas getPlazasSubordinadoA() {
        return plazasSubordinadoA;
    }

    public void setPlazasSubordinadoA(Plazas plazasSubordinadoA) {
        this.plazasSubordinadoA = plazasSubordinadoA;
    }

}

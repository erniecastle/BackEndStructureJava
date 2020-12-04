/**
 * @author: Ernesto Castillo Fecha de Creación: 13/05/2016 Compañía: Exito
 * Software. Descripción del programa: Entidad de Vacaciones Devengadas para
 * Hibernate
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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Ernesto
 */
@Entity
public class VacacionesDevengadas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "plazasPorEmpleado_ID", nullable = true, insertable = true, updatable = true)//JSA01
    private PlazasPorEmpleado plazasPorEmpleado;
    @JoinColumn(nullable = false)
    private Integer ejercicio;
    private Double salarioAniversario;
    private Integer diasVacaciones;
    private Double factorPrima;
    private Double diasPrimavaca;
    private Boolean registroInicial;

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

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(Integer ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Double getSalarioAniversario() {
        return salarioAniversario;
    }

    public void setSalarioAniversario(Double salarioAniversario) {
        this.salarioAniversario = salarioAniversario;
    }

    public Integer getDiasVacaciones() {
        return diasVacaciones;
    }

    public void setDiasVacaciones(Integer diasVacaciones) {
        this.diasVacaciones = diasVacaciones;
    }

    public Double getFactorPrima() {
        return factorPrima;
    }

    public void setFactorPrima(Double factorPrima) {
        this.factorPrima = factorPrima;
    }

    public Double getDiasPrimavaca() {
        return diasPrimavaca;
    }

    public void setDiasPrimavaca(Double diasPrimavaca) {
        this.diasPrimavaca = diasPrimavaca;
    }

    public Boolean isRegistroInicial() {
        return registroInicial;
    }

    public void setRegistroInicial(Boolean registroInicial) {
        this.registroInicial = registroInicial;
    }

}

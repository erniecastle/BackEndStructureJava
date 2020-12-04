/**
 * @author: Jose Ernesto Valenzuela Castillo Fecha de Creación: 12/06/2012
 * Compañía: Exito Software. Descripción del programa: Entidad de Plazas Por
 * Empleado para Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor: Armando Fecha: 29-Jun-2012 Descripción: se quito el campo
 * tiporelacionlaboral
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "PlazasPorEmpleado",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "registroPatronal_ID", "empleados_ID", "referencia"})})
public class PlazasPorEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "referencia", length = 30, nullable = false)
    private String clave;
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE})
    private Empleados empleados;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @ManyToOne
    @JoinColumn(name = "registroPatronal_ID", nullable = false, insertable = true, updatable = true)
    private RegistroPatronal registroPatronal;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Temporal(TemporalType.DATE)
    private Date fechaPrestaciones;
    @OneToOne
    private PlazasPorEmpleado reIngreso;
    private boolean plazaPrincipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaPrestaciones() {
        return fechaPrestaciones;
    }

    public void setFechaPrestaciones(Date fechaPrestaciones) {
        this.fechaPrestaciones = fechaPrestaciones;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public PlazasPorEmpleado getReIngreso() {
        return reIngreso;
    }

    public void setReIngreso(PlazasPorEmpleado reIngreso) {
        this.reIngreso = reIngreso;
    }

    public boolean isPlazaPrincipal() {
        return plazaPrincipal;
    }

    public void setPlazaPrincipal(boolean plazaPrincipal) {
        this.plazaPrincipal = plazaPrincipal;
    }
    
}

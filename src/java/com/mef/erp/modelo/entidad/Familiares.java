/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.ManyToOne;


/**
 *
 * @author daniel
 */
@Entity
public class Familiares implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Parentesco parentesco;
    private Boolean empleado;
    private String nombre;
    private Boolean sexo;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    private Boolean finado;
    private Boolean dependiente;
    @ManyToOne
    private Empleados empleados;

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }
    
    public Boolean getDependiente() {
        return dependiente;
    }

    public void setDependiente(Boolean dependiente) {
        this.dependiente = dependiente;
    }

 

    public Boolean getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Boolean empleado) {
        this.empleado = empleado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public Boolean getFinado() {
        return finado;
    }

    public void setFinado(Boolean finado) {
        this.finado = finado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
    
    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

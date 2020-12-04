/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad.cfdi;

import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author User
 */
public class DatosPorEmpleado implements Serializable {

    private PlazasPorEmpleadosMov plazasPorEmpleadosMov;
    private double salarioDiarioIntegrado;
    private Date fechaIngreso;
    private String detalleReciboNomina;
    private String detalleReciboCorrida;
    private String detalleReciboPeriodo;

    public PlazasPorEmpleadosMov getPlazasPorEmpleadosMov() {
        return plazasPorEmpleadosMov;
    }

    public void setPlazasPorEmpleadosMov(PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        this.plazasPorEmpleadosMov = plazasPorEmpleadosMov;
    }

    public double getSalarioDiarioIntegrado() {
        return salarioDiarioIntegrado;
    }

    public void setSalarioDiarioIntegrado(double salarioDiarioIntegrado) {
        this.salarioDiarioIntegrado = salarioDiarioIntegrado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getDetalleReciboNomina() {
        return detalleReciboNomina;
    }

    public void setDetalleReciboNomina(String detalleReciboNomina) {
        this.detalleReciboNomina = detalleReciboNomina;
    }

    public String getDetalleReciboCorrida() {
        return detalleReciboCorrida;
    }

    public void setDetalleReciboCorrida(String detalleReciboCorrida) {
        this.detalleReciboCorrida = detalleReciboCorrida;
    }

    public String getDetalleReciboPeriodo() {
        return detalleReciboPeriodo;
    }

    public void setDetalleReciboPeriodo(String detalleReciboPeriodo) {
        this.detalleReciboPeriodo = detalleReciboPeriodo;
    }
}
